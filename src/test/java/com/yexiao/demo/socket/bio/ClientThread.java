package com.yexiao.demo.socket.bio;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author xuhf
 * @date 2021/1/20 15:08
 **/
public class ClientThread implements Runnable {

    @Override
    public void run() {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("192.168.2.17",9876));
            while (true){
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                char[] chars = new char[1024];
                if(inputStreamReader.read(chars) >= 0){
                    System.out.println(socket.getLocalPort() + "接收到服务端的消息:" + String.valueOf(chars));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
