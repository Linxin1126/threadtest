package com.lx.lock.fair_or;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: int getHoldCount()可以返回当前线程调用lock()方法的次数
 * @Author: LinXin_
 * @CreateTime: 2021/4/19 12:34
 */
public class Test02 {
    static ReentrantLock lock = new ReentrantLock();    //定义锁对象

    public static void m1(){
        try{
            lock.lock();
            //打印线程调用lock()的次数
            System.out.println(Thread.currentThread().getName()+" -- hold count :"+lock.getHoldCount());
            //调用m2方法，ReentrantLock是可重入锁，所以在m2方法中可以再次获得该锁对象
            m2();
        }finally {
            lock.unlock();
        }
    }
    public static void m2(){
        try{
            lock.lock();
            //打印线程调用lock()的次数
            System.out.println(Thread.currentThread().getName()+" == hold count :"+lock.getHoldCount());
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        //main线程调用m1()
        m1();
    }
}
