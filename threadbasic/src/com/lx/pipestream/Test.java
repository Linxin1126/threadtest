package com.lx.pipestream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @Description:使用PipedInputStream和PipedOutputStream管道字节流在线程之间传递数据
 * @Author: LinXin_
 * @CreateTime: 2021/4/17 09:40
 */
public class Test {
    public static void main(String[] args) throws IOException {

        //定义管道字节流
        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream();
        //在输入管道流与输出管道流之间建立连接
        inputStream.connect(outputStream);
        //创建一个线程向管道流中写入数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                writeData(outputStream);
            }
        }).start();

        //定义一个线程从管道流中读取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                readData(inputStream);
            }
        }).start();
    }

    //定义方法：向管道流中写入数据
    public static void writeData(PipedOutputStream out) {
        try {
            //分别把0～100之间的数写入管道中
            for (int i = 0; i < 100; i++) {
                String data = " " + i;
                out.write(data.getBytes());     //把字节数组写入到输出管道流中
            }
            out.close();                //关闭管道流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //定义方法：从管道流中读取数据
    public static void readData(PipedInputStream in) {
        byte[] bytes = new byte[1024];
        try {
            //从管道输入字节流中读取字节保存到字节数组
            int len = in.read(bytes);       //返回读到的字节数，如果没有读到任何数据返回-1
            while (len != -1) {
                //把bytes数组中从0开始到len个字节转换为字符串打印
                System.out.println(new String(bytes, 0, len));
                len = in.read(bytes);       //继续从管道中读取数据
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
