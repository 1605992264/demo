package com.yexiao.demo.proxy;


import com.yexiao.demo.domain.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ProxyTest {

    @Autowired
    private ProxyService proxyService;

    /**
     * 动态代理
     * */
    @Test
    public void test() {
        ProxyDao proxyDao =(ProxyDao) Proxy.newProxyInstance(this.getClass().getClassLoader(), ProxyService.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if("save".equals(method.getName())){
                    System.out.println(args[0]);
                    System.out.println("生成实例");
                    System.out.println("生成开始任务");
                    Object invoke = null;
                    try {
                        invoke = method.invoke(proxyService, args);
                    }catch (Exception e){
                        new RuntimeException();
                    }
                    System.out.println("完成任务");
                    return invoke;
                }
                return null;
            }
        });
        UserDO user = new UserDO();
        user.setName("测试");
        proxyDao.save(user);
    }

}
