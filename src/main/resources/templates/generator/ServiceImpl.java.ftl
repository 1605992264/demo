package ${packageName!"com.yexiao.demo"}.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper,${className}DO> implements ${className}Service {


    /**
    * 查询列表
    * @return*/
    @Override
    public IPage<${className}DO> page(IPage<${className}DO> basePage,${className}DO ${className?uncap_first}DO){
        QueryWrapper<${className}DO> wrapper = new QueryWrapper(${className?uncap_first}DO);
        IPage<${className}DO> page = baseMapper.selectPage(basePage, wrapper);
        return page;
    }

     /**
     * 物理删除
     * */
     @Override
     public boolean deleteById(String id) {
        if(baseMapper.removeById(id) >= 1){
            return true;
        }
        return false;
     }

}
