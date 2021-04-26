package com.lx.threadmethod.p5yield;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 16:50
 * @Company: 人生无限公司
 */
public class SubThread6 extends Thread{
    @Override
    public void run() {

        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 1; i <= 1000000; i++) {
            sum += i;

        }
        long end = System.currentTimeMillis();

        System.out.println("用时：" + (end - begin));
    }
}
