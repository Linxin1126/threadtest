package com.lx.wait;

/**
 * @Description:
 *      需要通过notify()唤醒等待的线程
 * @Author: LinXin_
 * @CreateTime: 2021/4/15 21:44
 */
public class Test03 {
    public static void main(String[] args) throws InterruptedException {
        String lock = "lock";   //定义一个字符串作为锁对象
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("线程1开始等待：" + System.currentTimeMillis());
                    try {
                        lock.wait();    //线程等待，会释放锁对象，当前线程转入blocked阻塞状态
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程1结束等待：" + System.currentTimeMillis());
                }
            }
        });

        //定义第二个线程，在第二个线程中唤醒第一个线程
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //notify()方法也需要在同步代码块中，由锁对象调用
                synchronized (lock) {
                    System.out.println("线程2开始唤醒：" + System.currentTimeMillis());
                    lock.notify();  //唤醒在lock锁对象上等待的某一个线程
                    System.out.println("线程2结束唤醒：" + System.currentTimeMillis());
                }
            }
        });
        t1.start();     //t1线程开启，t1线程等待
        Thread.sleep(3000);     //main线程睡眠3秒，确保t1入睡
        t2.start();     //t1线程开启3秒后，再开启t2线程唤醒t1线程
    }
}
