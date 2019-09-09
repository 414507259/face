package com.tap2up.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tap2up.mapper.UserInfoMapper;
import com.tap2up.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserInfoService {

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoService(UserInfoMapper userInfoMapper){
        this.userInfoMapper = userInfoMapper;
    }

    public int addUserInfo(UserInfo userInfo){
        return userInfoMapper.insertSelective(userInfo);
    }

    public int updateUserInfo(UserInfo userInfo){
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }


    public PageInfo findUserInfoPreview(Integer pageNum, Integer pageSize, Integer type, String username, Integer groupId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map> userInfoPreview = userInfoMapper.findUserInfoPreview(type, username, groupId);
        PageInfo pageInfo = new PageInfo(userInfoPreview,5);
        return pageInfo;
    }

    public Map findUserInfoDetailById(Integer id) {
        return userInfoMapper.findUserInfoDetailById(id);
    }
}
