package com.lx.lock.reentant;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:使用tryLock()可以避免死锁
 * @Author: LinXin_
 * @CreateTime: 2021/4/18 13:07
 */
public class Test09 {
    static class IntLock implements Runnable {
        private static ReentrantLock lock1 = new ReentrantLock();
        private static ReentrantLock lock2 = new ReentrantLock();
        private int lockNum;        //用于控制锁的顺序

        public IntLock(int lockNum) {
            this.lockNum = lockNum;
        }

        @Override
        public void run() {

            if (lockNum % 2 == 0) {     //偶数先锁1，再锁2
                while (true) {
                    try {
                        if (lock1.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + "获得锁1，还想获得锁2");
                            Thread.sleep(new Random().nextInt(995));
                            try {
                                if (lock2.tryLock()) {
                                    System.out.println(Thread.currentThread().getName() + "获得锁1和锁2");
                                    return;     //结束run方法执行，即当前线程结束
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (lock2.isHeldByCurrentThread())
                                    lock2.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock1.isHeldByCurrentThread())
                            lock1.unlock();
                    }
                }
            } else {     //奇数先锁2，再锁1
                while (true) {
                    try {
                        if (lock2.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + "获得锁2，还想获得锁1");
                            Thread.sleep(new Random().nextInt(1000));
                            try {
                                if (lock1.tryLock()) {
                                    System.out.println(Thread.currentThread().getName() + "获得锁1和锁2");
                                    return;     //结束run方法执行，即当前线程结束
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (lock1.isHeldByCurrentThread())
                                    lock1.unlock();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock2.isHeldByCurrentThread())
                            lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        IntLock l1 = new IntLock(11);
        IntLock l2 = new IntLock(22);

        Thread t1 = new Thread(l1);
        Thread t2 = new Thread(l2);

        t1.start();
        t2.start();

        /*
            运行后，使用tryLock()尝试获得锁，不会傻傻的等待，通过循环不停的再次尝试，
            如果等待的时间足够长，线程总是会获得想要的资源
         */
    }
}
