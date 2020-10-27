package com.yexiao.demo.controller;

import com.yexiao.demo.service.OfficeService;
import com.yexiao.demo.domain.OfficeDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yexiao.demo.base.domain.BasePage;
import com.yexiao.demo.base.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
* @author xuhf
* @date 2020-10-26 15:45:31.443
**/
@RestController
@RequestMapping("office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;


    @GetMapping("/list")
    public R list(BasePage<OfficeDO> basePage, OfficeDO officeDO){
        IPage<OfficeDO> list = officeService.page(basePage.newMybatisPlusPage(),officeDO);
        return R.success(list);
    }

    @GetMapping("/get")
    public R list(String id){
        OfficeDO office = officeService.getById(id);
        return R.success(office);
    }

    @PostMapping("/save")
    public R save(OfficeDO officeDO){
        if(officeService.save(officeDO)){
            return R.success("保存成功");
        }
        return R.error("保存失败");
    }

    @PostMapping("/update")
    public R update(OfficeDO officeDO){
        if( officeService.updateById(officeDO)) {
            return R.success("更新成功");
        }
        return R.error("更新失败");
    }

    @PostMapping("/remove")
    public R remove(String id){
        if(officeService.removeById(id)){
            return R.success("逻辑删除成功");
        }
        return R.error("逻辑删除失败");
    }

    @PostMapping("/delete")
    public R delete(String id){
        if(officeService.deleteById(id)){
            return R.success("物理删除成功");
        }
        return R.error("物理删除失败");
    }

}
