package com.yexiao.demo.designModel.decoration;

/**
 * @author xuhf
 * @date 2021/1/13 16:09
 **/
public class DefaultCash implements Cash {

    @Override
    public double payCash(double money) {
        return money;
    }
}
