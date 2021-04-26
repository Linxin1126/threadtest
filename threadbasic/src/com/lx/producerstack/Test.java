package com.lx.producerstack;

/**
 * @Description:测试一生产一消费的情况
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 17:14
 */
public class Test {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        ProducerThread producerThread = new ProducerThread(myStack);
        ConsumerThread consumerThread = new ConsumerThread(myStack);

        producerThread.start();
        consumerThread.start();
        /*
            运行结果是两个线程交替执行，一个线程负责生产，通知另外一个线程负责消费
         */
    }
}
