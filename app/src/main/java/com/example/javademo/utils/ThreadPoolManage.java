package com.example.javademo.utils;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 */
public class ThreadPoolManage {

    private HashMap<String, ThreadPoolExecutor> threadPoolMap = new HashMap<>();

    /**
     * cpu数量
     */
    private int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /**
     * 核心线程数为手机CPU数量+1
     */
    private int CORE_POOL_SIZE = CPU_COUNT + 1;

    /**
     * 最大线程数为手机CPU数量×2+1
     */
    private int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;

    /**
     * 线程活跃时间 秒，超时线程会被回收
     */
    private long KEEP_ALIVE_TIME = 3;

    /**
     * 等待队列大小
     */
    private int QUEUE_SIZE = 128;

    private static ThreadPoolManage instance;
    private static ThreadPoolExecutor threadPoolExecutor;

    public static ThreadPoolManage getInstance() {
       if(instance ==null){
           instance = new ThreadPoolManage();
       }
        return instance;
    }


    /**
     * @ corePoolSize    线程池中核心线程的数量
     * @ maximumPoolSize 线程池中最大线程数量
     * @ keepAliveTime   非核心线程的超时时长，
     * 当系统中非核心线程闲置时间超过keepAliveTime之后，则会被回收
     *  如果ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true，则该参数也表示核心线程的超时时长
     * @ unit            第三个参数的单位，有纳秒、微秒、毫秒、秒、分、时、天等
     * @ queueSize       等待队列的长度 一般128 (参考 AsyncTask)
     *  workQueue 线程池中的任务队列，该队列主要用来存储已经被提交但是尚未执行的任务。存储在这里的任务是由ThreadPoolExecutor的execute方法提交来的。
     *  threadFactory  为线程池提供创建新线程的功能，这个我们一般使用默认即可
     * <p>
     * 1.ArrayBlockingQueue：这个表示一个规定了大小的BlockingQueue，ArrayBlockingQueue的构造函数接受一个int类型的数据，
     * 该数据表示BlockingQueue的大小，存储在ArrayBlockingQueue中的元素按照FIFO（先进先出）的方式来进行存取。
     * 2.LinkedBlockingQueue：这个表示一个大小不确定的BlockingQueue，在LinkedBlockingQueue的构造方法中可以传
     * 一个int类型的数据，这样创建出来的LinkedBlockingQueue是有大小的，也可以不传，不传的话，
     * LinkedBlockingQueue的大小就为Integer.MAX_VALUE
     */
    private ThreadPoolExecutor getThreadPool(){
        if (threadPoolExecutor == null) {
            threadPoolExecutor = new ThreadPoolExecutor(
                    CORE_POOL_SIZE,
                    MAXIMUM_POOL_SIZE,
                    KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(QUEUE_SIZE),
                    Executors.defaultThreadFactory(),
                    new RejectedExecutionHandler() {
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

                        }
                    }
            );
            //允许核心线程闲置超时时被回收
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        }
        return threadPoolExecutor;
    }


    /**
     * @param runnable 对应的 runnable 任务
     */
    public void  removeTask(Runnable runnable) {
        getThreadPool().getQueue().remove(runnable);
    }

    /**
     * @param runnable 对应的 runnable 任务
     */
    public void addTask(Runnable runnable) {
        getThreadPool().execute(runnable);
    }

    /**
     *            取消 移除线程池
     */

    //shutDown()：关闭线程池后不影响已经提交的任务
    //shutDownNow()：关闭线程池后会尝试去终止正在执行任务的线程
    public void  exitThreadPool() {
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdownNow();
        }
    }
}
