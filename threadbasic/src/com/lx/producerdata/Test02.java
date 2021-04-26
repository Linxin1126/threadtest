package com.lx.producerdata;

/**
 * @Description:
 *      测试多生产，多消费的情况
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 15:18
 */
public class Test02 {
    public static void main(String[] args) {
        ValueOP valueOP = new ValueOP();

        ProducerThread producerThread1 = new ProducerThread(valueOP);
        ProducerThread producerThread2 = new ProducerThread(valueOP);
        ProducerThread producerThread3 = new ProducerThread(valueOP);
        ComsuerThread comsuerThread1 = new ComsuerThread(valueOP);
        ComsuerThread comsuerThread2 = new ComsuerThread(valueOP);
        ComsuerThread comsuerThread3 = new ComsuerThread(valueOP);

        producerThread1.start();
        producerThread2.start();
        producerThread3.start();
        comsuerThread1.start();
        comsuerThread2.start();
        comsuerThread3.start();
        //生产与消费交替运行
    }
}
