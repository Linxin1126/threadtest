package com.lx.producerdata;

/**
 * @Description: 定义线程类模拟生产者
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 15:49
 */
public class ProducerThread extends Thread {
    //生产者生产数据就是调用valueOP类的setValue方法给value字段赋值
    private ValueOP obj;

    public ProducerThread(ValueOP obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true) {
            obj.setValue();
        }
    }
}
