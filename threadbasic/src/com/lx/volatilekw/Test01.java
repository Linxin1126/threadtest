package com.lx.volatilekw;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/12 17:40
 * @Company: 人生无限公司
 */
public class Test01 {
    public static void main(String[] args) {
        //创建PrintString对象
        PrintString printString = new PrintString();
        //调用方法打印字符串
        printString.printStringMethod();

        //main线程睡眠1000毫秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("在main线程中修改打印标志");
        printString.setContinuePrint(false);
        //修改完打印标志后，运行程序，查看程序运行结果
        //程序根根本不会停止，因为printString.printStringMethod()方法调用后，该方法一致处于死循环状态，
        //程序根本执行不到printString.setContinuePrint(false);语句
        //解决方法：可以使用多线程技术
    }

    //定义类打印字符串
    static class PrintString {
        private boolean continuePrint = true;

        public void setContinuePrint(boolean continuePrint) {
            this.continuePrint = continuePrint;
        }

        public void printStringMethod() {
            while (continuePrint) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
