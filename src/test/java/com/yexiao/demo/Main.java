package com.yexiao.demo;

/**
 * @author xuhf
 * @date 2020/8/21 16:55
 **/
public class Main {
    public static void main(String[] args) {
        String ccc = "wer";
        String a = "qwertyuiopasdfghjkl";
        System.out.println(a.substring(a.indexOf(ccc)+ccc.length()));
    }

}
