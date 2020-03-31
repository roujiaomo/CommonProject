package com.example.javademo.source_code.event_bus;

/**
 * 用于EventBus传递数据
 */
public class MainEvent {

    public int code;
    public String content;

    public MainEvent() {
    }
    public MainEvent(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
