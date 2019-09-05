package com.tap2up.service;

import com.tap2up.mapper.UserInfoMapper;
import com.tap2up.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoService(UserInfoMapper userInfoMapper){
        this.userInfoMapper = userInfoMapper;
    }

    /**
     * 添加用户信息
     * @param userInfo 用户信息
     * @return
     */
    public int addUserInfo(UserInfo userInfo){
        return userInfoMapper.insertSelective(userInfo);
    }

    /**
     * 修改用户信息
     * @param userInfo 用户信息
     * @return
     */
    public int updateUserInfo(UserInfo userInfo){
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }


}
