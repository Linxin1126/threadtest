package com.lx.intrinsiclock;

/**
 * @Description:
 *      脏读
 *      读取属性值出现了一些意外，读取的是中间值，而不是修改之后的值
 *      出现的脏读的原因是：对共享数据的读取与修改不同步
 *      解决方法：（synchronized关键字）
 *              不仅对修改数据的代码块进行同步，也要对读取数据的代码块进行同步
 * @Author: LinXin_
 * @CreateTime: 2021/4/12 13:55
 * @Company: 人生无限公司
 */
public class Test08 {
    public static void main(String[] args) throws InterruptedException {

        //开启子线程设置用户名和密码
        PublicValue publicValue = new PublicValue();
        SubThread t1 = new SubThread(publicValue);
        t1.start();

        //为了确定设置成功
        Thread.sleep(100);

        //在main线程中读取用户名，密码
        publicValue.getValue();
    }

    //定义线程，设置用户名和密码
    static class SubThread extends Thread{
        private PublicValue publicValue;

        public SubThread(PublicValue publicValue) {
            this.publicValue = publicValue;
        }

        @Override
        public void run() {
            publicValue.setValue("bjpowernode","123");
        }
    }

    static class PublicValue {

        private String name = "lx";
        private String pwd = "666";

        public synchronized void getValue() {
            System.out.println(Thread.currentThread().getName() + ", getter -- name: " + name + "," + ",--pwd: " + pwd);
        }

        public synchronized void setValue(String name,String pwd) {

            this.name = name;
            try {
                Thread.sleep(1000);         //模拟操作name属性需要一定时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.pwd = pwd;
            System.out.println(Thread.currentThread().getName() + ",setter -- name: "+name+", s-- pwd: "+pwd);

        }
    }
}
