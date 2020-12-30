package com.yexiao.demo.conf.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyLogicSqlInjector extends DefaultSqlInjector {

    /**
     * 如果只需增加方法，保留MP自带方法
     * 可以super.getMethodList() 再add
     * @return
     */
    @Override
    public List<AbstractMethod> getMethodList() {
        return (List) Stream.of(
                new MysqlInsert()
                , new Delete()
                , new DeleteByMap()
                , new DeleteById()
                , new DeleteBatchByIds()
                , new Update()
                , new UpdateById()
                , new SelectById()
                , new SelectBatchByIds()
                , new SelectByMap()
                , new SelectOne()
                , new SelectCount()
                , new SelectMaps()
                , new SelectMapsPage()
                , new SelectObjs()
                , new SelectList()
                , new SelectPage()).collect(Collectors.toList());
    }
}