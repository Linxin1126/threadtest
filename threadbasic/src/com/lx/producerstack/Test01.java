package com.lx.producerstack;

/**
 * @Description:测试一生产多消费的情况
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 18:10
 */
public class Test01 {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        ProducerThread producerThread1 = new ProducerThread(myStack);

        ConsumerThread consumerThread1 = new ConsumerThread(myStack);
        ConsumerThread consumerThread2 = new ConsumerThread(myStack);
        ConsumerThread consumerThread3 = new ConsumerThread(myStack);

        consumerThread1.setName("消费者一号");
        consumerThread2.setName("消费者二号");
        consumerThread3.setName("消费者三号");

        producerThread1.start();
        consumerThread1.start();
        consumerThread2.start();
        consumerThread3.start();
    }


}
