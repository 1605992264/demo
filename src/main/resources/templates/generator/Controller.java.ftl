package ${packageName!"com.yexiao.demo"}.service.impl;

import ${packageName!"com.yexiao.demo"}.service.${className}Service;
import ${packageName!"com.yexiao.demo"}.domain.${className}DO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yexiao.demo.base.domain.BasePage;
import com.yexiao.demo.base.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author xuhf
* @date ${DATE} ${TIME}
**/
@RestController
@RequestMapping("${className?uncap_first}")
public class ${className}Controller {

    @Autowired
    private ${className}Service ${className?uncap_first}Service;


    @GetMapping("/list")
    public R list(BasePage<${className}DO> basePage, ${className}DO ${className?uncap_first}DO){
        IPage<${className}DO> list = ${className?uncap_first}Service.page(basePage.newMybatisPlusPage(),${className?uncap_first}DO);
        return R.success(list);
    }

    @GetMapping("/get")
    public R list(String id){
        ${className}DO ${className?uncap_first} = ${className?uncap_first}Service.getById(id);
        return R.success(${className?uncap_first});
    }

    @PostMapping("/save")
    public R save(${className}DO ${className?uncap_first}DO){
        if(${className?uncap_first}Service.save(${className?uncap_first}DO)){
            return R.success("保存成功");
        }
        return R.error("保存失败");
    }


    @PostMapping("/update")
    public R add(${className}DO ${className?uncap_first}DO){
        if( ${className?uncap_first}Service.updateById(${className?uncap_first}DO)) {
            return R.success("更新成功");
        }
        return R.error("更新失败");
    }

    @PostMapping("/remove")
    public R remove(String id){
        if(${className?uncap_first}Service.removeById(id)){
            return R.success("逻辑删除成功");
        }
        return R.error("逻辑删除失败");
    }

    @PostMapping("/delete")
    public R delete(String id){
        if(${className?uncap_first}Service.deleteById(id)){
            return R.success("物理删除成功");
        }
        return R.error("物理删除失败");
    }

}
