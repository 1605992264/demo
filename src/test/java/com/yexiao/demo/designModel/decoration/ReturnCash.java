package com.yexiao.demo.designModel.decoration;

import com.yexiao.demo.base.domain.R;

/**
 * @author xuhf
 * @date 2021/1/12 14:57
 **/
public class ReturnCash implements Cash {

    private Cash cash;
    private double fullMoney;
    private double returnMoney;

    public ReturnCash(double fullMoney, double returnMoney,Cash cash) {
        this.fullMoney = fullMoney;
        this.returnMoney = returnMoney;
        this.cash = cash;
    }

    @Override
    public double payCash(double money) {
        money = cash.payCash(money);
        if(money >= fullMoney){
            return money - returnMoney;
        }
        return money;
    }

}
