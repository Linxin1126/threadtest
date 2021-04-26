package com.lx.producerdata;

/**
 * @Description:
 *      测试一生产，一消费的情况
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 15:18
 */
public class Test {
    public static void main(String[] args) {
        ValueOP valueOP = new ValueOP();

        ProducerThread producerThread = new ProducerThread(valueOP);
        ComsuerThread comsuerThread = new ComsuerThread(valueOP);

        producerThread.start();
        comsuerThread.start();
        //生产与消费交替运行
    }
}
