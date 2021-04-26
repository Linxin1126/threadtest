package com.lx.intrinsiclock;

/**
 * @Description:
 * synchronize同步代码块,如果线程的锁不同，不能实现同步
 * 想要实现同步必须使用同一个锁对象
 * @Author: LinXin_
 * @CreateTime: 2021/4/11 16:38
 * @Company: 人生无限公司
 */
public class Test02 {
    public static void main(String[] args) {
        //创建两个线程，分别调用mm()方法
        //先创建Test01对象，通过对象名调用mm()方法
        Test02 test01 = new Test02();
        Test02 test02 = new Test02();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.mm();    //使用的锁对象this就是test01对象
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test02.mm();    //使用的锁对象this也是test02对象
            }
        }).start();
    }

    //定义一个方法，打印100行字符串
    public void mm() {
        synchronized (this) {   //经常使用this当前对象作为锁对象
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }
    }
}
