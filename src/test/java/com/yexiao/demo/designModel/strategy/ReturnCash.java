package com.yexiao.demo.designModel.strategy;

/**
 * @author xuhf
 * @date 2021/1/12 14:57
 **/
public class ReturnCash implements Cash{

    private double fullMoney = 0f;
    private double returnMoney = 0f;

    public ReturnCash(double fullMoney, double returnMoney) {
        this.fullMoney = fullMoney;
        this.returnMoney = returnMoney;
    }

    @Override
    public double payCash(double money) {
        if(money >= fullMoney){
            return money - returnMoney;
        }
        return money;
    }

}
