package com.yexiao.demo.conf.shiro;

import com.yexiao.demo.base.utils.UserUtils;
import com.yexiao.demo.domain.PermissionDO;
import com.yexiao.demo.domain.RoleDO;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.service.PermissionService;
import com.yexiao.demo.service.RoleService;
import com.yexiao.demo.service.UserService;
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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xuhf
 *
 * */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;


    /**
     * shiro拦截后调用
     * 权限验证(授权)
     * 当判断是否拥有权限时调用
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取用户所有的角色和权限 把它们添加到SimpleAuthorizationInfo中
        UserDO user = (UserDO) principals.getPrimaryPrincipal();
        List<RoleDO> roleList = roleService.getRoleList(user.getId());
        for(RoleDO roleDO : roleList){
            authorizationInfo.addRole(roleDO.getName());
            for(PermissionDO permissionDO : permissionService.getPermissionList(roleDO.getId())){
                authorizationInfo.addStringPermission(permissionDO.getName());
            }
        }
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
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        UserDO user = ((List<UserDO>) userService.listByMap(map)).get(0);
        if(user == null){
            return null;
        }
        // 设置token
        user.setToken(UserUtils.newToken(user.getPassword()));
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户名（对象）
                user,
                //密码
                user.getPassword(),
                //realm name
                getName()
        );
        return authenticationInfo;
    }
}
