package com.yexiao.demo.conf.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yexiao.demo.base.utils.UserUtils;
import com.yexiao.demo.domain.UserDO;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

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
        if(this.getFieldValByName("createBy",metaObject) == null && UserUtils.getUser() != null){
            this.setFieldValByName("createBy", UserUtils.getUser().getId(), metaObject);
        }
        if(this.getFieldValByName("createDate",metaObject) == null) {
            this.setFieldValByName("createDate", System.currentTimeMillis(), metaObject);
        }
        if(this.getFieldValByName("updateBy",metaObject) == null && UserUtils.getUser() != null){
            this.setFieldValByName("updateBy", UserUtils.getUser().getId(),metaObject);
        }
        if(this.getFieldValByName("updateDate",metaObject) == null) {
            this.setFieldValByName("updateDate", System.currentTimeMillis(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        UserDO user = UserUtils.getUser();
        if(user == null){
            user = new UserDO();
        }
        if(this.getFieldValByName("updateBy",metaObject) == null && UserUtils.getUser() != null){
            this.setFieldValByName("updateBy", UserUtils.getUser().getId(),metaObject);
        }
        if(this.getFieldValByName("updateDate",metaObject) == null) {
            this.setFieldValByName("updateDate", System.currentTimeMillis(), metaObject);
        }
    }
}
