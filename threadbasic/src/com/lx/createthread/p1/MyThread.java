package com.lx.createthread.p1;

/**
 * @Description: 1) 定义一个类继承Thread
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 12:37
 * @Company: 人生无限公司
 */
public class MyThread extends Thread{
    //2)重写Thread父类中的run()方法
    //run()方法体中的代码就是子线程要执行的任务

    @Override
    public void run() {
        System.out.println("这是子线程打印的内容");
    }
}
