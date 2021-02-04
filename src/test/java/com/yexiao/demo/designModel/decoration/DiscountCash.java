package com.yexiao.demo.designModel.decoration;

/**
 * @author xuhf
 * @date 2021/1/12 14:51
 **/
public class DiscountCash implements Cash {

    private float discount = 1f;
    private Cash cash;

    public DiscountCash(float discount,Cash cash) {
        this.discount = discount;
        this.cash = cash;
    }

    @Override
    public double payCash(double money) {
        money = cash.payCash(money);
        return money * discount;
    }
}

