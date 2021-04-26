package com.lx.lock.reentant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:ReentrantLock锁的可重入性
 * @Author: LinXin_
 * @CreateTime: 2021/4/17 16:09
 */
public class Test04 {
    static class SubThread extends Thread {
        private static Lock lock = new ReentrantLock();        //定义锁对象
        public static int num = 0;      //定义变量

        @Override
        public void run() {

            for (int i = 0; i < 10000; i++) {
                try {
                    //可重入锁指可以反复获得该锁
                    lock.lock();
                    lock.lock();
                    num++;
                } finally {
                    //释放时也需要释放两次
                    lock.unlock();
                    lock.unlock();
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

        SubThread t1 = new SubThread();
        SubThread t2 = new SubThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(SubThread.num);

    }
}
