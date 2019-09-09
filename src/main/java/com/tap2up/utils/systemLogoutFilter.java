package com.tap2up.utils;

import com.tap2up.pojo.Users;
import com.tap2up.service.UserService;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Date;

/**
 * @Author: Han
 * @Date: 2019/9/9 14:36
 * @Description:
 */
@Service
public class systemLogoutFilter extends LogoutFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        //在这里执行退出系统前需要清空的数据

        Subject subject = getSubject(request, response);
        Users user = (Users) subject.getPrincipal();
        user.setEndtime(new Date());
        userService.update(user);


        //String redirectUrl = getRedirectUrl(request, response, subject);
        String redirectUrl="/login.jsp";
        System.out.println(redirectUrl);

        try {

            subject.logout();

        } catch (SessionException ise) {

            ise.printStackTrace();

        }

        issueRedirect(request, response, redirectUrl);
        //返回false表示不执行后续的过滤器，直接返回跳转到登录页面
        return false;
    }
}
