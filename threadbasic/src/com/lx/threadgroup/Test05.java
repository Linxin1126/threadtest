package com.lx.threadgroup;

/**
 * @Description:演示设置守护线程组
 * @Author: LinXin_
 * @CreateTime: 2021/4/21 17:12
 */
public class Test05 {
    public static void main(String[] args) throws InterruptedException {
        //先定义线程组
        ThreadGroup group = new ThreadGroup("group");
        //设置线程组为守护线程组
        group.setDaemon(true);
        //向线程组中加入三个线程
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 20; j++) {
                        System.out.println(Thread.currentThread().getName() + "--" + j);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        //main线程睡眠5秒
        Thread.sleep(5000);
        System.out.println("main--------end---");
    }
}
