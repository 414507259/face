package com.tap2up.controller;

import com.tap2up.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * User: thinknovo
 * Date: 2019/05/14
 * Description:
 * Version: V1.0
 */
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        System.out.println("--------------------------UserController构造函数");
        this.userService = userService;
    }

    @RequestMapping(value = "/login.do")
    public String selectUser(@RequestParam String username, @RequestParam String password) {
        System.out.println("UserController --------------------------selectUser");
        System.out.println("UserController ------username=" + username);
        System.out.println("UserController ------password=" + password);

        Subject currentUser = SecurityUtils.getSubject();                                     // shiro权限认证主体对象
        if (!currentUser.isAuthenticated()) {                                                 // 满足shiro的可认证状态
            UsernamePasswordToken upToken = new UsernamePasswordToken(username, password);    // shiro权限认证类型
            upToken.setRememberMe(true);                                                     // 用户登录时效性
            try {
                currentUser.login(upToken);    // 调用realm认证用户权限
                return "redirect:/index.jsp";
            } catch (IncorrectCredentialsException ice) {
                System.out.println("用户名/密码不匹配！");
            } catch (LockedAccountException lae) {
                System.out.println("账户已被冻结！");
            } catch (UnknownAccountException uae) {
                System.out.println("账户不存在");
            } catch (AuthenticationException ae) {
                System.out.println(ae.getMessage());
            }
        }
        return "redirect:/login.jsp";
    }


    @RequestMapping(value = "/reg.do")
    public String regUser(@RequestParam String username, @RequestParam String password) {
        System.out.println("UserController --------------------------regUser");
        System.out.println("UserController ------username=" + username);
        System.out.println("UserController ------password=" + password);
        userService.regUser(username, md5(username, password));
        return "redirect:/login.jsp";
    }

    // 注册时，进行shiro加密，返回加密后的结果，如果在加入shiro之前，存在用户密码不是此方式加密的，那么将无法登录
    // 使用用户名作为盐值
    private String md5(String username, String password){
        String hashAlgorithmName = "MD5";                   // 加密方式
        ByteSource salt = ByteSource.Util.bytes(username);  // 以账号作为盐值
        int hashIterations = 11;                            // 加密11次
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }
}