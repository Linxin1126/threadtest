package com.lx.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:多个Condition实现通知部分线程，使用更灵活
 * @Author: LinXin_
 * @CreateTime: 2021/4/18 14:57
 */
public class Test02 {
    static class Service {
        private ReentrantLock lock = new ReentrantLock();   //定义锁对象
        //定义两个Condition对象
        private Condition c1 = lock.newCondition();
        private Condition c2 = lock.newCondition();

        //定义方法，使用c1等待
        public void waitMethod1() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "--begin wait");
                c1.await();
                System.out.println(Thread.currentThread().getName() + "--signal");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        //定义方法，使用c2等待
        public void waitMethod2() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "--begin wait");
                c2.await();
                System.out.println(Thread.currentThread().getName() + "--signal");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethod1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethod2();
            }
        }).start();

        Thread.sleep(3000);     //main线程睡眠3秒钟

        service.lock.lock();
//        service.c1.signal();      //唤醒c1对象上的等待，c2对象上的等待依然继续等待
        service.c2.signal();
        service.lock.unlock();
    }
}
