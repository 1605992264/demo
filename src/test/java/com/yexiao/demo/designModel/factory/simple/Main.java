package com.yexiao.demo.designModel.factory.simple;

/**
 * @author xuhf
 * @date 2021/1/11 11:20
 *
 *
 **/
public class Main {

    public static void main(String[] args) {
        DBConnection mysql = DBConnectionFactory.getInstance(DBEnum.mysql);
        mysql.connection();
        DBConnection sqlServer = DBConnectionFactory.getInstance(DBEnum.sqlServer);
        sqlServer.connection();
    }

}
