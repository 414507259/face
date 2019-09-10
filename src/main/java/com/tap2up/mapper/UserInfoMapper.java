package com.tap2up.mapper;

import com.tap2up.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer infoid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer infoid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<Map> findUserInfoPreview(@Param("type") Integer type, @Param("username") String username, @Param("groupId") Integer groupId);

    Map findUserInfoDetailById(@Param("id") Integer id, @Param("type") Integer type);

    Integer findGroupIdByGroupName(String groupName);
}