package com.lx.aomics.atomicreference;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 *      使用AtomicReference原子读写一个对象
 * @Author: LinXin_
 * @CreateTime: 2021/4/15 13:40
 */
public class test01 {
    //创建一个AtomicReference对象
    static AtomicReference<String> atomicReference= new AtomicReference<>("abc");

    public static void main(String[] args) {
        //创建100个线程修改字符串
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                synchronized public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(20));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(atomicReference.compareAndSet("abc","def")) {
                        System.out.println(Thread.currentThread().getName()+"将abc改成为def");
                    }
                }
            }).start();
        }
        //再创建100个线程修改字符串
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (atomicReference.compareAndSet("def", "abc")) {
                        System.out.println(Thread.currentThread().getName() + "将def还原为abc");
                    }
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicReference.get());
    }
}
