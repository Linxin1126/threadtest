package com.lx.threadmethod.p4getid;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 16:24
 * @Company: 人生无限公司
 */
public class SubThread5 extends Thread{
    @Override
    public void run() {
        System.out.println("thread name = " + Thread.currentThread().getName()
            +" id = " + this.getId());
    }
}
