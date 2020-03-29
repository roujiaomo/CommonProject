package com.example.javademo.java.thread.thread_status;
/**
 * Thread的join(),yield()
 * join() : 见下方代码
 * yield() : 因为线程的竞争时间片机制, yield()是让出当前这次抢到的执行时间, 仅一次
 */
public class ThreadJoin {
    private void doThread() {
       final Thread threadA = new Thread() {
            @Override
            public void run() {
                Thread.yield();//让出这次的时间
                setValue();
            }
        };
        threadA.start();
        Thread threadB = new Thread() {
            @Override
            public void run() {
                try {
                    //暂时不执行该线程, 等threadA全部执行完后, 再执行该线程
                    threadA.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
//                    return;当有interrupt操作需加上
                }
                setValue();
            }
        };

        threadB.start();

    }

    private void setValue(){

    }
}
