package com.example.javademo.java.thread.thread_status;

import com.example.javademo.java.LogUtils;

/**
 * 线程的停止
 */
public class ThreadStop {
    public static void main(String[] args) {
        //stopThread();
        interruptThread();
    }

    /**
     * thread.stop() 暴力停止
     * 会在未知的时刻终止线程, 时间不可控, 即代码执行到哪未知, 不安全
     * 如下方例子, 终止线程时, 耗时操作1,2,3不一定终止在哪一步
     */
    public static void stopThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                //耗时操作1
                for (int i = 0; i < 100000; i++) {
                    LogUtils.println(i + "");
                }
                //耗时操作2
                //耗时操作3
            }
        };
        thread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop(); //thread.stop() 会在未知的时刻终止线程, 时间不可控
    }

    /**
     * thread.interrupt() 修改线程为中止状态
     * 配合线程内使用
     * 在线程内部可配置使用 isInterrupted() 或者 Thread.interrupted()
     * <p>
     * 两种内部配置区别:
     * 调用 isInterrupted()只获取当前线程是否中断的状态
     * 调用 Thread.interrupted() 获取当前线程中断状态 且将中断的状态清除 即下次调用默认不是中断的(中断状态返回false)
     * <p>
     * 如果普通中止线程 二者都可以满足 可使用: if(){ return;}的形式
     * 但是如果需要中断线程后继续工作 见下方代码
     * 所以 : Thread.interrupted()可完全取代 isInterrupted()
     * <p>
     * 注: 当线程处于wait(),sleep(),join()等空闲状态时, 调用 thread.interrupt()时, 需要在catch里做相应处理.
     */
    public static void interruptThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    /**
                     * 仅打断
                     */
                    if (Thread.interrupted()) {//打断
                        return;
                    }
                    if (!isInterrupted()) { //打断
                        return;
                    }
                    //做耗时操作
                    LogUtils.println(i + "");
                    /**
                     * 打断后继续
                     */
                    if (!Thread.interrupted()) {//第一次中断后,可继续执行循环
                        //做耗时操作
                        LogUtils.println(i + "");
                    }
                    if (!isInterrupted()) { //第一次中断后,不可继续执行循环
                        //做耗时操作
                        LogUtils.println(i + "");
                    }
                }
                /**
                 * 注: 如果线程内有Sleep操作,在外部调用 thread.interrupt()时,
                 *  如果标记打断的时刻发生在sleep的时间, 则需要再catch里 return,
                 *  因为抛出异常了, 代表中断操作未生效, 此时需要手动中止
                 *
                 *  可使用 SystemClock.sleep(1000) 代替
                 */
                //
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

}
