package com.lx.wait;

/**
 * @Description:
 *      wait(long)
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 11:53
 */
public class Test07 {
    public static void main(String[] args) {
        final Object LOCK = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK){
                    try {
                        System.out.println("thread begin wait ");
                        LOCK.wait(3000);        //如果3000毫秒内没有被唤醒，会被自动唤醒
                        System.out.println("end...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
