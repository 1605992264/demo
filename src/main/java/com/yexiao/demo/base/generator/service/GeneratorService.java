package com.yexiao.demo.base.generator.service;


import com.yexiao.demo.base.generator.domain.Table;
import com.yexiao.demo.base.generator.mapper.GeneratorMapper;
import com.yexiao.demo.base.generator.utils.GeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author xuhf
 * @date 2020/8/27 17:07
 **/
@Service
public class GeneratorService  {

    @Autowired
    private GeneratorMapper generatorMapper;

    public Table generatorCode(String name, HttpServletResponse response) {
        Table table = generatorMapper.generatorCode(name);
        table.setDATE(LocalDate.now());
        table.setTIME(LocalTime.now());
        GeneratorUtils.FillTable(table);
        GeneratorUtils.generatorCode(table,response);
        return table;
    }

}
