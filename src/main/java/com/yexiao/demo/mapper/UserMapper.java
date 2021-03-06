package com.yexiao.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yexiao.demo.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author xuhf
 * @date 2020/8/20 10:48
 **/
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 物理删除
     * */
    Integer removeById(String id);

    /**
     * 验证用户名唯一性
     * */
    Integer verificationUserName(String name);

    UserDO findUserInfo(@Param("userId") String userId);

    UserDO findUserByName(String name);

}
