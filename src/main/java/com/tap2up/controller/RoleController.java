package com.tap2up.controller;

import com.tap2up.pojo.Role;
import com.tap2up.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Han
 * @Date: 2019/9/5 13:36
 * @Description:
 */
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 给角色添加权限
     * @param pmIds 权限id列表
     * @param rId 角色id
     * @return 是否成功
     */
    @RequestMapping("/addPermissions")
    public String addPermissions(@RequestBody List<Integer> pmIds, int rId){
        int n =roleService.addPermissions(pmIds, rId);
        if (n > 0){
            return "成功";
        }
        return "添加失败";
    }

    /**
     * 删除角色的权限
     * @param pmIds 权限id列表
     * @param rId 角色id
     * @return 是否成功
     */
    @RequestMapping("/deletePermissions")
    public String deletePermissions(@RequestBody List<Integer> pmIds, int rId){
        int n = roleService.deletePermissions(pmIds, rId);
        if(n >0){
            return "成功";
        }
        return "失败";

    }

    /**
     * 添加角色
     * @param role 角色id
     * @return 是否成功
     */
    @RequestMapping("/addRole")
    public String addRole(Role role){
        int n = roleService.addRole(role);
        if(n >0){
            return "成功";
        }
        return "失败";
    }

    /**
     * 修改角色
     * @param role 角色
     * @return 是否成功
     */
    @RequestMapping("/updateRole")
    public String updateRole(Role role){
        int n = roleService.updateRole(role);
        if(n >0){
            return "成功";
        }
        return "失败";
    }


    /**
     * 通过用户id查找角色
     * @param uId 用户id
     * @return 角色列表
     */
    @RequestMapping("/findRoleByUid")
    public List<Role> findRoleByUid(int uId){
        List<Role> roleList = roleService.findRoleByUid(uId);
        if(roleList.size() == 0){
            return null;
        }
        return roleList;
    }

    /**
     * 删除角色 （假删除）
     * @param role 角色
     * @return 是否成功
     */
    @RequestMapping("/deleteRole")
    public String deleteRole(Role role){
        role.setIsdelete(1);
        int n = roleService.updateRole(role);
        if(n >0){
            return "成功";
        }
        return "删除失败";
    }

    /**
     * 通过用户id查找该用户未拥有的角色
     * @param uId 用户id
     * @return 该用户未拥有的角色列表
     */
    @RequestMapping("/findRestRoleByUid")
    public List<Role> findRestRoleByUid(int uId){
        return roleService.findRestRoleByUid(uId);
    }

    /**
     * 查找所有角色
     * @return 角色列表
     */
    @RequestMapping("/findAll")
    public List<Role> findAll(){
        return roleService.findAll();
    }
}
