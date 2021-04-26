package com.lx.intrinsiclock;

/**
 * @Description:
 *      同步过程中线程出现异常，会自动释放锁对象
 *
 *
 * @Author: LinXin_
 * @CreateTime: 2021/4/11 16:38
 * @Company: 人生无限公司
 */
public class Test09 {
    public static void main(String[] args) {
        //先创建Test01对象，通过对象名调用m1()方法
        Test09 test01 = new Test09();
        //一个线程调用m1方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.m1();    //使用的锁对象this就是test06.class
            }
        }).start();
        //另一个线程调用sm2()方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                Test09.sm2();   //使用的锁对象this也是test06.class
            }
        }).start();
    }

    //定义一个方法，打印100行字符串
    public void m1() {
        //使用当前类的运行时对象作为锁对象，可以简单的理解为把Test06类的字节码文件作为锁对象
        synchronized (Test09.class) {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " --> " + i);
                if ( i == 50){
                    //把字符串转换为int类型时，如果字符串不符合数字格式就会产生异常
                    Integer.parseInt("abc");
                }
            }
        }
    }

    //使用synchronized修饰静态方法，同步静态方法，默认的锁对象是当前类运行时类对象即（Test06.class）
    public synchronized static void sm2() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + " --> " + i);
        }
    }
}
