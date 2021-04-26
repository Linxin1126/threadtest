package com.lx.producerdata;

/**
 * @Description: 定义线程类模拟消费者
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 15:49
 */
public class ComsuerThread extends Thread {
    //消费者使用数据，就是使用ValueOP类的value字段值
    private ValueOP obj;

    public ComsuerThread(ValueOP obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true) {
            obj.getValue();
        }
    }
}
