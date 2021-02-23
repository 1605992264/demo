package com.yexiao.demo.controller;

import com.yexiao.demo.base.domain.R;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author xuhf
 * @date 2021/2/19 16:08
 **/
@RestController
@RequestMapping("/valid")
@Validated
public class ValidController {

    @RequestMapping("/notNull")
    public R valid(@NotNull String id){
        return R.success();
    }






}
