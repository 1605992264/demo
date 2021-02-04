package ${packageName!"com.yexiao.demo"}.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.util.Date;
<#if extendsUserInfoBaseEntity == false>
import com.yexiao.demo.base.domain.BaseEntity;
<#else>
import com.yexiao.demo.base.domain.UserInfoBaseEntity;
</#if>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @author xuhf
* @date ${DATE} ${TIME}
**/
@ApiModel(value = "${className}对象", description = "${tableComment}")
@TableName("${tableName}")
public class ${className}DO extends <#if extendsUserInfoBaseEntity == false>BaseEntity<#else>UserInfoBaseEntity</#if>{
<#list columns as column>
    <#if column.javaName != 'id'>
    <#if column.javaName == 'deleteFlag'>

    /**
     * ${column.comment}
     */
    @ApiModelProperty(value = "删除标识")
    @TableField(value = "delete_flag")
    @TableLogic(value = "0",delval = "1")
    private Integer deleteFlag;
    <#elseif extendsUserInfoBaseEntity= false>

    /**
     * ${column.comment}
     */
    @ApiModelProperty("${column.comment}")
    @TableField("${column.name}")
    private ${column.javaType} ${column.javaName};
    <#elseif column.javaName != 'createDate' && column.javaName != 'createBy' && column.javaName != 'updateDate' && column.javaName != 'updateBy' >

    /**
    * ${column.comment}
    */
    @ApiModelProperty("${column.comment}")
    @TableField("${column.name}")
    private ${column.javaType} ${column.javaName};
    </#if>
    </#if>
</#list>


}
