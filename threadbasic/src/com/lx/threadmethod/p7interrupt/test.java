package com.lx.threadmethod.p7interrupt;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 17:49
 * @Company: 人生无限公司
 */
public class test {
    public static void main(String[] args) {
        SubThread subThread = new SubThread();
        subThread.start();      //开启子线程

        //当前线程是main线程
        for (int i = 0; i <= 100; i++) {
            System.out.println("main ==> " + i);
        }

        //中断子线程
        subThread.interrupt();      //仅仅是给子线程标记中断，子线程没有真正的中断
    }
}
