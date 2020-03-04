package com.db.commonlibrary.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.db.commonlibrary.constant.LoadStatus;
import com.db.commonlibrary.widget.LoadStatusLiveData;
import com.db.commonlibrary.widget.LoadingDialog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private LoadingDialog loadingDialog;
    public VM mViewModel;
    public DB mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel(); //初始化ViewModel
        if(isShowLoadStatus()){
            initLoadStatus(); //数据请求状态
        }
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        initView(mBinding);//初始化布局
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
     * @param bindView 界面绑定对象
     */
    public abstract void initView(DB bindView);

    public abstract void initData();

    public abstract void initLiveDataObserve();

    public boolean isShowLoadStatus(){
        return true;
    }

    private  Class<VM>  getVMClass(){
        ParameterizedType type= (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = type.getActualTypeArguments();
        return (Class<VM>) actualTypeArguments[0];
    }

}
