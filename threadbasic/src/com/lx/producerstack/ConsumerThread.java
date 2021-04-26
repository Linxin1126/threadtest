package com.lx.producerstack;

/**
 * @Description:消费者线程
 * @Author: LinXin_
 * @CreateTime: 2021/4/16 17:41
 */
public class ConsumerThread extends Thread{
    private MyStack myStack;

    public ConsumerThread(MyStack myStack) {
        this.myStack = myStack;
    }

    @Override
    public void run() {
        while (true){
            myStack.pop();
        }
    }
}
