package com.lx.aomics.atomicarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Description:
 *      再多线程中使用AtomicIntegerArray原子数组
 * @Author: LinXin_
 * @CreateTime: 2021/4/14 17:08
 */
public class Test02 {

    //定义一个原子数组
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

    public static void main(String[] args) {
        //定义线程数组
        Thread[] threads = new Thread[10];
        //给线程数组元素赋值
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new AddThread();
        }
        //开启子线程
        for (Thread thread : threads) {
            thread.start();
        }
        //在主线程中查看自增完以后原子数组中各个元素的值，在主线程中需要在所有子线程都执行完后再查看
        //把所有的子线程合并到当前主线程中
        for (Thread thread : threads) {
            try {
                thread.join();//让主线程等待子线程完成（防止主线程先于子线程结束）
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(atomicIntegerArray);
    }
    //定义一个线程类，在线程类中修改原子数组
    static class AddThread extends Thread {
        @Override
        public void run() {
            //把原子数组的每个元素都自增1000次
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < atomicIntegerArray.length(); j++) {
                    atomicIntegerArray.getAndIncrement(j % atomicIntegerArray.length());
                }
            }
        }
    }
}
