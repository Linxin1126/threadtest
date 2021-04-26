package com.lx.producerstack;

/**
 * @Description:测试多生产多消费的情况
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 18:10
 */
public class Test02 {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();

        ProducerThread producerThread1 = new ProducerThread(myStack);
        ProducerThread producerThread2 = new ProducerThread(myStack);
        ProducerThread producerThread3 = new ProducerThread(myStack);

        ConsumerThread consumerThread1 = new ConsumerThread(myStack);
        ConsumerThread consumerThread2 = new ConsumerThread(myStack);
        ConsumerThread consumerThread3 = new ConsumerThread(myStack);

        producerThread1.setName("生产者一号");
        producerThread2.setName("生产者二号");
        producerThread3.setName("生产者三号");

        consumerThread1.setName("消费者一号");
        consumerThread2.setName("消费者二号");
        consumerThread3.setName("消费者三号");

        producerThread1.start();
        producerThread2.start();
        producerThread3.start();
        consumerThread1.start();
        consumerThread2.start();
        consumerThread3.start();
    }


}
