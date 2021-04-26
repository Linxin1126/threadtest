package com.lx.threadmethod.p6priority;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 17:25
 * @Company: 人生无限公司
 */
public class test {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.setPriority(1);
        threadA.start();

        ThreadB threadB = new ThreadB();
        threadB.setPriority(10);
        threadB.start();
    }
}
