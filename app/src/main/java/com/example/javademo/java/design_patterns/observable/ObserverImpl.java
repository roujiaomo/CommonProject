package com.example.javademo.java.design_patterns.observable;


import com.example.javademo.java.LogUtils;

public class ObserverImpl implements Observer {
    private int notifyCount; //接收被观察者消息次数
    //当被观察者发送数据的时候
    @Override
    public void update(String data) {
        notifyCount++;
        LogUtils.println("观察者数据更新: " + data);
        LogUtils.println("接收被观察者消息次数: " + notifyCount);
    }

}
