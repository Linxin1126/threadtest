package com.lx.createthread.p2;

/**
 * @Description: 演示线程运行结果的随机性
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 13:06
 * @Company: 人生无限公司
 */
public class Test {
    public static void main(String[] args) {

        MyThread2 myThread2 = new MyThread2();
        myThread2.start();//开启子线程

        //当前是main线程
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("main-- thread:" + i);
                int time = (int)(Math.random()*1000);
                Thread.sleep(time);//线程睡眠，单位是毫秒，1秒等于1000毫秒
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
