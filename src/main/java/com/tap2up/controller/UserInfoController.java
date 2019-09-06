package com.tap2up.controller;

import com.github.pagehelper.PageInfo;
import com.tap2up.pojo.AlfUserLibrary;
import com.tap2up.pojo.UserInfo;
import com.tap2up.service.AlfService;
import com.tap2up.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AlfService alfService;

    @RequiresPermissions("test")
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }

    /**
     * 添加用户信息
     * @param userInfo 用户信息
     * @return
     */
    @RequestMapping("/addUserInfo")
    public String addUserInfo(UserInfo userInfo){
        int n = userInfoService.addUserInfo(userInfo);
        if(n > 0){
            return "添加成功";
        }
        return "添加失败";
    }

    /**
     * 修改用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping("/updateUserInfo")
    public String updateUserInfo(UserInfo userInfo){
        int n = userInfoService.updateUserInfo(userInfo);
        if(n > 0){
            return "修改成功";
        }
        return "修改失败";
    }

    /**
     * 查找员工按分页
     * @param pageNum 当前页码
     * @param pageSize 每页显示条数
     * @param username 用户姓名
     * @param groupId 组id
     * @return
     */
    @RequestMapping("/findEmployeePreview")
    public PageInfo findUserInfoPreview(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                                        @RequestParam(required = false,defaultValue = "10",value = "pageSize") Integer pageSize,
                                        String username, Integer groupId){
        PageInfo pageInfo =userInfoService.findUserInfoPreview(pageNum, pageSize, username, groupId);
        return pageInfo;
    }

    /**
     * 通过人脸库用户id查找员工详细信息
     * @param id 人脸库用户id
     * @return
     */
    @RequestMapping("/findEmployeeDetailById")
    public Map findUserInfoDetailById(Integer id){
        Map userInfoDetail = userInfoService.findUserInfoDetailById(id);
        return userInfoDetail;
    }

    @RequestMapping("/addEmployee")
    public String addEmployee(AlfUserLibrary alfUserLibrary, UserInfo userInfo){
        Integer id = alfService.addStaff(alfUserLibrary);
        if(id == null){
            return "失败";
        }
        userInfo.setId(id);
        int n = userInfoService.addUserInfo(userInfo);
        if(n < 1){
            return "失败";
        }
        return "成功";
    }

    @RequestMapping("/updateEmployee")
    public String updateEmployee(AlfUserLibrary alfUserLibrary, UserInfo userInfo){
        int n0 = 1;
        int n1 = userInfoService.updateUserInfo(userInfo);
        if(n0 < 1){
            return "失败";
        }
        if(n1 < 1){
            return "失败";
        }
        return "成功";
    }

}
