package com.lx.threadmethod.p2IsAlive;

/**
 * @Description: 测试线程的活动状态
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 15:13
 * @Company: 人生无限公司
 */
public class test {
    public static void main(String[] args) {
        SubThread3 t3 = new SubThread3();
        System.out.println("begin==" + t3.isAlive());   //false,在启动线程之间
        t3.start();
        //结果不一定，打印这一行时，如果t3线程还没结束就返回true，如果t3线程已结束，返回false
        System.out.println("end==" + t3.isAlive());


    }
}
