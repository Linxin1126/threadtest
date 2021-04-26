package com.lx.lock.readwrite;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:演示ReadWriteLock的读写互斥：一个线程获得读锁时，写线程等待；一个线程获得写锁时，其他线程也是等待
 * @Author: LinXin_
 * @CreateTime: 2021/4/20 12:33
 */
public class Test03 {
    static class Service {
        static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void write() {
            try {
                readWriteLock.writeLock().lock();
                System.out.println(Thread.currentThread().getName() + "--获得写锁，时间：" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.writeLock().unlock();
                System.out.println(Thread.currentThread().getName() + "--释放写锁，时间：" + System.currentTimeMillis());
            }
        }

        public void read() {
            try {
                readWriteLock.readLock().lock();
                System.out.println(Thread.currentThread().getName() + "--获得读锁，时间：" + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
                System.out.println(Thread.currentThread().getName() + "--释放读锁，时间：" + System.currentTimeMillis());
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();

        //定义一个线程读数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.read();
            }
        }).start();

        //定义一个线程写数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.write();
            }
        }).start();
    }
}
