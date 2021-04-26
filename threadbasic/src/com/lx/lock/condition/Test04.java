package com.lx.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:使用Condition实现生产者/消费者模式中的，多对多，即有多个线程打印---，多个线程打印*** 实现--与**的交替打印
 *      解决方法：将signal()方法改成signalAll()方法
 * @Author: LinXin_
 * @CreateTime: 2021/4/18 15:47
 */
public class Test04 {
    static class MyService {

        private Lock lock = new ReentrantLock();        //创建锁对象
        private Condition condition = lock.newCondition();     //创建Condition对象
        private boolean flag = true;        //定义一个交替打印的标志

        //定义方法只打印-----横线
        public void printOne() {
            try {
                lock.lock();        //锁定
                while (flag) {       //当flag为true时等待
                    System.out.println(Thread.currentThread().getName() + "waiting...");
                    condition.await();
                }
                //flag为false时打印
                System.out.println(Thread.currentThread().getName() + " ---------");
                flag = true;       //修改交替打印标志
                condition.signalAll();     //通知另外线程打印
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();      //释放锁
            }
        }

        //定义方法只打印*****横线
        public void printTwo() {
            try {
                lock.lock();        //锁定
                while (!flag) {       //当flag为false时等待
                    System.out.println(Thread.currentThread().getName() + "waiting...");
                    condition.await();
                }
                //flag为true时打印
                System.out.println(Thread.currentThread().getName() + " ********");
                flag = false;       //修改交替打印标志
                condition.signalAll();     //通知另外所有等待的线程打印
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();      //释放锁对象
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        myService.printOne();
                    }
                }
            }).start();
            Thread.sleep(500);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        myService.printTwo();
                    }
                }
            }).start();
        }
    }
}
