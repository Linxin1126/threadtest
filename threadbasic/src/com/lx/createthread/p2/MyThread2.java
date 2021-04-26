package com.lx.createthread.p2;


/**
 * @Description: 演示线程运行结果的随机性
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 13:07
 * @Company: 人生无限公司
 */
public class MyThread2 extends Thread{
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("sub thread:" + i);
                int time = (int)(Math.random()*1000);
                Thread.sleep(time);//线程睡眠，单位是毫秒，1秒等于1000毫秒
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
