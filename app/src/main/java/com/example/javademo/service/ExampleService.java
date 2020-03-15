package com.example.javademo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 服务示例
 * <p/>
 * 如果同时使用startService 与bindService 方法启动Service，需要终止该Service时，
 * 要调用stopService 和unbindService 方法（unbindService 依附于启动它的Context，
 * startService并不依附于启动它的Context。如果先调用unbindService ，这时服务并不会被终止，
 * 当调用stopService 后，服务才会被终止；如果先调用stopService ，服务也不会被终止，
 * 当调用unbindService 或者之前调用bindService 的Context不存在了（如Activity被finish掉了）服务才会自动停止）
 */
public class ExampleService extends Service {

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
    public int onStartCommand( Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Log.d(TAG, "onStartCommand: ");
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
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
     *
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
    public void fromActivity() {
        Log.d(TAG, "Activity里调用Service方法");
    }


    public class MyBinder extends Binder {
        public ExampleService getService() {
            return ExampleService.this;
        }
    }

    private MyBinder myBinder = new MyBinder();
}
