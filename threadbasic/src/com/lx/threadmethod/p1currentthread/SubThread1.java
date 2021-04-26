package com.lx.threadmethod.p1currentthread;

/**
 * @Description:
 *  定义线程类
 *  分别在构造方法中和run方法中打印当前线程
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 13:57
 * @Company: 人生无限公司
 */
public class SubThread1 extends Thread {
    public SubThread1(){
        System.out.println("构造方法打印当前线程的名称："+ Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run方法打印当前线程的名称：" + Thread.currentThread().getName());
    }
}
