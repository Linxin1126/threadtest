package com.lx.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: wait条件发生变化
 * 定义一个集合
 * 定义一个线程向集合中添加数据，添加完数据后通知另外的线程从集合中取数据
 * 定义一个线程从集合中取数据，如果集合中没有数据则等待
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 13:37
 */
public class Test10 {
    public static void main(String[] args) {
        //定义添加数据的线程对象
        ThreadAdd threadAdd = new ThreadAdd();
        //定义取数据的线程对象
        ThreadSubtract threadSubtract = new ThreadSubtract();
        threadSubtract.setName("subtract 1 ");
        //测试一：先开启添加数据的线程，再开启一个取数据的线程，大多数情况下会正常取数据
//        threadAdd.start();
//        threadSubtract.start();
        //测试二：先开启取数据的线程，再开启一个添加数据的线程，取数据的线程会先等待，等到添加数据之后再取数据
//        threadSubtract.start();
//        threadAdd.start();
        //测试三：开启两个取数据的线程，再开启添加数据的线程
        ThreadSubtract threadSubtract2 = new ThreadSubtract();
        threadSubtract2.setName("subtract 2 ");
        threadSubtract.start();
        threadSubtract2.start();
        threadAdd.start();
        /*
            某一次执行结果如下：
                subtract 1 begin wait.....
                subtract 2 从集合中取了data后，集合中数据的数量：0
                subtract 1 end wait.....
                Exception in thread "subtract 1 " java.lang.IndexOutOfBoundsException
            分析可能的执行顺序：
                threadSubtract线程先启动，取数据时，集合中没有数据，wait()等待
                threadAdd线程获得CPU执行权，添加数据，把threadSubtract线程唤醒，
                threadSubtract2线程开启后获得CPU执行权，正常取数据
                threadSubtract线程或的CPU执行权，打印end wait 再执行list.remove(0)取数据时，现在list集合中已经没有数据了，
                这时会产生java.lang.IndexOutOfBoundsException异常
            出现异常的原因是：向list集合中添加了一个数据，remove()了两次
            如何解决？
                当等待的线程被唤醒后，再判断一次集合中是否有数据可取，即需要把subtract()方法中的if判断改为while
         */


    }

    //1、定义一个集合
    static List list = new ArrayList<>();

    //2、定义方法从集合中取数据
    public static void subtract() {

        synchronized (list) {
            while (list.size() == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + "begin wait.....");
                    list.wait();        //等待
                    System.out.println(Thread.currentThread().getName() + "end wait.....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Object data = list.remove(0);  //从集合中取出一个数据
            System.out.println(Thread.currentThread().getName() + "从集合中取了" + data + "后，集合中数据的数量：" +
                    list.size());
        }
    }

    //3、定义一个方法向集合中添加数据后,通知等待的线程取数据
    public static void add() {
        synchronized (list) {
            list.add("data");
            System.out.println(Thread.currentThread().getName() + "存储了一个数据");
            list.notifyAll();
        }
    }

    //4、定义线程类调用add()取数据的方法
    static class ThreadAdd extends Thread {
        @Override
        public void run() {
            add();
        }
    }

    //定义线程类调用subtract()方法
    static class ThreadSubtract extends Thread {
        @Override
        public void run() {
            subtract();
        }
    }

}
