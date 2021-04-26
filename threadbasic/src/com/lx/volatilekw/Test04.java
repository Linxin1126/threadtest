package com.lx.volatilekw;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 *      使用原子类进行自增
 * @Author: LinXin_
 * @CreateTime: 2021/4/13 14:10
 * @Company: 人生无限公司
 */
public class Test04 {
    public static void main(String[] args) throws InterruptedException {

        //在main线程中创建50个子线程
        for (int i = 0; i < 50; i++) {
            new MyThread().start();
        }
        Thread.sleep(1000);
        System.out.println(MyThread.count.get());

    }
    static class MyThread extends Thread{
        ///使用AtomicInteger对象
        private static AtomicInteger count = new AtomicInteger();

        //若需要实现原子性，需要对方法进行同步（synchronized）
        public  static void addCount1() {
            for (int i = 0; i < 1000; i++) {
                //自增的后缀形式
                count.getAndIncrement();
            }
            System.out.println(Thread.currentThread().getName() + " count=" + count.get());
        }

        @Override
        public void run() {
            addCount1();
        }
    }
}
