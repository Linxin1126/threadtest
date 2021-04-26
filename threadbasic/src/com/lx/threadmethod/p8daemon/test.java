package com.lx.threadmethod.p8daemon;

/**
 * @Description: 设置线程为守护线程
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 18:12
 * @Company: 人生无限公司
 */
public class test {
    public static void main(String[] args) {
        SubDaemonThread subDaemonThread = new SubDaemonThread();
        //设置线程为守护线程
        subDaemonThread.setDaemon(true);       //设置守护线程的代码应该在线程启动前
        subDaemonThread.start();

        //当前线程为main线程
        for (int i = 0; i <= 10; i++) {
            System.out.println("main ==> " + i);
        }

        //当main线程结束，守护线程subDaemonThread也销毁了
    }
}
