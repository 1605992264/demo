package com.yexiao.demo.conf.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.mapping.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class MysqlInsert extends Insert {

    @Override
    protected MappedStatement addMappedStatement(Class<?> mapperClass, String id, SqlSource sqlSource, SqlCommandType sqlCommandType, Class<?> parameterClass, String resultMap, Class<?> resultType, KeyGenerator keyGenerator, String keyProperty, String keyColumn) {
        String statementName = mapperClass.getName() + "." + id;
        if (this.configuration.hasStatement(statementName, false)) {
            //System.err.println("{" + statementName + "} Has been loaded by XML or SqlProvider, ignoring the injection of the SQL.");
            return null;
        } else {
            boolean isSelect = false;
            if (sqlCommandType == SqlCommandType.SELECT) {
                isSelect = true;
            }
            return this.builderAssistant.addMappedStatement(id, sqlSource, StatementType.PREPARED, sqlCommandType, (Integer)null, (Integer)null, (String)null, parameterClass, resultMap, resultType, (ResultSetType)null, !isSelect, isSelect, false, keyGenerator, keyProperty, keyColumn, this.configuration.getDatabaseId(), this.languageDriver, (String)null);
        }
    }

}