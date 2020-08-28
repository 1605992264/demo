package ${packageName!"com.yexiao.demo"}.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yexiao.demo.base.domain.BaseEntity;
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
public class ${className}DO implements BaseEntity {

<#list columns as column>
    private ${column.javaType} ${column.javaName}; // ${column.comment}

</#list>

<#list columns as column>
    public ${column.javaType} get${column.javaName?cap_first}{
        return this.${column.javaName};
    }

    public ${column.javaType} set${column.javaName?cap_first}(${column.javaType} ${column.javaName}){
        this.${column.javaName} = ${column.javaName}
    }

</#list>
}
