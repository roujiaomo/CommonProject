package com.example.javademo.java.thread.thread_status;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程创建
 * <p>
 * 进程: 进程间资源不共享 一个进程含有多个线程同时在进行 (一个运行的程序)
 * 线程: 线程间共享资源 (程序中进行的线路)
 * cpu线程: 四核: Cpu在硬件级别可以同时做 4 件事情
 * 操作系统线程:  模仿cpu线程 时间片竞争 事情竞争去做 做完A做B
 * <p>
 * 多线程: 从cpu硬件级别 几核就是可以同时做几件事 但是从操作系统而言 单核也可以同时做几件事情(感观上)
 * <p>
 * Android的UI线程 是在执行死循环, 直到操作系统资源不足时候kill进程
 */
public class ThreadCreate {

    public static void main(String[] args) {
    }

    /**
     * 创建一个Thread 直接new
     */
    private void thread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
            }
        };
        thread.start();
    }

    /**
     * 将runnable作为target参数传进去, 在thread的run方法里 target.run();
     */
    private void runnable() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();
    }

    /**
     * 线程池执行runnable
     * <p>
     * Executor
     * 暂记两个方法:
     * shutdown: 缓和的, 不允许添加新的任务, 但是会执行完现有任务再结束
     * shutdownNow: 不允许添加新的任务, 并杀掉当前所有的线程, 通过interrupt()方式
     */
    private void execute() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        //普通常用需求
        Executor executor = Executors.newCachedThreadPool();//最小0个 最大..会崩
        executor.execute(runnable);
        //当如果需要一次性创建很多线程并当线程运行完就不再使用的时候(提升并行效率)
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }

    /**
     * Callable 与Future成对使用
     * Callable: 类似有返回值的Runnable
     */
    private void callable() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call()  {
                try {
                    Thread.sleep(1500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return "后台的事做完了";
            }
        };
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(callable);
        try {
            //这里是阻塞的 future.get()会在callable的call()方法执行完之后再返回结果
            while (future.isDone()){ //当call事件走完
                String result = future.get();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
