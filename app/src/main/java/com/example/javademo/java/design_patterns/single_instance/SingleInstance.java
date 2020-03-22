package com.example.javademo.java.design_patterns.single_instance;

import com.example.javademo.java.design_patterns.factory.SampleInterface;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 单例模式
 * <p>
 * 懒汉 饿汉 双重锁 枚举等方式
 */
public class SingleInstance {
}

/**
 * 懒汉: 类加载时 就已经new好了 当对象所占资源不大时可用
 */
class Sample1Instance {
    private static Sample1Instance sample1Instance = new Sample1Instance();

    public static Sample1Instance getInstance() {
        return sample1Instance;
    }
}

/**
 * 饿汉: 需要的时候再创建对象
 * <p>
 * 线程不安全 多个线程同时new
 */
class Sample2Instance {
    //老版本Java要加volatile关键字: 禁止指令重排序
    private static volatile Sample1Instance sample2Instance;

    public static Sample1Instance getInstance() {
        if (null != sample2Instance) {
            //上锁 注意:此处为类级别的锁 因为锁的机制是防止多线程
            //如果是对象级别的锁 那么在其他对象获取单例时, 锁不住, 仍然新建对象
            synchronized (Sample2Instance.class) {
                sample2Instance = new Sample1Instance();
            }
        }
        return sample2Instance;
    }
}



/**
 * 枚举
 */
class Sample3Instance {
}
enum SampleEnum {
    INSTANCE;
    private Sample3Instance sample3Instance;
    SampleEnum() {
        sample3Instance = new Sample3Instance();
    }
}