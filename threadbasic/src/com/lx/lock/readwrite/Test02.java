package com.lx.lock.readwrite;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:通过ReadWriteLock读写锁中的写锁，只允许有一个线程执行lock()后面的代码
 * @Author: LinXin_
 * @CreateTime: 2021/4/20 12:33
 */
public class Test02 {
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
    }

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.write();
                }
            }).start();
        }
        //从同一时间结果来看，同一时间只有一个线程获得写锁
    }
}
