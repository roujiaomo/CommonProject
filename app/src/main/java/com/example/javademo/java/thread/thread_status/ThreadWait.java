package com.example.javademo.java.thread.thread_status;

import com.example.javademo.java.LogUtils;

/**
 * 线程的 wait(), notify() , notifyAll()
 * <p>
 * wait() : 等待, 本质上是一个插队动作, 插队完执行后, 去通知被插队的线程继续执行
 * notify() : 通知, 暂时被插队后, 如果还需要被插队的线程继续工作, 需要调用这个方法, 注: 调用一次之后通知一个线程
 * notifyAll() : 通知全部: 功能同notify, 可以通知全部需要通知的线程, 比较常用
 *
 * 注: 以上几种是monitor的方法, 不是thread的, 也仅有当资源被锁住的时候, 会设计这些操作
 */
public class ThreadWait {

    private static String value;

    public static void main(String[] args) {
        //这里做一个顺序不正常的操作, 即对象未初始化直接使用
        final ThreadWait threadWait = new ThreadWait();
        Thread readThread = new Thread() {
            @Override
            public void run() {
                threadWait.read();
            }
        };
        readThread.start();
        Thread writeThread = new Thread() {
            @Override
            public void run() {
                threadWait.write();
            }
        };
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writeThread.start();

    }

    //初始化
    private synchronized void write() {
        value = "初始化完成";
        notify();
    }

    /**
     * wait() 作用: 暂时释放锁, monitor将之前占用锁的线程暂时释放,
     * 此时锁住的数据资源可以被访问, 而先前的占用锁的线程会在monitor外排队,
     * 当操作执行完毕后, 必须调用notify()方法通知先前排队的线程序列可以继续进行了
     */
    private synchronized void read() {
        while (null == value) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LogUtils.println("value: " + value);
    }
}
