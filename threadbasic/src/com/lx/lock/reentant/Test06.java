package com.lx.lock.reentant;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:通过ReentrantLock锁的lockInterruptibly()方法避免死锁的产生
 * @Author: LinXin_
 * @CreateTime: 2021/4/17 17:23
 */
public class Test06 {
    static class IntLock implements Runnable {
        //创建两个ReentrantLock锁对象
        private static ReentrantLock lock1 = new ReentrantLock();
        private static ReentrantLock lock2 = new ReentrantLock();
        int lockNum;        //定义整数变量，决定使用哪个锁

        public IntLock(int lockNum) {
            this.lockNum = lockNum;
        }

        @Override
        public void run() {
            try {
                if (lockNum % 2 == 1) {     //奇数，先锁1再锁2
//                    lock1.lock();
                    lock1.lockInterruptibly();//如果线程正常运行就获得锁，如果程序中断，不会获得锁，会产生异常
                    System.out.println(Thread.currentThread().getName() + "获得锁1，还需要获得锁2");
                    Thread.sleep(new Random().nextInt(500));
//                    lock2.lock();
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "同时获得了锁1与锁2。。。");
                } else {        //偶数，先锁2再锁1
//                    lock2.lock();
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁2，还需要获得锁1");
                    Thread.sleep(new Random().nextInt(500));
//                    lock1.lock();
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "同时获得了锁1与锁2。。。");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread())  //判断当前线程是否持有该锁
                    lock1.unlock();
                if (lock2.isHeldByCurrentThread())
                    lock2.unlock();
                System.out.println(Thread.currentThread().getName() + "线程退出");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        IntLock l1 = new IntLock(11);
        IntLock l2 = new IntLock(22);

        Thread t1 = new Thread(l1);
        Thread t2 = new Thread(l2);
        t1.start();
        t2.start();

        //在main线程，等待3000秒，如果线程还没有结束就中断该线程
        Thread.sleep(3000);

        //可以中断任何一个线程来解决死锁
//        if (t1.isAlive()) {
//            t1.interrupt();
//        }
        //t2会放弃对锁1的申请，同时释放锁2，t1线程会完成它的任务
        if (t2.isAlive()) {
            t2.interrupt();
        }
    }
}
