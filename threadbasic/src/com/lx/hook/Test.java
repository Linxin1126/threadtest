package com.lx.hook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @Description:通过Hook线程防止线程重复启动
 * @Author: LinXin_
 * @CreateTime: 2021/4/22 14:50
 */
public class Test {
    public static void main(String[] args) {
        //1、出入Hook线程,在程序退出时删除.lock文件
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("JVM退出，会启动当前Hook线程，在Hook线程中删除.lock文件");
                getLockFile().toFile().delete();
            }
        });

        //2、程序运行时，检查lock文件是否存在，如果.lock文件存在，则抛出异常
        if(getLockFile().toFile().exists()){
            throw new RuntimeException("程序已启动。。。");
        }else { //文件不存在说明程序第一次启动，创建.lock文件
            try {
                getLockFile().toFile().createNewFile();
                System.out.println("程序在启动时");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //模拟程序运行
        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("程序正在运行。。。");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Path getLockFile() {
        return Paths.get("","tmp.lock");
    }
}
