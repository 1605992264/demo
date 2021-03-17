package com.yexiao.demo.conf.security.shiro;

import com.yexiao.demo.domain.PermissionDO;
import com.yexiao.demo.domain.RoleDO;
import com.yexiao.demo.domain.UserDO;
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


/**
 * @author xuhf
 *
 * */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

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
        UserDO userInfo = userService.findUserInfo(user.getId());
        // 为用户添加角色
        for(RoleDO roleDO : userInfo.getRoleList()){
            authorizationInfo.addRole(roleDO.getName());
            // 为用户添加权限
            for(PermissionDO permissionDO : roleDO.getPermissionList()){
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
        UserDO user = userService.findUserByUserName(username);
        if(user == null){
            return null;
        }
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
