package com.example.javademo.android.handler;


import android.os.Handler;
import android.os.Looper;

/**
 * handler运行机制模型
 * <p>
 * 工作流程:
 * <p>
 * Handler创建完毕后,
 * 当调用handler.send()或 post()方法时, (post()最终会调用send())
 * 会调用 MessageQueue的enqueueMessage()方法, 将这个消息放入消息队列MessageQueue中,
 * Looper发现有新消息时, Looper.loop循环查询有无新消息到来, 有消息处理完毕会调用handleMessage()方法,
 * 通常handler声明在主线程, 且默认的Looper所在的线程是和创建Handler的线程相同, 这样完成了线程切换.
 * <p>
 * 注:
 * 1.Looper.loop() 内才是处理我们Thread里逻辑的代码
 * 2.Handler如果在子线程创建 那么最后回调的 handleMessage()方法也在主线程
 * 可以手动声明Looper所在线程 ,见代码
 */
public class HandlerRunningModel {

    Handler handler = new Handler(Looper.getMainLooper());


}
