package com.lx.threadmethod.p4getid;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 16:23
 * @Company: 人生无限公司
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName()+"--------"+Thread.currentThread().getId());
        for (int i = 0; i < 5; i++) {
            new SubThread5().start();
            Thread.sleep(100);
        }
    }
}
