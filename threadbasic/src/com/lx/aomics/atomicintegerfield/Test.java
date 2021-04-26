package com.lx.aomics.atomicintegerfield;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/14 19:18
 */
public class Test {
    public static void main(String[] args) {
        User user = new User(1,10);

        //开启10线程
        for (int i = 0; i < 10; i++) {
            new SubThread(user).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(user.toString());
    }
}
