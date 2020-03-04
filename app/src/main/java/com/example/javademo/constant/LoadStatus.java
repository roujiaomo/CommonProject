package com.example.javademo.constant;

public class LoadStatus {

    public static final int  STATUS_CONTENT = 0x00;
    public static final int STATUS_LOADING = 0x01; //加载中...
    public static final int STATUS_UPLOADING = 0x02; //上传中...
    public static final int STATUS_REQUEST = 0x03; //正在请求中...
    public static final int STATUS_EMPTY = 0x04;
    public static final int STATUS_ERROR = 0x05; //服务器错误
    public static final int STATUS_NO_NETWORK = 0x06; //网络错误
}
