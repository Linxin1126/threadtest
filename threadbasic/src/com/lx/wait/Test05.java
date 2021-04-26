package com.lx.wait;

/**
 * @Description:
 *      Interrupt()会中断线程的wait()等待
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 10:30
 */
public class Test05 {
    public static void main(String[] args) throws InterruptedException {

        SubThread t1 = new SubThread();
        t1.start();
        Thread.sleep(2000); //主线程睡眠2秒，确保子线程处于wait等待状态
        t1.interrupt();


    }

    private static final Object Lock = new Object();        //定义一个常量作为锁对象
    static class SubThread extends Thread {
        @Override
        public void run() {
            synchronized ( Lock ){

                try {
                    System.out.println("begin wait......");
                    Lock.wait();
                    System.out.println("end wait......");
                } catch (InterruptedException e) {
                    System.out.println("wait等待被中断了");
                }

            }
        }
    }
}
