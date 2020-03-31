package com.example.javademo.source_code.event_bus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusNote {

    public void registerEventBus() {

        /**
         * getDefault : 通过builder和单例创建
         *
         * register:
         * 1. 通过反射获取订阅者的class对象, 然后通过 SubscriberMethodFinder
         * 遍历查找该订阅者下订阅的方法, 如果缓存里有返回缓存里的, 如果没缓存, 就拿所有的
         * subscribe注解下的方法返回.
         * 2. 将返回的订阅的方法 和 订阅者绑定(class), 注: 返回的集合里的SubscriberMethod 对象包含了Event(对应post的Event)的类型
         * 3. 处理黏性事件, 将之前发送的黏性事件发送给黏性订阅者
         */
        EventBus.getDefault().register(this);
    }

    /**
     * post: 想象下最终是要将事件发送给订阅者的
     * post: 根据这个事件, 获取所有订阅它的订阅者集合，遍历集合，将事件发送给订阅者
     * <p>
     * 发送回主线程: 创建EventBus的时候会创建一个poster, 里边封装的是handler的逻辑
     * 发送回其他线程: 创建EventBus的时候会创建一个poster, 里边用的是线程池的机制
     */
    public void postEvent() {
        EventBus.getDefault().post(new MainEvent());
    }

    /**
     * 接收事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(MainEvent event) {

    }
}
