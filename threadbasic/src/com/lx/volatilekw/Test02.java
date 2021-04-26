package com.lx.volatilekw;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/12 17:40
 * @Company: 人生无限公司
 */
public class Test02 {
    public static void main(String[] args) {
        //创建PrintString对象
        PrintString printString = new PrintString();
        //开启子线程让子线程调用方法打印字符串
        new Thread(new Runnable() {
            @Override
            public void run() {
                printString.printStringMethod();
            }
        }).start();

        //main线程睡眠1000毫秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("在main线程中修改打印标志");
        printString.setContinuePrint(false);
        //修改完打印标志后，运行程序，查看程序运行结果
        //程序运行后，可能会出现死循环现象
        //分析原因：main线程修改了printString对象的打印标志后，子线程读不到
        //解决方法：使用volatile关键字修饰printString对象的打印标志
        //volatile的作用可以强制线程从公共内存中读取变量的值，而不是从工作内存中读取
    }

    //定义类打印字符串
    static class PrintString {
        private volatile boolean continuePrint = true;

        public void setContinuePrint(boolean continuePrint) {
            this.continuePrint = continuePrint;
        }

        public void printStringMethod() {
            System.out.println("开始。。。");
            while (continuePrint) {

            }
            System.out.println("结束。。。");
        }

    }
}
