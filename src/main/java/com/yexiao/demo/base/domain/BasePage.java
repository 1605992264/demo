package com.yexiao.demo.base.domain;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author xuhf
 * @date 2020/9/2 10:01
 **/
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

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}
