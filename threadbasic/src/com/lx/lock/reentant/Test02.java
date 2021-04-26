package com.lx.lock.reentant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:Lock锁的基本使用
 * @Author: LinXin_
 * @CreateTime: 2021/4/17 15:26
 */
public class Test02 {
    //定义显示锁
    static Lock lock = new ReentrantLock();

    //定义方法
    public static void sm() {
        //先获得锁
        lock.lock();
        //for循环就是同步代码块
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
        }
        //释放锁
        lock.unlock();
    }

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
