package com.lx.lock.reentant;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:tryLock(long time, TimeUnit unit)的基本使用
 * @Author: LinXin_
 * @CreateTime: 2021/4/18 11:59
 */
public class Test07 {
    static class TimeLock implements Runnable {
        private static ReentrantLock lock = new ReentrantLock();       //定义一个锁对象

        @Override
        public void run() {
            try {
                if (lock.tryLock(3, TimeUnit.SECONDS)) {      //获得锁，返回true
                    System.out.println(Thread.currentThread().getName() + "获得锁，执行耗时任务");
                    Thread.sleep(3000);
                    //假设Thread-0线程先拿到锁，它完成任务需要3秒钟,Thread-1线程尝试获得锁，
                    //若在三秒内还未获得锁，就会放弃
                } else {     //没有获得锁
                    System.out.println(Thread.currentThread().getName() + "没有获得锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();

        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);

        t1.start();
        t2.start();
    }
}
