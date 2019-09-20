package com.tap2up.mapper;

import com.tap2up.pojo.AlfUserLibrary;

import java.util.List;
import java.util.Map;


public interface AlfUserLibraryMapper {

    List<Map> groupInfo(Integer groupId);

    /**
     * 获取用户组列表
     */
    List<Map> group();

    /**
     * 设备登录
     */
    int login(String account,String password);

     AlfUserLibrary getAlfUserLibrarybyUserId(Integer userId);

     int getByGroupId(Integer groupId);
    int deleteByPrimaryKey(Integer id);

    int insert(AlfUserLibrary record);

    int insertSelective(AlfUserLibrary record);

    AlfUserLibrary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlfUserLibrary record);

    int updateByPrimaryKey(AlfUserLibrary record);
}