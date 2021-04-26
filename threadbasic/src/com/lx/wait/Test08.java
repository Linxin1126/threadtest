package com.lx.wait;

/**
 * @Description:
 *      notify()通知过早问题
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 12:10
 */
public class Test08 {
    public static void main(String[] args) {
        final Object lock = new Object();       //定义对象作为锁对象
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println("begin wait");
                        lock.wait();
                        System.out.println("end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("begin notify");
                    lock.notify();
                    System.out.println("end notify");
                }
            }
        });

        //如果先开启t1线程,再开启t2线程，大多数情况下，t1先等待，t2再把t1唤醒
        //t1.start();
        //t2.start();

        //如果先开启t2通知线程,再开启t1等待线程，可能会出现t1等待线程没有收到通知的情况，
        //即t1不会被唤醒
        t2.start();
        t1.start();

    }

}
