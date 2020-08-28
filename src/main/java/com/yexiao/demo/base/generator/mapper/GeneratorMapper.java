package com.yexiao.demo.base.generator.mapper;

import com.yexiao.demo.base.generator.domain.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xuhf
 * @date 2020/8/27 17:08
 **/
@Mapper
public interface GeneratorMapper {

    Table generatorCode(String tableName);

}
