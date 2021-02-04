package com.yexiao.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yexiao.demo.domain.LogDO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xuhf
* @date 2021-02-04 15:15:55.730
**/
@Mapper
public interface LogMapper extends BaseMapper<LogDO> {

    /**
    * 物理删除
    * */
    Integer removeById(String id);
}
