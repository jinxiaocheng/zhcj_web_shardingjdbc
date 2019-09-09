package com.escst.commons.shiro;

import com.escst.commons.utils.MD5Util;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * @author caozx
 * @desc
 * @date 2017/4/26 12:52
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        boolean flag = false;
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        Object accountCredentials  = getCredentials(info);
        String password = String.valueOf(usernamePasswordToken.getPassword());
        String username = usernamePasswordToken.getUsername();
        if(password.length() == 32) {
            flag = equals(password,accountCredentials);
            return flag;
        }
        try {
            password = MD5Util.md5Encode(username+password);
            flag = equals(password,accountCredentials);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.doCredentialsMatch(token, info);
    }
}
