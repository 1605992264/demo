package com.yexiao.demo.designModel.factory.simple;

/**
 * @author xuhf
 * @date 2021/1/11 11:16
 **/
public class SqlServerConnention implements DBConnection{

    @Override
    public void connection() {
        System.out.println("我是sqlServer的数据库连接");
    }
}
