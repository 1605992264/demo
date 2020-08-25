package com.yexiao.demo.proxy;


import com.yexiao.demo.domain.UserDO;
import org.springframework.stereotype.Component;

@Component
public class ProxyService implements ProxyDao {

    @Override
    public int save(UserDO userDO) {
        System.out.println("保存");
        return 0;
    }

}
