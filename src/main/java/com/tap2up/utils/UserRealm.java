package com.tap2up.utils;

import com.tap2up.pojo.Users;
import com.tap2up.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: thinknovo
 * Date: 2019/05/14
 * Description:
 * Version: V1.0
 */
public class UserRealm extends AuthorizingRealm {

//    @Resource(name = "UserService")
//    private UserService userService;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Users user = null;
        UserService userService = new UserService();
        // 把AuthenticationToken实质为UsernamePasswordToken，直接转换即可
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        user = userService.getUser(usernamePasswordToken.getUsername());          // 通过service查询用户名是否存在
        if (user == null)
            throw new UnknownAccountException("用户不存在！");
        System.out.println("doGetAuthenticationInfo username=" + user.getUsername());
        System.out.println("doGetAuthenticationInfo password=" + user.getPassword());

        //  spring_database.xml文件中已经对此UserRealm bean对象设置了加密方式和次数，固这里无需重复配置，如果xml文件中没有配置，则需要代码配置
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("MD5");                      // 加密方式，与注册一致
//        hashedCredentialsMatcher.setHashIterations(11);                            // 加密次数，与注册一致
//        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);           // true是默认值，代表16机制值，如果设置false则为base64
//        setCredentialsMatcher(hashedCredentialsMatcher);                           // 保存加密设置
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());    // 以用户名为加密盐值
        String realmName = getName();                                              // 当前realm对象的name，调用父类的getName()方法即可
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, realmName);

        // 在没有加盐的情况下，三个参数就可以进行初步的简单认证信息对象的包装
//        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getSimpleName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}