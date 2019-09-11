package com.tap2up.service;

import com.tap2up.mapper.PermissionMapper;
import com.tap2up.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionService(PermissionMapper permissionMapper){
        this.permissionMapper = permissionMapper;
    }

    public List<Permission> findPermissionsByRoleName(String rolename) {
        return permissionMapper.selectByRolename(rolename);
    }

    public int addPermission(Permission permission) {
        return permissionMapper.insertSelective(permission);
    }

    public List<Permission> findPermissionByRid(int rId) {
        return permissionMapper.selectByRid(rId);
    }

    public int updatePermission(Permission permission) {
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }

    public List<Permission> findAllPermission(){
        return permissionMapper.selectAll();
    }
}
