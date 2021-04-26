package com.lx.aomics.atomicreference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 *      演示AtomicReference 可能会出现CAS的ABA问题
 *
 * @Author: LinXin_
 * @CreateTime: 2021/4/15 14:04
 */
public class test02 {
    static AtomicReference<String> atomicReference = new AtomicReference<>("abc");
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicReference.compareAndSet("abc","def");
                System.out.println(Thread.currentThread().getName()+" -- "+atomicReference.get());
                atomicReference.compareAndSet("def","abc");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicReference.compareAndSet("abc","def");
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(atomicReference.get());

    }
}
