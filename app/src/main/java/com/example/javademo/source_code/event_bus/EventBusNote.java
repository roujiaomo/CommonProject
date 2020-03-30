package com.example.javademo.source_code.event_bus;

import org.greenrobot.eventbus.EventBus;

public class EventBusNote {

    public void registerEventBus() {

        /**
         * getDefault : 通过builder和单例创建
         *
         * register:
         * 1. 通过反射获取订阅者的class对象, 然后通过 SubscriberMethodFinder
         * 遍历查找该订阅者下订阅的方法, 如果缓存里有返回缓存里的, 如果没缓存, 就拿所有的
         * subscribe注解下的方法返回.
         * 2. 将返回的订阅的方法 和 订阅者绑定(class).
         * 3. 处理黏性事件, 将之前发送的黏性事件发送给黏性订阅者
         */
        EventBus.getDefault().register(this);
    }

}
