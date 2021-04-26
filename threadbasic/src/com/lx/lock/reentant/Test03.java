package com.lx.lock.reentant;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:使用lock锁同步不同方法中的同步代码块
 * @Author: LinXin_
 * @CreateTime: 2021/4/17 15:40
 */
public class Test03 {
    static Lock lock = new ReentrantLock();     //定义锁对象

    public static void sm() {
        //经常在try代码块中获得Lock锁，在finally子句中释放锁
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " -- method1 -- " + System.currentTimeMillis());
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void sm2() {
        //经常在try代码块中获得Lock锁，在finally子句中释放锁
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " -- method2 -- " + System.currentTimeMillis());
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                sm2();
            }
        };
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r2).start();
        new Thread(r2).start();


    }
}
