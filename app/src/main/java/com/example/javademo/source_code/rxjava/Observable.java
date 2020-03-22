package com.example.javademo.source_code.rxjava;

/**
 * 观察者模式 :  被观察者 订阅 观察者
 *
 * 观察者场景 : 1 .A依赖于B的变化  2. 更改一个对象的时候需要同时连带改变其他对象
 *
 * 被观察者接口
 * 接口 : 不能被实例化，不能直接创建对象，因为接口里面只有抽象的方法，没有具体的功能。
 */
public interface Observable {

    /**
     * 被观察者内部核心方法
     */

    /**
     *  被观察者订阅观察者
     */
    public void registerObserver(Observer observer);

    /**
     * 被观察者移除观察者
     */
    public void removeObserver(Observer observer);

    /**
     *  核心方法
     *  当被观察者发生变化时 , 通知订阅的观察者
     *  方法内部调用观察者observer的 update()方法
     */

    public void notifyObserver();

}
