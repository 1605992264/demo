package ${packageName!"com.yexiao.demo"}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${packageName!"com.yexiao.demo"}.domain.${className}DO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xuhf
* @date ${DATE} ${TIME}
**/
@Mapper
public interface ${className}Mapper extends BaseMapper<${className}DO> {

    /**
    * 物理删除
    * */
    Integer removeById(String id);
}
