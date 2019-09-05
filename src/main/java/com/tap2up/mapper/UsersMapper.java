package com.tap2up.mapper;

import com.tap2up.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users getUser(String username);

    int addRoles(@Param("roleIds") List<Integer> roleIds, @Param("uId") int uId);

    int deleteRoles(@Param("roleIds") List<Integer> roleIds, @Param("uId") int uId);
}