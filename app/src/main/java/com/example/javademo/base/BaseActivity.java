package com.example.javademo.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.javademo.constant.LoadStatus;
import com.example.javademo.databinding.ActivityBaseBinding;
import com.example.javademo.widget.LoadStatusLiveData;
import com.example.javademo.widget.LoadingDialog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private LoadingDialog loadingDialog;
    public VM mViewModel;
    public DB mBinding;
    public ActivityBaseBinding activityBaseBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel(); //初始化ViewModel
        if(isShowLoadStatus()){
            initLoadStatus(); //数据请求状态
        }
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        //未封装title栏
//       activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
//       LayoutInflater.from(this).inflate(getLayoutId(), activityBaseBinding.flContentView);
        initView();//初始化布局
        initData(); //初始化数据
        initLiveDataObserve(); //请求返回数据处理
    }

    private  void initViewModel(){
        mViewModel = ViewModelProviders.of(this).get(getVMClass());
    }

    private void initLoadStatus(){
        loadingDialog = new LoadingDialog(this);
        LoadStatusLiveData loadStatusLiveData = LoadStatusLiveData.getInstance();
        loadStatusLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer status) {
                switch (status){
                    case LoadStatus.STATUS_LOADING:
                        loadingDialog.show("加载中");
                        break;
                    case LoadStatus.STATUS_UPLOADING:
                        loadingDialog.show("正在上传");
                        break;
                    case LoadStatus.STATUS_REQUEST:
                        loadingDialog.show("正在请求");
                        break;
                    case LoadStatus.STATUS_CONTENT: //加载完成
                        loadingDialog.dismiss();
                        break;
                    case LoadStatus.STATUS_EMPTY:
                        break;
                    case LoadStatus.STATUS_ERROR: //服务器错误
                        loadingDialog.dismiss();
                        break;
                    case LoadStatus.STATUS_NO_NETWORK: //网络错误
                        loadingDialog.dismiss();
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
    public abstract int getLayoutId();

    /**
     * 初始化界面
     */
    public abstract void initView();

    public abstract void initData();

    public abstract void initLiveDataObserve();

    /**
     * 是否显示网络请求时进度条
     * @return
     */
    public boolean isShowLoadStatus(){
        return true;
    }

    /**
     *  退出程序 供HomeActivity页使用
     */
    public void exit(){
        ActivityUtils.getActivityList().clear();
        System.exit(0);
    }


    /**
     * 适配框架
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
    private  Class<VM>  getVMClass(){
        ParameterizedType type= (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = type.getActualTypeArguments();
        return (Class<VM>) actualTypeArguments[0];
    }

}
