package com.lx.lock.fair_or;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:boolean hasWaiters(Condition condition)：查询是否有线程正在等待指定的Condition条件
 * @Author: LinXin_
 * @CreateTime: 2021/4/19 16:20
 */
public class Test06 {
    static ReentrantLock lock = new ReentrantLock();    //创建锁对象
    static Condition condition = lock.newCondition();   //返回锁定的条件

    static void sm() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " lock...");
            System.out.println("是否有线程正在等待当前Condition条件？" + lock.hasWaiters(condition) + " -- waitQueuelength: " + lock.getWaitQueueLength(condition));
            condition.await(new Random().nextInt(1000), TimeUnit.MILLISECONDS);     //超时后会自动唤醒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"超时唤醒，是否有线程正在等待当前Condition条件？" + lock.hasWaiters(condition) + " -- waitQueuelength: " + lock.getWaitQueueLength(condition));
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };
        //开启十个线程，都调用sm()方法
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
