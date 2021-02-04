package com.yexiao.demo.designModel.strategy;

/**
 * @author xuhf
 * @date 2021/1/11 11:33
 *
 * 策略模式
 **/
public class Main {

    public static void main(String[] args) {
        DiscountCash discountCash = new DiscountCash(0.9f);
        ReturnCash returnCash = new ReturnCash(500,100);
        Double money =  returnCash.payCash(discountCash.payCash(600));
        System.out.println(money);
    }
}
