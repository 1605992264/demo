package com.yexiao.demo;


import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuhf
 * @date 2020/8/21 16:55
 **/
public class Main {

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Test.flag){
                    for(int i=0;i<10000;i++){
                        test.add();
                    }
                    System.out.println(Test.count);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Test.flag){
                    for(int i=0;i<10000;i++){
                        test.add();
                    }
                    System.out.println(Test.count);
                }
            }
        }).start();
    }
}
class Test{

    public static Integer count=0;
    public static Integer flag = 0;
    public Integer x = 0;
    public void add(){
        count++;
    }


}

