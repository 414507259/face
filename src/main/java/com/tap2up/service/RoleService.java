package com.tap2up.service;

import com.tap2up.mapper.RoleMapper;
import com.tap2up.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleMapper roleMapper){
        this.roleMapper = roleMapper;
    }

    public List<Role> findRoleListByUsername(String username) {
        return roleMapper.selectByUsername(username);
    }

    public int addPermissions(List<Integer> pmIds, int rId) {
        return roleMapper.addPermissions(pmIds, rId);
    }

    public int deletePermissions(List<Integer> pmIds, int rId) {
        return roleMapper.deletePermissions(pmIds, rId);
    }

    public int addRole(Role role) {
        return roleMapper.insertSelective(role);
    }

    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    public List<Role> findRoleByUid(int uId) {
        return roleMapper.findRoleByUid(uId);
    }

    public List<Role> findAll() {
        return roleMapper.selectAll();
    }

    public List<Role> findRestRoleByUid(int uId) {
        List<Role> allRoles = this.findAll();
        List<Role> ownRoles = this.findRoleByUid(uId);
        for (Role role : ownRoles){
            allRoles.remove(role);
        }
        return allRoles;
    }

    public Integer findRidByRoleName(String roleName){
        return roleMapper.selectRoleIdByRoleName(roleName);
    }

    public int isRelationExisted(Integer roleId, Integer userId) {
        return roleMapper.isRelationExisted(roleId, userId);
    }
}
