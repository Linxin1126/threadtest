package com.lx.aomics.atomiclong;

import java.util.Random;

/**
 * @Description:
 *      模拟服务器的请求总数，处理成功数，处理失败数
 * @Author: LinXin_
 * @CreateTime: 2021/4/14 15:07
 */
public class Test {
    public static void main(String[] args) {
        //通过线程模拟请求,实际应用中可以在ServletFilter中调用Indicator计数器的相关方法
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //每个线程就是一个请求，请求总数加1
                    Indicator.getInstance().newRequestReceive();
                    int num = new Random().nextInt();
                    if ( num % 2 == 0) {    //偶数模拟成功
                        Indicator.getInstance().requestProcessSuccess();
                    }else {
                        Indicator.getInstance().requestProcessFailure();
                    }

                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //打印结果
        System.out.println("总请求数： "+Indicator.getInstance().getRequestCount());
        System.out.println("成功请求数： "+Indicator.getInstance().getSuccessCount());
        System.out.println("失败请求数： "+Indicator.getInstance().getFailureCount());

    }
}
