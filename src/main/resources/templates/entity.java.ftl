package ${package!"com.yexiao"}.modules.domain;

import java.io.Serializable;
<#if hasDate == true>
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
</#if>

public class ${className}DO implements Serializable {

    <#list columns as column>
        <#if column.javaType == "Date">
        @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        </#if>
        private ${column.javaType} ${column.javaName}; // ${column.comment}
    </#list>

    <#list columns as column>
        public ${column.javaType} get${column.attrName}{
            return this.${column.javaName};
        }

        public ${column.javaType} set${column.attrName}(${column.javaType} ${column.javaName}){
            this.${column.javaName} = ${column.javaName}
        }

    </#list>
}
