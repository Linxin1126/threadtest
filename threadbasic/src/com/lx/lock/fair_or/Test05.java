package com.lx.lock.fair_or;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:boolean hasQueuedThread(Thread thread)：查询参数指定的线程是否在等待获得锁；
 * boolean hasQueuedThreads()：查询是否还有线程在等待获得该锁；
 * @Author: LinXin_
 * @CreateTime: 2021/4/19 15:55
 */
public class Test05 {
    static ReentrantLock lock = new ReentrantLock();        //定义锁

    public static void waitMethod() {

        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得了锁");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁。。。");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                waitMethod();
            }
        };
        Thread[] threads = new Thread[5];       //定义线程数组
        //给线程数组的元素赋值，每个线程都调用waitMethod()方法，并启动线程
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(runnable);
            threads[i].setName("Thread -- " + i);
            threads[i].start();
        }
        Thread.sleep(3000);

        //判断数组中每个线程对象是否在等待获得锁
//        System.out.println(lock.hasQueuedThread(threads[0]));
//        System.out.println(lock.hasQueuedThread(threads[1]));
//        System.out.println(lock.hasQueuedThread(threads[2]));
//        System.out.println(lock.hasQueuedThread(threads[3]));
//        System.out.println(lock.hasQueuedThread(threads[4]));

        Thread.sleep(2000);

        //再次判断是否还有线程在等待获得该锁
//        System.out.println(lock.hasQueuedThreads());
    }
}
