package com.db.commonlibrary.base;


import com.db.commonlibrary.constant.ErrorStatus;
import com.db.commonlibrary.constant.LoadStatus;
import com.db.commonlibrary.utils.NetExceptionUtil;
import com.db.commonlibrary.widget.LoadStatusLiveData;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private LoadStatusLiveData loadStatusLiveData;
    private int loadStatus = LoadStatus.STATUS_LOADING;

    public BaseObserver(LoadStatusLiveData loadStatusLiveData) {
        this.loadStatusLiveData = loadStatusLiveData;
    }

    /**
     * @param loadStatusLiveData
     * @param loadStatus         根据参数显示不同的加载进度条信息
     */
    public BaseObserver(LoadStatusLiveData loadStatusLiveData, int loadStatus) {
        this.loadStatusLiveData = loadStatusLiveData;
        this.loadStatus = loadStatus;
    }

    @Override
    public void onSubscribe(Disposable d) {
        loadStatusLiveData.post(loadStatus);
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        //数据请求正确
        if (response.getStatus() == 1) {
            onSuccess(response.getContent());
        } else {
            //数据请求错误 , 状态码错误
            //处理其他状态码的信息
            //showDialog(); context 取 application
            onFailure(null, response.getMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        NetExceptionUtil.handlerException(e);
        if (NetExceptionUtil.errorCode == ErrorStatus.NETWORK_ERROR) { //网络错误
            loadStatusLiveData.setStatus(LoadStatus.STATUS_NO_NETWORK);
        } else { //服务器错误
            loadStatusLiveData.setStatus(LoadStatus.STATUS_ERROR);
        }
    }

    @Override
    public void onComplete() {
        loadStatusLiveData.setStatus(LoadStatus.STATUS_CONTENT);
    }

    public abstract void onSuccess(T demo);

    public abstract void onFailure(Throwable e, String errorMsg);
}
