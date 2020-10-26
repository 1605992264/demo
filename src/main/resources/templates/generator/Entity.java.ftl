package ${packageName!"com.yexiao.demo"}.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
<#if extendsUserInfoBaseEntity == false>
import com.yexiao.demo.base.domain.BaseEntity;
<#else>
import com.yexiao.demo.base.domain.UserInfoBaseEntity;
</#if>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author xuhf
* @date ${DATE} ${TIME}
**/
@ApiModel(value = "${className}对象", description = "${tableComment}")
@Data
@Accessors(chain = true)
@TableName("${tableName}")
public class ${className}DO extends BaseEntity {
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
