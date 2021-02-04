package com.yexiao.demo.designModel.factory.simple;

/**
 * @author xuhf
 * @date 2021/1/11 11:15
 **/
public class MysqlConnection implements DBConnection {


    @Override
    public void connection() {
        System.out.println("我是mysql的数据库连接");
    }
}
