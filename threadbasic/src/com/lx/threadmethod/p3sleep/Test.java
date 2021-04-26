package com.lx.threadmethod.p3sleep;


/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 15:42
 * @Company: 人生无限公司
 */
public class Test {
    public static void main(String[] args) {

        SubThread4 t4 = new SubThread4();
        System.out.println("main_begin = " + System.currentTimeMillis());
        //t4.start();     //开启新的线程
        t4.run();       //在main线程中调用实例方法run(),没有开启新的线程
        System.out.println("main_end = " + System.currentTimeMillis());

    }
}
