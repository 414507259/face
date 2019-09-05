package com.tap2up.mapper;

import com.tap2up.pojo.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer infoid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer infoid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}