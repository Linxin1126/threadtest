package com.lx.aomics.atomicreference;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Description:
 *      AtomicStampedReference(解决CAS:ABA问题)
 *      在AtomicStampedReference有一个整数标记值stamp,每次执行CAS操作都需要对比Stamp（版本）的值
 * @Author: LinXin_
 * @CreateTime: 2021/4/15 14:30
 */
public class test03 {
    //定义AtomicStampedReference引用操作"abc"字符串，指定初始化版本号为0
    private static AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("abc",0);

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicStampedReference.compareAndSet("abc","def",atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName() + " -- " + atomicStampedReference.getReference());
                atomicStampedReference.compareAndSet("def","abc",atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int stamp = atomicStampedReference.getStamp();//获得版本号
                System.out.println(atomicStampedReference.compareAndSet("abc", "ggg", stamp, stamp + 1));
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(atomicStampedReference.getReference());
    }
}
