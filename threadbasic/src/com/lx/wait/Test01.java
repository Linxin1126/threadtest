package com.lx.wait;

/**
 * @Description:
 *      演示：wait()/notify()方法需要放在同步代码块中，否则会产生异常
 *      任何对象都可以调用wait()/notify(),这两个 方法是从Object类继承来的
 * @Author: LinXin_
 * @CreateTime: 2021/4/15 21:31
 */
public class Test01 {
    public static void main(String[] args) {
        try {
            String test = "lx";
            test.wait();        //java.lang.IllegalMonitorStateException异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
