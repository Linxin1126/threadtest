package com.lx.intrinsiclock;


/**
 * @Description:
 *      同步方法与同步代码块如何选择
 *      同步方法锁的粒度粗，执行效率低
 *      同步代码块锁的粒度细，执行效率高
 * @Author: LinXin_
 * @CreateTime: 2021/4/12 13:22
 * @Company: 人生无限公司
 */
public class Test07 {
    public static void main(String[] args) {
        Test07 obj = new Test07();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.doLongTimeTask2();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.doLongTimeTask2();
            }
        }).start();

    }
    //同步代码块锁的粒度细，执行效率高
    public void doLongTimeTask()  {
        try {
            System.out.println("Task Begin");
            Thread.sleep(3000);     //模拟任务需要准备3秒钟

            synchronized (this) {
                System.out.println("开始同步");
                for (int i = 1; i <= 100 ; i++) {
                    System.out.println(Thread.currentThread().getName() + "--->" + i);
                }
            }
            System.out.println("Task End");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //同步方法锁的粒度粗，执行效率低
    public synchronized void doLongTimeTask2()  {
        try {
            System.out.println("Task Begin");
            Thread.sleep(3000);     //模拟任务需要准备3秒钟

            System.out.println("开始同步");
            for (int i = 1; i <= 100 ; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + i);
            }

            System.out.println("Task End");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
