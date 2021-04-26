package com.lx.intrinsiclock;

/**
 * @Description:
 * synchronize同步代码块
 * 使用一个常量对象作为锁对象
 * @Author: LinXin_
 * @CreateTime: 2021/4/11 16:38
 * @Company: 人生无限公司
 */
public class Test04 {
    public static void main(String[] args) {
        //创建两个线程，分别调用mm()方法
        //先创建Test01对象，通过对象名调用mm()方法
        Test04 test01 = new Test04();
        Test04 test02 = new Test04();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.mm();    //使用的锁对象OBJ常量
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test02.mm();    //使用的锁对象OBJ常量
            }
        }).start();

        //第三个线程调用静态方法
        new Thread(new Runnable() {
            @Override
            public void run() {
               sm();   //使用的锁对象OBJ常量
            }
        }).start();
    }

    public static final Object OBJ = new Object();  //定义一个常量
    //定义一个方法，打印100行字符串
    public void mm() {
        synchronized (OBJ) {   //使用一个常量对象作为锁对象
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }
    }
    //定义一个方法，打印100行字符串
    public static void sm() {
        synchronized (OBJ) {   //使用一个常量对象作为锁对象
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        }
    }
}
