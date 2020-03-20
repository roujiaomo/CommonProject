package com.example.javademo.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.javademo.constant.LoadStatus;
import com.example.javademo.utils.DialogUtil;
import com.example.javademo.widget.ProgressLoading;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 基类带有网络请求Fragment
 * 已支持懒加载
 */
public abstract class BaseDataFragment<VM extends BaseViewModel, DB extends ViewDataBinding> extends Fragment {

    public Context mActivity;
    public DB binding;
    public VM mViewModel;
    private ProgressLoading progressLoading;//加载进度条
    private boolean isFirstLoad = true; // 是否第一次加载

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        initLoadStatus(); //数据请求状态
        initLiveDataObserve();
        initView();
        if(!isLazyLoad()){//不是懒加载
            if (isFirstLoad) {
                initData();//网络请求数据
                isFirstLoad = isVisibleReload();//第一次加载后
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isLazyLoad()){//是懒加载
            if (isFirstLoad) {
                initData();//网络请求数据
                isFirstLoad = isVisibleReload();//第一次加载后
            }
        }
    }

    private  void initViewModel(){
        if(isShareVMWithActivity()){
            mViewModel = ViewModelProviders.of(this).get(getVMClass());
        } else {
            mViewModel = ViewModelProviders.of((FragmentActivity) mActivity).get(getVMClass());
        }

    }

    private void initLoadStatus(){
        progressLoading = DialogUtil.getProgressDialog(mActivity);
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
                        if(progressLoading.isShowing()){
                            progressLoading.hideLoading();
                        }
                        break;
                    case LoadStatus.STATUS_EMPTY:
                        break;
                    case LoadStatus.STATUS_ERROR: //服务器错误
                        if(progressLoading.isShowing()){
                            progressLoading.hideLoading();
                        }
                        break;
                    case LoadStatus.STATUS_NO_NETWORK: //网络错误
                        if(progressLoading.isShowing()){
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
    public abstract int getLayoutId();

    /**
     * 初始化界面
     */
    public abstract void initView();

    public abstract void initData();

    public abstract void initLiveDataObserve();

    /**
     * 是否与Activity使用同一个ViewModel 默认false
     * (少数情况 : 宿主Activity与 Fragment数据源相同)
     * @return
     */
    public boolean isShareVMWithActivity(){
        return false;
    }

    /**
     * 是否每次可见都请求数据 (默认false)
     * true 每次重新请求数据
     * false 只第一次可见加载数据
     * @return
     */
    public  boolean isVisibleReload(){
        return false;
    };

    /**
     * 是否懒加载 (默认false)
     * @return
     */
    public  boolean isLazyLoad(){
        return false;
    };

    private Class<VM> getVMClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = type.getActualTypeArguments();
        return (Class<VM>) actualTypeArguments[0];
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = true;
    }
}
