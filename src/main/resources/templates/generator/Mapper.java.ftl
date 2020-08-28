package ${packageName!"com.yexiao.demo"}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${packageName!"com.yexiao.demo"}.domain.${className}DO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xuhf
* @date ${DATE} ${TIME}
**/
@Mapper
public interface ${className}Dao extends BaseMapper<${className}DO> {


}
