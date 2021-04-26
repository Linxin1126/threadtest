package com.lx.threadsafe;

import java.util.Random;

/**
 * @Description: 测试线程的可见性
 * @Author: LinXin_
 * @CreateTime: 2021/4/10 16:00
 * @Company: 人生无限公司
 */
public class test02 {
    public static void main(String[] args) throws InterruptedException {

        MyTask myTask = new MyTask();
        new Thread(myTask).start();

        //主线程1秒后取消子线程
        Thread.sleep(1000);
        myTask.cancel();

        /*
            可能会出现以下情况：
                在main线程中调用了task.cancel()方法，把task对象的toCancel变量修改为true
                可能存在子线程看不到main线程对toCancel做的修改，在子线程中toCancle变量一值为false
         */

    }
    static  class MyTask implements Runnable {
        private boolean toCancel = false;

        @Override
        public void run() {
            while (!toCancel) {
                if(doSomething()){
                }
            }
            if (toCancel){
                System.out.println("任务被取消。。。");
            }else {
                System.out.println("任务正常结束。。。");
            }
        }
        private boolean doSomething() {
            System.out.println("执行某个任务。。。");
            try {
                Thread.sleep(new Random().nextInt(1000));   //模拟执行任务的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        public void cancel() {
            toCancel = true;
            System.out.println("收到取消线程的消息。。。");
        }
    }
}
