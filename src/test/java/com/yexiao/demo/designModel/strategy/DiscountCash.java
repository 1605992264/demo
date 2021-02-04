package com.yexiao.demo.designModel.strategy;

import java.math.BigDecimal;

/**
 * @author xuhf
 * @date 2021/1/12 14:51
 **/
public class DiscountCash implements Cash {

    private float discount = 1f;

    public DiscountCash(float discount) {
        this.discount = discount;
    }

    @Override
    public double payCash(double money) {
        return money * discount;
    }
}

