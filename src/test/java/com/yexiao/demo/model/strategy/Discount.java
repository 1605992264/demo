package com.yexiao.demo.model.strategy;

/**
 * @author xuhf
 * @date 2020/11/5 10:29
 **/
public class Discount extends Pay {

    private double discount;
    private Pay pay;

    public Discount(Pay pay,double discount){
        this.discount = discount;
        this.pay = pay;
    }

    @Override
    public double pay(double money, int num) {
        double moneyPay = this.pay.pay(money, num);
        return moneyPay * discount;
    }
}
