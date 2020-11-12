package com.yexiao.demo.model.strategy;

/**
 * @author xuhf
 * @date 2020/11/5 10:44
 * 满减
 **/
public class FullReduction extends Pay {

    private double fullMoney;
    private double reductionMoney;
    private Pay pay;

    public FullReduction(Pay pay,double fullMoney,double reductionMoney){
        this.fullMoney = fullMoney;
        this.reductionMoney = reductionMoney;
        this.pay = pay;
    }

    @Override
    public double pay(double money, int num) {
        double moneyPay = this.pay.pay(money, num);
        int reduction = (int) (moneyPay/ fullMoney);
        return moneyPay - (reduction * reductionMoney) ;
    }
}
