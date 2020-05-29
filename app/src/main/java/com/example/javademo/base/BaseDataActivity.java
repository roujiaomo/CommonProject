package com.example.javademo.base;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.javademo.R;
import com.example.javademo.constant.LoadStatus;
import com.example.javademo.utils.DialogUtil;
import com.example.javademo.widget.ProgressLoading;
import com.gyf.immersionbar.ImmersionBar;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 有网络请求的Activity
 *
 * @param <VM>
 * @param <DB>
 */
public abstract class BaseDataActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends AppCompatActivity {

    private static final String TAG = "BaseDataActivity";
    private ProgressLoading progressLoading;//加载进度条
    public VM mViewModel;
    public DB mBinding;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .statusBarColor(R.color.title_blue)
                 .statusBarAlpha(0.2f)  //状态栏透明度，不写默认0.0f
                .fitsSystemWindows(true)
                .init();
        mContext = this;
        progressLoading = DialogUtil.getProgressDialog(mContext);
        initViewModel(); //初始化ViewModel
        if (isShowLoadStatus()) {
            initLoadStatus(); //数据请求状态
        }
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        initLiveDataObserve(); //请求返回数据处理(observe形式监听)
        //未封装title栏
        initView();//初始化布局
        initData(); //初始化数据
    }

    //ViewModelProviders形式创建 未传给VM层引用
    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(getVMClass());
    }

    private void initLoadStatus() {
        mViewModel.loadStatusLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer status) {
                switch (status) {
                    case LoadStatus.STATUS_LOADING:
                        progressLoading.showMessage("加载中");
                        break;
                    case LoadStatus.STATUS_UPLOADING:
                        progressLoading.showMessage("正在上传");
                        break;
                    case LoadStatus.STATUS_REQUEST:
                        progressLoading.showMessage("正在请求");
                        break;
                    case LoadStatus.STATUS_CONTENT: //加载完成
                        if (progressLoading.isShowing()) {
                            progressLoading.hideLoading();
                        }
                        break;
                    case LoadStatus.STATUS_EMPTY:
                        break;
                    case LoadStatus.STATUS_ERROR: //服务器错误
                        if (progressLoading.isShowing()) {
                            progressLoading.hideLoading();
                        }
                        break;
                    case LoadStatus.STATUS_NO_NETWORK: //网络错误
                        if (progressLoading.isShowing()) {
                            progressLoading.hideLoading();
                        }
                        break;
                }

            }
        });
    }

    /**
     * 获取资源ID
     *
     * @return 布局资源ID
     */
    public abstract int  getLayoutId();

    /**
     * 初始化界面
     */
    public abstract void initView();

    public abstract void initData();

    public abstract void initLiveDataObserve();

    /**
     * 是否显示网络请求时进度条(并发请求时使用, 或使用RxJava zip操作符)
     *
     * @return
     */
    public boolean isShowLoadStatus() {
        return true;
    }

    /**
     * 退出程序 供HomeActivity页使用
     */
    public void exit() {
        ActivityUtils.getActivityList().clear();
        System.exit(0);
    }


    /**
     * 适配框架
     *
     * @return
     */
//    @Override
//    public Resources getResources() {
//        //需要升级到 v1.1.2 及以上版本才能使用 AutoSizeCompat
//        AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources());//如果没有自定义需求用这个方法
//        AutoSizeCompat.autoConvertDensity((super.getResources(), 667, false);//如果有自定义需求就用这个方法
//        return super.getResources();
//    }
//
    private Class<VM> getVMClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = type.getActualTypeArguments();
        return (Class<VM>) actualTypeArguments[0];
    }

}
