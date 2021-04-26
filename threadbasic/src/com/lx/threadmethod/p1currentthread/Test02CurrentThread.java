package com.lx.threadmethod.p1currentthread;

/**
 * @Description:
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 14:30
 * @Company: 人生无限公司
 */
public class Test02CurrentThread {
    public static void main(String[] args) throws InterruptedException {
        //创建子线程对象
        SubThread2 t2 = new SubThread2();
       // t2.setName("t2");       //设置线程的名称
        t2.start();

        Thread.sleep(500);          //main线程睡眠500毫秒

        //Thread(Runnable)构造方法形参是Runnable接口，调用时传递的实参是接口的实现类对象
        Thread  t3 = new Thread(t2);
        t3.start();

    }
}
