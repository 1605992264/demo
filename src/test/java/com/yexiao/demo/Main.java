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
        ExecutorService executorService = Executors.newFixedThreadPool(10);


        Test test = new Test();
        new Thread(new Runnable() {
            @Override
            public void run() {
                    for(int i=0;i<10000;i++){
                        test.add();

                    }
                    System.out.println(test.threadLocal.get());

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                    for(int i=0;i<10000;i++){
                        test.add();

                    }
                    System.out.println(test.threadLocal.get());
                }
        }).start();
    }
}
class Test{

    public ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    {
        threadLocal.set(0);
    }

    public void add(){
        threadLocal.set(Math.addExact(threadLocal.get(),1));
    }


}

