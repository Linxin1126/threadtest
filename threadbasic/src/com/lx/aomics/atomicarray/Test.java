package com.lx.aomics.atomicarray;


import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Description:
 *
 *      AtomicIntegerArray的基本操作
 *      原子更新数组
 *
 * @Author: LinXin_
 * @CreateTime: 2021/4/14 16:25
 */
public class Test {
    public static void main(String[] args) {
        //1、创建一个指定长度的原子数组
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        System.out.println(atomicIntegerArray);
        //2、返回指定位置的元素
        System.out.println(atomicIntegerArray.get(1));
        //3、设置指定位置的元素
        atomicIntegerArray.set(0,10);
        //在设置数组元素的新值时，同时返回数组元素的旧值
        System.out.println(atomicIntegerArray.getAndSet(0,20));
        System.out.println(atomicIntegerArray);
        //4、修改数组元素的值，把数组元素加上某个值
        System.out.println(atomicIntegerArray.addAndGet(0, 10));
        //5、CAS操作
        //如果数组中索引值为0的元素的值是30，就修改为100
        System.out.println( atomicIntegerArray.compareAndSet(0,30,100));
        System.out.println(atomicIntegerArray);
        //6、自增/自减操作
        System.out.println(atomicIntegerArray.getAndIncrement(0));//先返回旧值，再增（相当于后缀）
        System.out.println(atomicIntegerArray.incrementAndGet(0));//先增再返回（相当于前缀）
        System.out.println(atomicIntegerArray);

        System.out.println(atomicIntegerArray.getAndDecrement(1));//先返回，再自减（相当于后缀）
        System.out.println(atomicIntegerArray);
        System.out.println(atomicIntegerArray.decrementAndGet(1));//先自减，再返回（相当于前缀）
        System.out.println(atomicIntegerArray);
    }
}
