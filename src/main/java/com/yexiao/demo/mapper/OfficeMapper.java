package com.yexiao.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yexiao.demo.domain.OfficeDO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xuhf
* @date 2020-10-26 15:38:18.574
**/
@Mapper
public interface OfficeMapper extends BaseMapper<OfficeDO> {

    /**
    * 物理删除
    * */
    Integer removeById(String id);
}
