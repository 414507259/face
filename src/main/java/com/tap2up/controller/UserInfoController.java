package com.tap2up.controller;

import com.tap2up.pojo.UserInfo;
import com.tap2up.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;

    @RequiresPermissions("test")
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }

    @RequestMapping("/addUserInfo")
    public String addUserInfo(UserInfo userInfo){
        int n = userInfoService.addUserInfo(userInfo);
        if(n > 0){
            return "添加成功";
        }
        return "添加失败";
    }

    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(UserInfo userInfo){
        int n = userInfoService.updateUserInfo(userInfo);
        if(n > 0){
            return "修改成功";
        }
        return "修改失败";
    }
}
