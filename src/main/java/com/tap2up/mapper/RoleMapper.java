package com.tap2up.mapper;

import com.tap2up.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByUsername(@Param("username") String username);

    int addPermissions(@Param("pmIds") List<Integer> pmIds, @Param("rId") int rId);

    int deletePermissions(@Param("pmIds") List<Integer> pmIds, @Param("rId") int rId);

    List<Role> findRoleByUid(int uId);

    List<Role> selectAll();

    Integer selectRoleIdByRoleName(String roleName);

    int isRelationExisted(@Param("roleId") Integer roleId, @Param("userId") Integer userId);
}