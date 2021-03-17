package com.yexiao.demo.conf.security.shiro;

import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.utils.UserUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * @author xuhf
 * @date 2020/12/30 11:13
 *
 * 自定义shiro 登入验证
 **/
public class MyCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        SimplePrincipalCollection simplePrincipalCollection = ((SimplePrincipalCollection) info.getPrincipals());
        UserDO user = (UserDO) simplePrincipalCollection.asList().get(0);
        if(usernamePasswordToken.getUsername().equals(user.getUsername())){
            if(usernamePasswordToken.getPassword().equals(user)){
                return true;
            }else {
                String s = UserUtils.newPassword(String.valueOf(usernamePasswordToken.getPassword()));
                if(s.equals(user.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }

}
