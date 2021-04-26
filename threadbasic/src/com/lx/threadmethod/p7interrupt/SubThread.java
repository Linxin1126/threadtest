package com.lx.threadmethod.p7interrupt;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 17:50
 * @Company: 人生无限公司
 */
public class SubThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <= 10000; i++) {
            System.out.println("sub thread --> " + i);
        }
    }
}
