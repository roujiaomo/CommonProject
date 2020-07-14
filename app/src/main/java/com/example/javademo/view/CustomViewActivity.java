package com.example.javademo.view;

import android.util.Log;
import android.view.View;

import com.example.javademo.R;
import com.example.javademo.base.BaseActivity;
import com.example.javademo.databinding.ActivityCustomViewBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CustomViewActivity extends BaseActivity<ActivityCustomViewBinding> {

    private static final String TAG = "CustomViewActivity";
    private static final int PERIOD = 10 * 1000;
    private static final int DELAY = 100;
    private Disposable mDisposable;


    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_view;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        mBinding.customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"点击了"+ i );
                timeLoop1();
            }
        });

    }

    private String getData(){
        return "数据来了";
    }


    int i = 0;
    //https://www.jianshu.com/p/6a89413f2ab1
    private void timeLoop1() {
        Observable.just(getData()).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {

                                // 加入判断条件：当轮询次数 = 5次后，就停止轮询
                                if (i > 3) {
                                    Log.d(TAG, "repeatWhen: "+i);
                                    // 此处选择发送onError事件以结束轮询，因为可触发下游观察者的onError（）方法回调
                                    return Observable.error(new Throwable("轮询结束"));
                                }
                                Log.d(TAG, "repeatWhen: "+i);
                                // 若轮询次数＜4次，则发送1Next事件以继续轮询
                                // 注：此处加入了delay操作符，作用 = 延迟一段时间发送（此处设置 = 2s），以实现轮询间间隔设置
                                return Observable.just(getData()).delay(2000, TimeUnit.MILLISECONDS);
                            }
                        });

                    }
                }).subscribe(new Observer<String>() {
                 @Override
                 public void onSubscribe(Disposable d) {
                     Log.d(TAG, "onSubscribe: ");
                 }

                 @Override
                 public void onNext(String s) {
                     i++;
                     Log.d(TAG, "onNext: "+s);
                 }

                 @Override
                 public void onError(Throwable e) {
                     Log.d(TAG, "onError: ");
                 }

                 @Override
                 public void onComplete() {
                     Log.d(TAG, "onComplete: ");
                 }
             });
    }
}
