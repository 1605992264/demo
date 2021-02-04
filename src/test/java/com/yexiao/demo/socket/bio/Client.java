package com.yexiao.demo.socket.bio;

/**
 * @author xuhf
 * @date 2021/1/20 15:36
 **/
public class Client {

    public static void main(String[] args) {
        new Thread(new ClientThread()).start();
        new Thread(new ClientThread()).start();
        new Thread(new ClientThread()).start();
        new Thread(new ClientThread()).start();
    }
}
