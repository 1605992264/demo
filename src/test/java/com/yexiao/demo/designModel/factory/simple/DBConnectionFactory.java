package com.yexiao.demo.designModel.factory.simple;

/**
 * @author xuhf
 * @date 2021/1/11 11:18
 * 实例
 **/
public class DBConnectionFactory {

    public static DBConnection getInstance(DBEnum dbEnum){
        switch (dbEnum){
            case mysql:
                return new MysqlConnection();
            case sqlServer:
                return new SqlServerConnention();
            default: return null;
        }
    }

}
