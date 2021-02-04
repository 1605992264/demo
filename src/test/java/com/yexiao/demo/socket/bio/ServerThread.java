package com.yexiao.demo.socket.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xuhf
 * @date 2021/1/20 15:15
 **/
public class ServerThread implements Runnable {

    private static ServerSocket serverSocket;
    private List<Socket> sockets = new LinkedList<>();

    static {
        try {
            serverSocket = new ServerSocket(9876,10, InetAddress.getByName("192.168.2.17"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void send(String message,int num){
        Socket socket = sockets.get(num);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(socket.getOutputStream());
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            if(writer != null){
                writer.close();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            Socket accept = null;
            try {
                System.out.println("等待连接");
                accept = serverSocket.accept();
                sockets.add(accept);
                PrintWriter writer = new PrintWriter(accept.getOutputStream());
                writer.write("你好！" + accept.getRemoteSocketAddress().toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
                if(accept != null){
                    try {
                        accept.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
