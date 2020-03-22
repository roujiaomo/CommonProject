package com.example.javademo.source_code.rxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者实现类
 *
 *  1. 正常逻辑上 ,整个流程为观察者订阅被观察者 , 被观察者给观察者发送消息
 *   一个被观察者可以被多个观察者订阅 , 所以在被观察者实现类里 使用 observerList管理全部观察者
 *  2. 由于链式调用
 *
 *  以上两点原因, 解释了实际开发过程中, 代码逻辑为被观察者订阅观察者的行为 .
 *
 */
public class ObservableImpl implements Observable {

    /**
     * 一个被观察者可能有多个观察者
     * (一场比赛有若干人看, 比赛是被观察者 , 人是观察者)
     * 因为 Observer是接口类 , 无法传入Observer对象 ,只能传入其实现类对象
     * 从而可以传入不同的Observer , 实现拓展性
     */
   private List<Observer> observerList ; //构造方法初始化

    public ObservableImpl() {
        observerList = new ArrayList<>();
    }

    //注册
    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int i = observerList.indexOf(observer);
        if(i>=0){
            observerList.remove(i);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observerList) {
            observer.update("被观察者发送消息了");
        }
    }
}
