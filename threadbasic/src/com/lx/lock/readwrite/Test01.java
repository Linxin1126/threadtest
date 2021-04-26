package com.lx.lock.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:ReadWriteLock读写锁可以实现多个线程同时读取共享数据，即读读共享，可以提高程序读取数据的效率
 * @Author: LinXin_
 * @CreateTime: 2021/4/20 10:27
 */
public class Test01 {
    static class Service {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        //读取数据
        public void read() {
            try {
                readWriteLock.readLock().lock();
                System.out.println(Thread.currentThread().getName()+"获得读锁，开始读取数据的时间--"+System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);  //模拟读取数据用时

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
                System.out.println(Thread.currentThread().getName()+"释放读锁");
            }
        }

    }

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.read();
                }
            }).start();
        }
        //运行程序后，这多个线程几乎可以同时获得锁，执行lock()后面的代码，
    }
}
