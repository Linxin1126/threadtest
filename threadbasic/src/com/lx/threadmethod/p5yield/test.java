package com.lx.threadmethod.p5yield;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 16:49
 * @Company: 人生无限公司
 */
public class test {
    public static void main(String[] args) {
        //开启一个子线程，计算累加和
        SubThread6 t6 = new SubThread6();
        t6.start();

        //在main线程中计算累加和
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 1; i <= 1000000; i++) {
            sum+=i;
            Thread.yield();
        }
        long end = System.currentTimeMillis();

        System.out.println("main方法用时：" + (end - begin));
    }
}
