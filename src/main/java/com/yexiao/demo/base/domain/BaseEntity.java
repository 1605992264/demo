package com.yexiao.demo.base.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * @author xuhf
 * @date 2020/8/20 10:55
 **/
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
