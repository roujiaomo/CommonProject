package com.example.javademo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class ExampleService extends Service  {

    private static final String TAG = "ExampleService";
    /**
     * 无论哪种启动方式 , 该方法都只走一次
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    /**
     * 手动调用StartService时候 Service默认走 onCreate() -> onStartCommand()
     * 重复调用时 , 会重复走该方法
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 手动调用bindService时候 Service默认走 onCreate() -> onBind()
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return myBinder;
    }

    /**
     * 手动调用unBindService() ,默认走onUnbind() -> onDestroy()
     * @param intent
     * @return
     */

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    /**
     * 手动调用stopService() , 如果之前已绑定则
     */
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    /**
     * 与Activity通信的方法
     */
    public void fromActivity(){
        Log.d(TAG, "Activity里调用Service方法");
    }



    public class MyBinder extends Binder {
        public ExampleService getService(){
            return ExampleService.this;
        }
    }

    private MyBinder myBinder = new MyBinder();
}
