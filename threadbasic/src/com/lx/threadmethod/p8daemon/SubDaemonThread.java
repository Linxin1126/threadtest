package com.lx.threadmethod.p8daemon;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 18:13
 * @Company: 人生无限公司
 */
public class SubDaemonThread extends Thread{
    @Override
    public void run() {
        while(true){
            System.out.println("sub thread.....");

        }
    }
}
