package com.lx.threadmethod.p6priority;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 17:25
 * @Company: 人生无限公司
 */
public class ThreadA extends Thread{
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (long i = 0; i <= 10000000000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("thread a : " + (end - begin));

    }
}
