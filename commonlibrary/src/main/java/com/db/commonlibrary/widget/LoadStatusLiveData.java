package com.db.commonlibrary.widget;

import androidx.lifecycle.LiveData;

import com.db.commonlibrary.constant.LoadStatus;


public class LoadStatusLiveData extends LiveData<Integer> {
    private static LoadStatusLiveData instance;
    public static LoadStatusLiveData getInstance() {
        if (instance == null) {
            instance = new LoadStatusLiveData();
        }
        return instance;
    }

    @Override
    protected void onActive() {
        super.onActive();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }


    public void setStatus(int status) {
        instance.setValue(status);
    }

    public int getStatus() {
        if(instance.getValue()!= null){
            return instance.getValue();
        }else {
            return LoadStatus.STATUS_ERROR;
        }
    }

    /**
     * 子线程更新数据
     */
    public void  post(int status) {
        postValue(status);
    }

}
