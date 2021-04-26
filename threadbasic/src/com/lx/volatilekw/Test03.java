package com.lx.volatilekw;

/**
 * @Description:
 *      volatile不具备原子性
 * @Author: LinXin_
 * @CreateTime: 2021/4/13 14:10
 * @Company: 人生无限公司
 */
public class Test03 {
    public static void main(String[] args) {

        //在main线程中创建10个子线程
        for (int i = 0; i < 50; i++) {
            new MyThread().start();
        }

    }
    static class MyThread extends Thread{
        //volatile关键字仅仅是表示所有线程从主内存读取count变量的值(若使用了synchronized就不需要volatile)
        volatile public static int count;

        //这段代码运行后不是线程安全的，想要线程安全，需要使用synchronized进行同步，
        //如果使用synchronized同时，也就不需要使用volatile关键字了
        public static void addCount() {
            for (int i = 0; i < 1000; i++) {
                //count++不是原子操作
                count++;
            }
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }

        //若需要实现原子性，需要对方法进行同步（synchronized）
        public synchronized static void addCount1() {
            for (int i = 0; i < 1000; i++) {
                //count++不是原子操作
                count++;
            }
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }

        @Override
        public void run() {
            addCount1();
        }
    }
}
