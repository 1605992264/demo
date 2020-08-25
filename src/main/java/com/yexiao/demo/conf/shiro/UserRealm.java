package com.yexiao.demo.conf.shiro;

import com.yexiao.demo.domain.UserDO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author xuhf
 *
 * */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * shiro拦截后调用
     * 权限验证(授权)
     * 当判断是否拥有权限时调用
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取用户所有用的角色和权限 把它们添加到SimpleAuthorizationInfo中
//        User userInfo  = (User) principals.getPrimaryPrincipal();
//        for(Role role:userInfo.getRoleList()){
//            authorizationInfo.addRole(role.getRoleName());
//            /* 添加权限
//            for(SysPermission p:role.getPermissions()){
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//            */
//        }
        return authorizationInfo;
    }


    /**
     * 登录验证
     * 调用Subject.login(token) 时调用
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        //从数据库中查询user User userInfo = userService.getByName(username);
        UserDO userInfo = new UserDO();
        /**
         * 从数据库中查询数据
         * */
        userInfo.setName("tom");
        userInfo.setPassword("3f924a3b2d9ed860548357e00e3b060a");
        if(userInfo == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户名（对象）
                userInfo,
                //密码
                userInfo.getPassword(),
                //realm name
                getName()
        );
        redisTemplate.opsForValue().set("user",userInfo,1, TimeUnit.MINUTES);
        return authenticationInfo;
    }
}
