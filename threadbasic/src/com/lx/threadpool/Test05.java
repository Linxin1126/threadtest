package com.lx.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:监控线程池
 * @Author: LinXin_
 * @CreateTime: 2021/4/24 09:58
 */
public class Test05 {
    public static void main(String[] args) throws InterruptedException {
        //先定义一个任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getId() + " 编号的线程开始执行：" + System.currentTimeMillis());
                    Thread.sleep(10000);        //线程睡眠10秒，模拟任务执行时长
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //创建一个线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        //向线程池提交30个任务
        for (int i = 0; i < 30; i++) {
            threadPoolExecutor.submit(runnable);
            System.out.println("当前线程池核心线程数数量：" + threadPoolExecutor.getCorePoolSize() +
                    ",最大线程数：" + threadPoolExecutor.getMaximumPoolSize() +
                    ",当前线程池大小：" + threadPoolExecutor.getPoolSize() +
                    ",活动线程数量： " + threadPoolExecutor.getActiveCount() +
                    ",收到任务数：" + threadPoolExecutor.getTaskCount() +
                    ",完成任务数：" + threadPoolExecutor.getCompletedTaskCount() +
                    ",等待任务数：" + threadPoolExecutor.getQueue().size());
            TimeUnit.MILLISECONDS.sleep(500);
        }
        System.out.println("-------------------------");
        while (threadPoolExecutor.getActiveCount() > 0) {
            System.out.println("当前线程池核心线程数数量：" + threadPoolExecutor.getCorePoolSize() +
                    ",最大线程数：" + threadPoolExecutor.getMaximumPoolSize() +
                    ",当前线程池大小：" + threadPoolExecutor.getPoolSize() +
                    ",活动线程数量： " + threadPoolExecutor.getActiveCount() +
                    ",收到任务数：" + threadPoolExecutor.getTaskCount() +
                    ",完成任务数：" + threadPoolExecutor.getCompletedTaskCount() +
                    ",等待任务数：" + threadPoolExecutor.getQueue().size());
            Thread.sleep(1000);
        }
    }
}
