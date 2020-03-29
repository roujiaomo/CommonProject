package com.example.javademo.java.thread.synchronize;

import com.example.javademo.java.LogUtils;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * synchronized 详解
 * 监视器的不同声明, 影响锁的范围
 * <p>
 * 底层原理:
 * 任何线程在获取monitor的第一时间会把共享内存里的数据复制到自己的缓存里,
 * 任何线程在释放monitor的第一时间会把自己的缓存里的数据复制进共享内存里.
 * 解释:(想想那三个图)
 * 共享内存: 即手机自己的内存区
 * 每一个线程有自己的一块内存区域: cpu高速缓存
 * cpu高速缓存里操作数据读写效率远大于共享内存
 * <p>
 * 如果不使用synchronized关键字:
 * 普通线程想修改共享内存里的数据的时候, 是先把共享内存里的资源写入线程,
 * 进行一系列读写操作, 再把数据写入共享内存, 这样一旦多线程访问, 数据就错乱了.
 * 使用synchronized关键字:
 * 数据读写都在共享内存内进行, 提升了安全性, 但是降低了性能
 *
 * 拓展: 静态锁(见单例模式), 死锁(下方) , 乐观锁 , 悲观锁 , 读写锁(下方)
 *
 * 乐观锁: 数据库分为读写, 读取时候不上锁, 但是写入的时候, 有上锁机制
 * 例如: 默认每次写入数据库时, 数据库会判断version值是否增长了1, 增长了可以写入, 否则锁住写入数据库的代码块.
 * A,B线程同时读该表修改数据, A线程提交数据后version增长了1 , 此时B并不知道A的操作, 也将version增长了1,
 * 提交到数据库的时候, B的version值和数据库的值一样, 则锁住写入数据库的代码块, 驳回请求.
 *
 * 悲观锁: 每次在数据库读数据都会有锁, 供单线程访问
 */
public class SynchronizePlus {

    private int count = 0;
    private final Object object = new Object();
    private String dataA,dataB; //死锁数据
    private int dataRW;//读写锁数据
    private final Object monitorA = new Object();//A监视器
    private final Object monitorB = new Object();//B监视器
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();//读锁
    ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();//写锁

    /**
     * 以下两种写法是完全相同的
     * 这两个方法monitor相同, 两个方法不同同时被多线程访问
     */
    public synchronized void setData() {
        //这里的synchronized为隐式声明, monitor为该类的对象
        count = 10;
    }

    public void setDataS() {
        synchronized (this) {
            //这里的synchronized所设置的monitor为this, 也是该类的对象
            count = 10;
        }
    }

    /**
     * 这种写法下的monitor与上面两种方法不同, 锁的范围不同 ,可以被多线程同时访问
     */
    public void set() {
        synchronized (object) {
            //这里的synchronized所设置的object, 即这个代码块下的资源是被object这个监视者监视的
            count = 10;
        }
    }

    /**
     * 静态锁 静态锁
     */
    public synchronized static void newInstanceA(){

    }

    public static void newInstanceB(){

    }

    /**
     * 死锁:
     *
     * 只有出现双锁才会有死锁
     * 如下面两个方法:
     * 当在一个线程中调用setDataA(), 当进行到 synchronized (monitorB)之前,
     * 在另一个线程中调用setDataB(), 这时先进入synchronized (monitorB)的可以正常运行,
     * 我们假设setDataB()先进入synchronized (monitorB)代码块, 而setDataA()中被synchronized (monitorB)锁住,
     * 当setDataB()执行到synchronized (monitorA), 就出现了互相等待, 互相锁住的情况,
     * 即死锁.
     */
    public void setDataA(String newValue){
        //锁住A
        synchronized (monitorA){
            dataA = newValue;
            //锁住A的同时锁之后B
            synchronized (monitorB){
                dataB = newValue;
            }
        }
    }

    public void setDataB(String newValue){
        //锁住B
        synchronized (monitorB){
            dataB = newValue;
            //锁住B的同时锁之后A
            synchronized (monitorA){
                dataA = newValue;
            }
        }
    }

    /**
     * 读写锁
     * 数据库读写场景
     * 应用: 多线程只可以一起读, 不可以一起进行其他操作(写写,写读,读写)
     */

    public void write(){//写
        writeLock.lock();
        try {
            dataRW++;
        }finally {
            writeLock.unlock();
        }
    }

    public void read(){//读
        readLock.lock();
        try {
            LogUtils.println(dataRW+"");
        }finally {
            readLock.unlock();
        }
    }
}
