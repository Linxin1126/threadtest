package com.lx.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:Condition等待与通知
 * @Author: LinXin_
 * @CreateTime: 2021/4/18 14:27
 */
public class Test01 {
    //定义锁
    static Lock lock = new ReentrantLock();
    //获得Condition对象
    static Condition condition = lock.newCondition();

    //定义线程子类
    static class SubThread extends Thread{
        @Override
        public void run() {
            try {
                lock.lock();        //在调用await()前必须先获得锁
                System.out.println("method lock");
                condition.await();      //等待
                System.out.println("method await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();      //释放锁
                System.out.println("method unlock");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SubThread t1 = new SubThread();
        //子线程启动后，会转入等待状态
        t1.start();

        Thread.sleep(3000);
        //主线程在睡眠3秒后，唤醒子线程的等待
        try {
            lock.lock();
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}
