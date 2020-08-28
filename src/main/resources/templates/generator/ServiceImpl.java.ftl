package ${packageName!"com.yexiao.demo"}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${packageName!"com.yexiao.demo"}.domain.${className}DO;
import ${packageName!"com.yexiao.demo"}.mapper.${className}Mapper;
import ${packageName!"com.yexiao.demo"}.service.${className}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author xuhf
* @date ${DATE} ${TIME}
**/
@Service
@Transactional(rollbackFor = Exception.class)
public interface ${className}ServiceImpl extends IService<${className}DO> {


}
