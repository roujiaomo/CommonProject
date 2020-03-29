package com.example.javademo.java.thread.synchronize;


import com.example.javademo.java.LogUtils;

/**
 * Synchronize关键字
 * <p>
 * 背景: 多线程在切换时, 可以在任意原子操作范围外切换, 一个在写资源未完成的时候, 切换了线程, 数据出了问题
 * 原理: 使用Synchronize声明的对象或代码块等, 本质上是给声明的部分加一个监视器 Monitor, 此时同一时间只可以被一个线程访问,
 * 作用和理解:
 * 保护的不是方法, 是方法体里操作的资源, 给资源设置访问限制, 即监视资源的数据同步.
 *
 * <p>
 * 原子操作 : 一行代码不代表不可以被切断线程, 不会被切断的操作,cpu级别的一行代码叫做原子操作
 */
public class SynchronizeSample {
    private  int x, y = 0;

    //普通方法
    private  void setData(int data) {
        x = data; //如果线程在此时切换, 则y并未被赋值, 在这一刻, x!=y
        y = data;
        if (x != y) {//报黄说明代码层面有问题
            LogUtils.println("普通方法------>" + x);
            LogUtils.println("普通方法------>" + y);
        }
    }

    //普通方法使用synchronized关键字: 上锁, 同一时间只可以被一个线程访问
    private  synchronized void setDataBySynchronize(int data) {
        x = data;
        y = data;
        if (x != y) {
            LogUtils.println("synchronized------>" + x);
            LogUtils.println("synchronized------>" + y);
        }
    }

    //原子操作解释() 使用synchronized可以解决
    private  void setDataByCpu() {
        x++;
        //上面这一行代码 可以拆分为:
        int count = x;//即线程可以在这个地方切换
        x = count + 1;
    }

    private  void setData() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 1; i < 50000; i++) {
                    setData(i);
                    setDataBySynchronize(i);
                    setDataByCpu();
                }

            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                for (int i = 1; i < 50000; i++) {
                    setData(i);
                    setDataBySynchronize(i);
                    setDataByCpu();
                }
            }
        }.start();
    }

}
