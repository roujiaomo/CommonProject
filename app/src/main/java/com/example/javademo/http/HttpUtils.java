package com.example.javademo.http;


import android.util.Log;

import com.example.javademo.http.config.BasicParamsInterceptor;
import com.example.javademo.http.config.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit2工具类
 */

public class HttpUtils {
    public static HttpUtils mRetorfitHelper = null;
    private Retrofit mRetrofit = null;
    private ApiService apiService = null;

    /**
     * 对Retrofit初始化
     * 设置塑性属性
     * 设置网络访问使用OkHttp3
     * 得到IRetrofitApi接口对象
     */
    private HttpUtils() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(UrlConfig.BASE_URL)
                .client(getSingletonInstance())
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(ResponseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 设置Okhttp3的相关拦截器
     *
     * @return Okhttp3
     */
    private OkHttpClient getSingletonInstance() {
        //打印网络日志
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("okHttp3", message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        BasicParamsInterceptor.Builder builder = new BasicParamsInterceptor.Builder();
        BasicParamsInterceptor basicParamsInterceptor = new BasicParamsInterceptor.Builder()
                .addHeaderParam("Cache-Control", "public,max-age=3600") //指定请求和响应遵循的缓存机制
                .addHeaderParam("Connection", "Keep-Alive") //表示是否需要持久连接。
                .addHeaderParam("Accept-Encoding", "gzip")//指定可以支持的web服务器返回内容压缩编码类型。
                .build();

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    public static HttpUtils getInstance() {
        if (mRetorfitHelper == null) {
            synchronized (HttpUtils.class) {
                if (mRetorfitHelper == null) {
                    mRetorfitHelper = new HttpUtils();
                }
            }
        }
        return mRetorfitHelper;
    }

    // 获得接口实例
    public ApiService getApiService() {
        if (apiService == null) {
            apiService = mRetrofit.create(ApiService.class);
        }
        return apiService;
    }
}
