package com.tap2up.mapper;

import com.tap2up.pojo.AlfUserLibrary;


public interface AlfUserLibraryMapper {

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