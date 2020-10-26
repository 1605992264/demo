<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName!"com.yexiao.demo"}.mapper.${className}Mapper">

	<!-- 通用查询映射结果 -->
	<resultMap id="BaseResultMap" type="${packageName!"com.yexiao.demo"}.domain.${className}DO">
        <#list columns as column>
            <result column="${column.name}" property="${column.javaName}"/>
        </#list>
	</resultMap>

	<!-- 通用查询结果列 -->
	<sql id="Base_Column_List">
        <#list columns as column>
            ${tableName}.`${column.name}`<#if column_has_next>,</#if>
        </#list>
	</sql>

	<sql id="Map_Where_Clause" >
		<trim prefix="where" prefixOverrides="AND" >
            <#list columns as column>
                <if test="where.${column.javaName} != null and where.${column.javaName} != ''">
                    AND ${tableName}.`${column.name}` = ${r"#{"}where.${column.javaName}${r"}"}
                </if>
            </#list>
		</trim>
	</sql>

    <sql id="leftJion">
    </sql>

    <delete id="removeById">
        delete from ${tableName} where id = ${r"#{"}value${r"}"}
    </delete>

</mapper>