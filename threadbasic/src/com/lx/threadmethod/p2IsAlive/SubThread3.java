package com.lx.threadmethod.p2IsAlive;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 15:13
 * @Company: 人生无限公司
 */
public class SubThread3 extends Thread{


    @Override
    public void run() {
        System.out.println("run方法，isAlive = " + this.isAlive());    //运行状态,true
    }
}
