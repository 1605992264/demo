package com.yexiao.demo.designModel.decoration;

/**
 * @author xuhf
 * @date 2021/1/13 16:01
 **/
public class Main {

    public static void main(String[] args) {
        Cash activityCash = new ReturnCash(89, 20, new DiscountCash(0.9f, new DefaultCash()));
        double money = activityCash.payCash(100);
        System.out.println(money);
    }

}
