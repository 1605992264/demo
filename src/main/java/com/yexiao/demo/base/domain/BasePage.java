package com.yexiao.demo.base.domain;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xuhf
 * @date 2020/9/2 10:01
 **/
@Data
public class BasePage<T> {

    @ApiModelProperty(value = "一页多少条数据")
    private Long pageSize = 10L;
    @ApiModelProperty(value = "当前页数")
    private Long pageNo = 1L;

    /**
     * mybatis plus 分页
     * */
    public Page<T> newMybatisPlusPage(){
        Page<T> page = new Page<>();
        page.setCurrent(this.pageNo);
        page.setSize(this.pageSize);
        return page;
    }

}
