package ${packageName!"com.yexiao.demo"}.service.impl;

import com.alibaba.fastjson.JSONObject;
import ${packageName!"com.yexiao.demo"}.service.${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* @author xuhf
* @date ${DATE} ${TIME}
**/
@RestController
@RequestMapping("user")
public class ${className}Controller {

    @Autowired
    private ${className}Service ${className?uncap_first}Service;

}
