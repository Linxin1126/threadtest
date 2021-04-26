package com.lx.aomics.atomicintegerfield;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description:
 *      线程类，
 * @Author: LinXin_
 * @CreateTime: 2021/4/14 19:29
 */
public class SubThread extends Thread{
    //要更新的User对象
    private User user;
    //创建AtomicIntegerFieldUpdater更新器
    private AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");

    public SubThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        //在子线程中对User对象的age字段自增10次
        for (int i = 0; i < 10; i++) {
            System.out.println(updater.getAndIncrement(user));
        }
    }
}
