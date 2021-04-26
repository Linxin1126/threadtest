package com.lx.lock.fair_or;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:isFair() 判断是否为公平锁
 * boolean isHeldByCurrentThread()判断锁是否被当前线程持有
 * @Author: LinXin_
 * @CreateTime: 2021/4/19 16:57
 */
public class Test07 {
    static class Service {
        private ReentrantLock lock;

        //通过构造方法接受一个布尔值，来判断当前线程是否公平
        public Service(boolean isFair) {
            this.lock = new ReentrantLock(isFair);
        }

        public void serviceMethod() {
            try {
                System.out.println("是否是公平锁：" + lock.isFair() + " -- " + Thread.currentThread().getName() + "调用lock()前是否持有锁？ " + lock.isHeldByCurrentThread());
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "调用lock()方法后是否持有锁？" + lock.isHeldByCurrentThread());
            } finally {
                if (lock.isHeldByCurrentThread()) {     //如果锁对象被当前线程持有就释放锁
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int num = new Random().nextInt();
                new Service(num % 2 == 0 ? true : false).serviceMethod();
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(runnable, "thread-" + i).start();
        }
    }
}
