package com.lx.cas;

/**
 * @Description:
 *      使用CAS实现一个线程安全的计数器
 * @Author: LinXin_
 * @CreateTime: 2021/4/13 16:43
 * @Company: 人生无限公司
 */
public class CASTest {
    public static void main(String[] args) {
        CASCounter casCounter = new CASCounter();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(casCounter.incrementAndGet());
                }
            }).start();
        }
    }
}
class CASCounter{
    //使用voaltile关键字修饰value值，使线程可见
    volatile private long value;

    public long getValue() {
        return value;
    }

    //定义Compare and Swap方法
    private boolean compareAndSwap(long expectedValue, long newValue){
        //如果当前value的值与期望的expectedValue值一样，就把当前的Value字段替换为newValue值
        synchronized (this){
            if( value == expectedValue ){
                value = newValue;
                return true;
            }else {
                return false;
            }
        }
    }

    //定义自增的方法
    public long incrementAndGet(){
        long oldValue;
        long newValue;
        do{
            oldValue = value;
            newValue = oldValue+1;
        }while ( !compareAndSwap(oldValue,newValue) );
        return newValue;
    }
}
