package com.tap2up.controller;

import com.github.pagehelper.PageInfo;
import com.tap2up.pojo.AlfUserLibrary;
import com.tap2up.pojo.UserInfo;
import com.tap2up.service.AlfService;
import com.tap2up.service.UserInfoService;
import com.tap2up.utils.IdCardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/userInfo")
@RestController
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AlfService alfService;

    /**
     * 添加用户信息
     * @param userInfo 用户信息
     * @return 是否成功
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
     * @param userInfo 用户信息
     * @return 是否成功
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
     * @param type 用户类型
     * @param username 用户姓名
     * @param groupId 组id
     * @return 分页后的信息
     */
    @RequestMapping("/findEmployeePreview")
    public PageInfo findUserInfoPreview(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                                        @RequestParam(required = false,defaultValue = "10",value = "pageSize") Integer pageSize,
                                        @RequestParam(required = false,defaultValue = "0",value = "type") Integer type,
                                            String username, Integer groupId ){
        return userInfoService.findUserInfoPreview(pageNum, pageSize, type, username, groupId);
    }

    /**
     * 通过人脸库用户id查找员工详细信息
     * @param id 人脸库用户id
     * @return 员工详细信息
     */
    @RequestMapping("/findEmployeeDetailById")
    public Map findUserInfoDetailById(Integer id, Integer type){
        return userInfoService.findUserInfoDetailById(id, type);
    }

    /**
     * 添加员工
     * @param alfUserLibrary 人脸库用户信息
     * @param userInfo 用户信息
     * @return 是否成功
     */
    @RequestMapping("/addEmployee")
    public String addEmployee(AlfUserLibrary alfUserLibrary, UserInfo userInfo){
        //从身份证号码中解析出生日和年龄
        String idNumber = alfUserLibrary.getIdnumber();
        if(idNumber != null && idNumber.length() >= 15){
            Map<String, String> idInfo = IdCardUtil.getBirAgeSex(idNumber);
            alfUserLibrary.setBirthday(idInfo.get("birthday"));
            userInfo.setAge(Integer.parseInt(idInfo.get("age")));
        }
        //通过用户组名获取用户组id
        String groupName = alfUserLibrary.getGroupname();
        Integer groupId = userInfoService.findGroupIdByGroupName(groupName);
        alfUserLibrary.setGroupid(groupId);
        Integer id = alfService.addStaff(alfUserLibrary);
        if(id < 0){
            return "失败";
        }
        userInfo.setId(id);
        int n = userInfoService.addUserInfo(userInfo);
        if(n < 1){
            return "失败";
        }
        return "成功";
    }

    /**
     * 修改员工信息
     * @param alfUserLibrary 人脸库用户信息
     * @param userInfo 用户信息
     * @return 是否成功
     */
    @RequestMapping("/updateEmployee")
    public String updateEmployee(AlfUserLibrary alfUserLibrary, UserInfo userInfo){
        //通过用户组名获取用户组id
        String groupName = alfUserLibrary.getGroupname();
        Integer groupId = userInfoService.findGroupIdByGroupName(groupName);
        alfUserLibrary.setGroupid(groupId);
        int n0 = alfService.updateAlf(alfUserLibrary);
        int n1 = userInfoService.updateUserInfo(userInfo);
        if(n0 < 1){
            return "失败";
        }
        if(n1 < 1){
            return "失败";
        }
        return "成功";
    }

    /**
     * 删除员工 （假删除）
     * @param userInfo 员工信息 （只需要infoId）
     * @return 是否成功
     */
    @RequestMapping("/deleteEmployee")
    public String deleteEmployee(UserInfo userInfo){
        userInfo.setIsdelete(1);
        int n = userInfoService.updateUserInfo(userInfo);
        if(n < 1){
            return "失败";
        }
        return "成功";
    }

}
