package com.example.javademo.java.design_patterns.single_instance;

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
    private static Sample2Instance sample2Instance;

    public static Sample2Instance getInstance() {
        //上锁 注意:此处为类级别的锁 因为锁的机制是防止多线程
        //如果是对象级别的锁 那么在其他对象获取单例时, 锁不住, 仍然新建对象
        synchronized (Sample2Instance.class) {
            if (null == sample2Instance) {
                sample2Instance = new Sample2Instance();
            }
        }
        return sample2Instance;
    }
}

/**
 * 饿汉 + 双重检测
 * <p>
 * 多线程安全 性能优化
 */
class Sample3Instance {
    //老版本Java要加volatile关键字: 在初始化完成过程中, 其他线程不可以访问该对象(引用)
    // 场景:
    // 在一个线程中访问getInstance(),当对象在new的过程中, 在未初始化完全的时候, 所创建的对象对外暴露有可能不是null
    // 这时另一个线程访问getInstance(), 外层的判断null == sample3Instance 不成立, 此时对象还未初始化完全,则会报错
    private static volatile Sample3Instance sample3Instance;

    public static Sample3Instance getInstance() {
        if (null == sample3Instance) {
            //上锁 注意:此处为类级别的锁 因为锁的机制是防止多线程
            //如果是对象级别的锁 那么在其他对象获取单例时, 锁不住, 仍然新建对象
            synchronized (Sample2Instance.class) {//其他线程访问这个类的这个或其他synchronized修饰的方法也会被锁住
                if (null == sample3Instance) {
                    sample3Instance = new Sample3Instance();
                }
            }
        }
        return sample3Instance;
    }
}

/**
 * 枚举
 */
class Sample4Instance {
}

enum SampleEnum {
    INSTANCE;
    private Sample4Instance sample4Instance;

    SampleEnum() {
        sample4Instance = new Sample4Instance();
    }
}