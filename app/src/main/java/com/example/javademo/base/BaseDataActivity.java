package com.example.javademo.base;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.javademo.constant.LoadStatus;
import com.example.javademo.widget.CenterProgressDialog;
import com.example.javademo.widget.LoadStatusLiveData;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 有网络请求的Activity
 * @param <VM>
 * @param <DB>
 */
public abstract class BaseDataActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends AppCompatActivity {

    private static final String TAG = "BaseDataActivity";
    private  CenterProgressDialog centerProgressDialog;
    public VM mViewModel;
    public DB mBinding;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initViewModel(); //初始化ViewModel
        if(isShowLoadStatus()){
            initLoadStatus(); //数据请求状态
        }
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        //未封装title栏
        initView();//初始化布局
        initData(); //初始化数据
        initLiveDataObserve(); //请求返回数据处理
    }

    //ViewModelProviders形式创建 未传给VM层引用
    private  void initViewModel(){
        mViewModel = ViewModelProviders.of(this).get(getVMClass());
    }

    private void initLoadStatus(){
        centerProgressDialog = DialogUtil.getProgressDialog("加载中",mContext);
        centerProgressDialog.show();

        LoadStatusLiveData loadStatusLiveData = LoadStatusLiveData.getInstance();
        loadStatusLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer status) {
                switch (status){
                    case LoadStatus.STATUS_LOADING:
                        centerProgressDialog = DialogUtil.getProgressDialog("加载中",mContext);
                        centerProgressDialog.show();
                        break;
                    case LoadStatus.STATUS_UPLOADING:
                        centerProgressDialog = DialogUtil.getProgressDialog("正在上传",mContext);
                        centerProgressDialog.show();
                        break;
                    case LoadStatus.STATUS_REQUEST:
                        centerProgressDialog = DialogUtil.getProgressDialog("正在请求",mContext);
                        centerProgressDialog.show();
                        break;
                    case LoadStatus.STATUS_CONTENT: //加载完成
                        if(null!=centerProgressDialog){
                            centerProgressDialog.dismiss();
                        }
                        break;
                    case LoadStatus.STATUS_EMPTY:
                        break;
                    case LoadStatus.STATUS_ERROR: //服务器错误
                        if(null!=centerProgressDialog){
                            centerProgressDialog.dismiss();
                        }
                        break;
                    case LoadStatus.STATUS_NO_NETWORK: //网络错误
                        if(null!=centerProgressDialog){
                            centerProgressDialog.dismiss();
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
