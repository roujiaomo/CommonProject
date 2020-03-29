package com.example.javademo.java.design_patterns.observable;

public class Test {
    public static void main(String[] args) {
        ObservableImpl observable = new ObservableImpl();//被观察者
        //观察者
        Observer observer1 = new ObserverImpl();
        Observer observer2 = new ObserverImpl();
        Observer observer3 = new ObserverImpl();
        observable.registerObserver(observer1);
        observable.registerObserver(observer2);
        observable.registerObserver(observer3);
        observable.notifyObserver();
    }
}
