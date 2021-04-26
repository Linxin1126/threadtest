package com.lx.threadmethod.p3sleep;

/**
 * @Description: 使用线程休眠Thread.sleep完成一个简易的计时器
 * @Author: LinXin_
 * @CreateTime: 2021/4/9 16:04
 * @Company: 人生无限公司
 */
public class SimpleTimer {
    public static void main(String[] args) {

        try {
            int remaining = 60;     //从60秒开始计时
            if (args.length == 1){
                remaining = Integer.parseInt(args[0]);
            }

            while(true){
                System.out.println("Remaining: " + remaining);
                remaining--;
                Thread.sleep(1000);     //线程休眠
                if(remaining<0) break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");

    }
}
