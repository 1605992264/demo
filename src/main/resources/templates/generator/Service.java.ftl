package ${packageName!"com.yexiao.demo"}.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import ${packageName!"com.yexiao.demo"}.domain.${className}DO;

/**
* @author xuhf
* @date ${DATE} ${TIME}
**/
public interface ${className}Service extends IService<${className}DO> {

    IPage<${className}DO> page(IPage<${className}DO> basePage,${className}DO ${className?uncap_first}DO);

    /**
    * 物理删除
    * */
    boolean deleteById(String id);

}
