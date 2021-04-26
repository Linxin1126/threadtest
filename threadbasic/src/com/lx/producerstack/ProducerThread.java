package com.lx.producerstack;

/**
 * @Description:生产者线程
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 17:41
 */
public class ProducerThread extends Thread{
    private MyStack myStack;

    public ProducerThread(MyStack myStack) {
        this.myStack = myStack;
    }

    @Override
    public void run() {
        while (true) {
            myStack.push();
        }
    }
}
