package com.lx.threadmethod.p1currentthread;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 13:56
 * @Company: 人生无限公司
 */
public class Test01CurrentThread {
    public static void main(String[] args) {
        System.out.println("main方法中打印当前线程："+ Thread.currentThread().getName());

        //创建子线程，调用SubThread1()构造方法，在main线程中调用构造方法，
        //所以构造方法中的当前线程就是main线程
        SubThread1 subThread1 = new SubThread1();
        //subThread1.start();//启动子线程,子线程调用run()方法，所以run()方法中的当前线程就是Thread-0子线程


        subThread1.run();//在main方法中直接调用run()方法，没有开启新的线程，所以在run()方法中的当前线程就是main线程
    }
}
