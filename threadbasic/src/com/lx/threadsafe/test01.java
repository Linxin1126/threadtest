package com.lx.threadsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 演示线程原子性问题
 * @Author: LinXin_
 * @CreateTime: 2021/4/10 15:10
 * @Company: 人生无限公司
 */
public class test01 {
    public static void main(String[] args) {

        //启动两个线程，不断的调用getNum方法
        MyInt2 myInt = new MyInt2();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println(Thread.currentThread().getName() + " --> " + myInt.getNum());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    static class MyInt{
        int num;
        public int getNum() {
            return num++;
           /*
                num++自增操作步骤：
                    读取num的值
                    num自增
                    把自增后的值赋值给num变量
            */
        }
    }
    //在java中提供了一个线程安全的AtomicInteger类，保证了操作的原子性
    static class MyInt2{
        AtomicInteger num = new AtomicInteger();
        public int getNum() {
            return num.getAndIncrement();
        }
    }
}
