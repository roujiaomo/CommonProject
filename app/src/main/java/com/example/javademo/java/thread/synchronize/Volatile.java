package com.example.javademo.java.thread.synchronize;

import java.util.concurrent.atomic.AtomicLong;

/**
 *  一个轻量级的synchronized锁, 单例模式会用到
 *  应用于基础数据类型(double和long比较特殊)和对象引用
 *  (针对原子操作)
 *  理解: volatile作用在初始化完成过程中, 其他线程不可以访问该对象(引用)
 *
 *  拓展:  AtomicLong针对于原子操作的数据类型
 */
public class Volatile {

    /**
     * 这样声明基础数据类型, 该数据被多线程访问时,数据是同步的
     * 以下两种方法, 作用相同
     * 注: 对于double和long , Java定义这两种类型的赋值可以不是原子操作
     * 使用volatile声明后, 可以变成原子操作.
     */
    volatile int count = 0;

    public void setCount(int count) {
        this.count = count;
    }

    public synchronized void setCountS(int count) {
        this.count = count;
    }

    /**
     * 对于String和其他对象类型
     */
    volatile SynchronizePlus synchronizePlus ;

    public void init() {
        //volatile可以保证这个阶段多线程无法同时访问
        synchronizePlus = new SynchronizePlus();
    }

    public void setObjData(){
        //volatile无法锁住多线程同时修改其属性的操作
        synchronizePlus.setDataA("");
    }

    public synchronized void setObjDatabyS(){
        //修改属性 只能加锁
        synchronizePlus.setDataA("");
    }

    /**
     * 拓展
     * 使用这种数据类型 即使 atomicLong++ 也是原子操作
     */
   volatile AtomicLong atomicLong = new AtomicLong(1);

}
