package com.db.commonlibrary.utils;

import android.util.Log;


import com.db.commonlibrary.constant.ErrorStatus;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

public class NetExceptionUtil {
    private static  String errorMsg = "请求失败，请稍后重试" ;
    public static  int  errorCode = 0 ;
    private static final String TAG = "NetExceptionUtil";

    public static String handlerException(Throwable e){
        if (e instanceof ConnectException || e instanceof UnknownHostException) {
            errorCode = ErrorStatus.NETWORK_ERROR;
            errorMsg = "网络连接异常 , 请稍后重试 ";
        } else if (e instanceof SocketTimeoutException) {
            errorCode = ErrorStatus.NETWORK_ERROR;
            errorMsg = "请求网络超时 , 请稍后重试";
        } else if(e instanceof IllegalArgumentException){
            errorCode = ErrorStatus.SERVER_ERROR;
            errorMsg = "参数错误 , 请稍后重试";
        } else if (e instanceof HttpException) {
            errorCode = ErrorStatus.SERVER_ERROR;
            HttpException httpException = (HttpException) e;
            errorMsg = convertStatusCode(httpException);
        } else if (e instanceof ParseException || e instanceof JSONException
                || e instanceof JSONException) {
            errorCode = ErrorStatus.SERVER_ERROR;
            errorMsg = "数据解析错误 , 请稍后重试";
        }else {

        }
        Log.e(TAG, "网络请求异常: "+  errorMsg);
        return errorMsg;
    }

    private static String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() >= 500 && httpException.code() < 600) {
            msg = "服务器处理请求出错 , 请稍后重试 ";
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            msg = "服务器无法处理请求 , 请稍后重试";
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            msg = "请求失败，请稍后重试";
        } else {
            msg = httpException.message();
        }
        return msg;
    }
}
