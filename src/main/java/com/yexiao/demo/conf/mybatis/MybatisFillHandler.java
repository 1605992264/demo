package com.yexiao.demo.conf.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.utils.UserUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xuhf
 * @date 2020/8/26 16:37
 * 自动填充
 **/
@Component
public class MybatisFillHandler implements MetaObjectHandler {

    /**
     * 如果属性有值则不覆盖,如果填充值为null则不填充
     * */

    @Override
    public void insertFill(MetaObject metaObject) {
        UserDO user = UserUtils.getUser();
        if(user == null){
            user = new UserDO();
            user.setId("-1");
        }
        this.strictInsertFill(metaObject, "createBy", String.class,user.getId());
        this.strictInsertFill(metaObject, "createDate", Date.class,new  Date());
        this.strictInsertFill(metaObject, "updateBy", String.class,user.getId());
        this.strictInsertFill(metaObject, "updateDate", Date.class,new  Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        UserDO user = UserUtils.getUser();
        if(user == null){
            user = new UserDO();
            user.setId("-1");
        }
        this.strictInsertFill(metaObject, "updateBy", String.class,user.getId());
        this.strictInsertFill(metaObject, "updateDate", Date.class,new  Date());
    }
}
