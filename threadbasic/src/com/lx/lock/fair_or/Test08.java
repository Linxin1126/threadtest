package com.lx.lock.fair_or;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:boolean isLocked()  判断锁是否被线程持有
 * @Author: LinXin_
 * @CreateTime: 2021/4/19 17:17
 */
public class Test08 {
    static ReentrantLock lock = new ReentrantLock();

    static void sm() {
        try {
            System.out.println("before lock..."+lock.isLocked());
            lock.lock();
            System.out.println("after lock..."+lock.isLocked());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                sm();
            }
        });
        thread.start();

    }
}
