package com.lx.threadmethod.p7interrupt;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 17:50
 * @Company: 人生无限公司
 */
public class SubThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <= 10000; i++) {

            //判断线程的中断标记,线程有 isInterrupted()方法该方法返回线程中断标记

            if(this.isInterrupted()) {
                System.out.println("当前线程的中断标记为true,我要退出了");
                //break;        //中断循环，run()方法执行完毕，子线程运行完毕
                return;         //直接结束当前run()方法的执行
            }
            System.out.println("sub thread --> " + i);
        }
    }
}
