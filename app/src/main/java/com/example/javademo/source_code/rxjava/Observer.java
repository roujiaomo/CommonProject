package com.example.javademo.source_code.rxjava;

/**
 * 观察者
 */
public interface Observer {
    /**
     *  更新观察者内部数据
     */
    public void update(String data);
}
