package com.lx.producerdata;

/**
 * @Description:
 *      定义一个操作数据的类
 *
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 15:18
 */
public class ValueOP {
    private String value = "";

    //定义方法修改value字段的值
    public void setValue() {
        synchronized (this) {
            //如果value值不是""空串就等待
            while (!value.equalsIgnoreCase("")) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果value字段值是空串，就设置value字段的值
            String value = System.currentTimeMillis() + " - " + System.nanoTime();
            System.out.println("set设置的值是：" + value);
            this.value = value;
//            this.notify();        //在多生产者多消费者环境中，notify()不能保证是生产者唤醒消费者，
//            如果生产者唤醒的还是生产者可能会出现假死的情况
            this.notifyAll();
        }
    }

    //定义方法读取value字段的值
    public void getValue() {
        synchronized (this) {
            //如果value是空串就等待
            while (value.equalsIgnoreCase("")) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果不是空串，读取字段的值
            System.out.println("get的值是：" + this.value);
            this.value = "";
            this.notifyAll();
        }
    }
}
