package com.yexiao.demo.base.generator.controller;

import com.yexiao.demo.base.domain.R;
import com.yexiao.demo.base.generator.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xuhf
 * @date 2020/8/27 17:05
 **/
@RestController
@RequestMapping("generator")
public class GeneratorController {

    @Autowired
    private GeneratorService service;

    @GetMapping("code")
    public R code(@RequestParam String tableName, HttpServletResponse response){
        return R.success(service.generatorCode(tableName,response));
    }


}
