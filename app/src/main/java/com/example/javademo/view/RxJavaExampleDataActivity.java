package com.example.javademo.view;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.javademo.R;
import com.example.javademo.base.BaseDataActivity;
import com.example.javademo.base.BaseResponse;
import com.example.javademo.bean.TranslationBean;
import com.example.javademo.bean.WeatherBean;
import com.example.javademo.databinding.ActivityRxjavaExampleBinding;
import com.example.javademo.http.HttpUtils;
import com.example.javademo.model.NewsModel;
import com.example.javademo.viewmodel.NewsViewModel;
import com.example.javademo.widget.LoadStatusLiveData;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaExampleDataActivity extends BaseDataActivity<NewsViewModel , ActivityRxjavaExampleBinding> implements View.OnClickListener {

    private static final String TAG = "RxJavaExampleActivity";
    @Override
    public int getLayoutId() {
        return R.layout.activity_rxjava_example;
    }

    @Override
    public void initView() {
        mBinding.setOnClickListener(this);
    }

    @Override
    public void initData() {
        initSearch();
    }

    @Override
    public void initLiveDataObserve() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_click:
                doClick();
                break;
            case R.id.btn_round:
                doHttp();
                break;
            case R.id.btn_zip_http:
                doZipHttp();
                break;

        }
    }

    /**
     * 防止多次点击
     */
    private void doClick() {
        RxView.clicks( findViewById(R.id.btn_click))
                .throttleFirst(2, TimeUnit.SECONDS)  // 才发送 2s内第1次点击按钮的事件
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Object value) {
                        Log.d(TAG, "发送了网络请求" );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应" + e.toString());
                        // 获取异常错误信息
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });


    }

    private NewsModel newsModel = new NewsModel();
    boolean flag = true;
    private void doHttp() {
        newsModel.getNews().repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                if(flag){
                    return Observable.error(new Throwable("轮询结束"));
                }else {//再轮询一次
                    return Observable.just(1).delay(2000, TimeUnit.MILLISECONDS);
                }
            }
        }) .subscribeOn(Schedulers.io())               // 切换到IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  // 切换回到主线程 处理请求结果
                .subscribe(new Observer<BaseResponse<TranslationBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<TranslationBean> translationBeanBaseResponse) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    /**
     * 防止连续输入多次调用后台
     */
    private void initSearch() {
        RxTextView.textChanges( (EditText)findViewById(R.id.et_search))
                .debounce(1, TimeUnit.SECONDS).skip(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(CharSequence charSequence) {
                        Log.d(TAG, "输入字符变化 , 搜索");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应" );
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

    }


    WeatherBean weatherBean11 = null;
    WeatherBean weatherBean12 = null;
    public LoadStatusLiveData loadStatusLiveData = LoadStatusLiveData.getInstance();
    private void doZipHttp(){
        Observable<WeatherBean> ob1 = HttpUtils.getInstance().getApiService().
                getWeather("武汉","3ee5b1f1af26cb1baab51209970eac46").subscribeOn(Schedulers.newThread());

        Observable<WeatherBean> ob2 = HttpUtils.getInstance().getApiService().
                getWeather("上海","3ee5b1f1af26cb1baab51209970eac46").subscribeOn(Schedulers.newThread());

        Observable.zip(ob1, ob2, new BiFunction<WeatherBean, WeatherBean, Object>() {
            @Override
            public Object apply(WeatherBean weatherBean, WeatherBean weatherBean2) throws Exception {
                //全部成功
                weatherBean11 = weatherBean;
                weatherBean12 = weatherBean2;
                Log.d(TAG, "apply1: " + weatherBean.getResult().getSk().getWind_direction());
                Log.d(TAG, "apply1: " + weatherBean2.getResult().getSk().getWind_direction());
                return new Object();
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                //全部成功
                Log.d(TAG, "onNext: " + "请求成功");
                Log.d(TAG, "onNext: " + weatherBean11.getResult().getSk().getWind_direction());
                Log.d(TAG, "onNext: " + weatherBean12.getResult().getSk().getWind_direction());
            }

            @Override
            public void onError(Throwable e) {
                //有一个失败
                Log.d(TAG, "onError: " + "请求错误" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
