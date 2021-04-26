package com.lx.threadmethod.p1currentthread;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 14:26
 * @Company: 人生无限公司
 */
public class SubThread2 extends Thread{
    public SubThread2() {
        System.out.println("构造方法中，Thread.currentThread().getName() ：" + Thread.currentThread().getName() );
        System.out.println("构造方法中，this.getName() ：" + this.getName());
    }

    @Override
    public void run() {
        System.out.println("run方法中，Thread.currentThread().getName() ：" + Thread.currentThread().getName() );
        System.out.println("run方法中，this.getName() ：" + this.getName());
    }
}
