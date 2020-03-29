package com.example.javademo.java.design_patterns.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxJavaCreateDemo {
    /**
     * 第一步 创建被观察者(几种方式)
     */
    Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
            emitter.onNext("");
        }
    });

    Observable observable2 = Observable.just("","");

    /**
     * 第二步 创建观察者 (Subscribe也是观察者 , 和Flowable一起使用 )
     */
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String s) {
            //被观察者发射数据过来
        }

        @Override
        public void onError(Throwable e) {
            //框架异常
        }

        @Override
        public void onComplete() {
            //当不再有数据变化了
        }
    };

    /**
     * 第三步 被观察者订阅观察者:
     *
     * 这么设计是方便链式调用 处理线程切换的是被观察者 最终数据处理的onNext方法在观察者里
     */

    public void doRxJava(){
        observable1.subscribe(observer);
        observable2.subscribe(observer);
    }
}
