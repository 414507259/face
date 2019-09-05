package com.tap2up.controller;

import com.tap2up.pojo.Permission;
import com.tap2up.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Han
 * @Date: 2019/9/5 13:36
 * @Description:
 */
@RequestMapping("/permission")
@RestController
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 添加权限
     * @param permission 权限
     * @return
     */
    @RequestMapping("/addPermission")
    public String addPermission(Permission permission){
        int n =permissionService.addPermission(permission);
        if(n > 0){
            return "成功";
        }
        return "添加失败";
    }

    /**
     * 通过角色id查找权限
     * @param rId 角色id
     * @return
     */
    @RequestMapping("/findPermissionByRid")
    public List<Permission> findPermissionByRid(int rId){
        List<Permission> permissionList = permissionService.findPermissionByRid(rId);
        if(permissionList.size() ==0 || permissionList == null){
            return null;
        }
        return permissionList;
    }

    /**
     * 修改权限
     * @param permission
     * @return
     */
    @RequestMapping("/updatePermission")
    public String updatePermission(Permission permission){
        int n = permissionService.updatePermission(permission);
        if(n > 0){
            return "成功";
        }
        return "修改失败";
    }

    /**
     * 删除权限（假删除）
     * @param permission 权限
     * @return
     */
    @RequestMapping("/deletePermission")
    public String deletePermission(Permission permission){
        permission.setIsdelete(1);
        int n = permissionService.updatePermission(permission);
        if(n > 0){
            return "成功";
        }
        return "删除失败";
    }

}
