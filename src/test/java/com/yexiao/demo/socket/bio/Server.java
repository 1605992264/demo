package com.yexiao.demo.socket.bio;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author xuhf
 * @date 2021/1/19 14:19
 **/
public class Server {

    public static void main(String[] args) throws Exception {

        ServerThread server = new ServerThread();
        new Thread(server).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Scanner scanner = new Scanner(System.in);
                    String s = scanner.nextLine();
                    server.send(s, Integer.parseInt(s.substring(0,1)));
                }
            }
        }).start();
    }
}
