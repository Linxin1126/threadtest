package com.lx.lock.reentant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:LockInterruptibly()的作用：如果当前线程未被中断则获得锁，如果当前线程被中断则出现异常。
 * @Author: LinXin_
 * @CreateTime: 2021/4/17 16:49
 */
public class Test05 {
    static class Service {
        private Lock lock = new ReentrantLock();        //定义锁对象

        public void serviceMethod() {
            try {
//                lock.lock();        //获得锁,即使调用了线程的interrupt()方法，也没有真正的中断这个线程
                lock.lockInterruptibly();   //如果线程被中断了，不会获得锁，会产生异常
                System.out.println(Thread.currentThread().getName() + " -- begin lock");
                //执行一段耗时的操作
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    new StringBuffer();
                }
                System.out.println(Thread.currentThread().getName() + " -- end lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " *****释放锁");
                lock.unlock();      //释放锁
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.serviceMethod();
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        Thread.sleep(50);
        t2.start();
        Thread.sleep(50);
        t2.interrupt();     //中断t2线程

    }
}
