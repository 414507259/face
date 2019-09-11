package com.tap2up.service;

import com.tap2up.mapper.UsersMapper;
import com.tap2up.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/08/12 11:04
 * Description:
 * Version: V1.0
 */
@Service
public class UserService {
    private final UsersMapper usersMapper;

    @Autowired
    public UserService(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    public Users getUser(String username){
        Users users = usersMapper.getUser(username);
        System.out.println(users);
        return users;
    }

    /**
     * 注册用户
     * @param users 用户信息
     */
    public Integer regUser(Users users){
        usersMapper.insertSelective(users);
        return users.getUserid();
    }

    /**
     * 给用户添加角色
     * @param roleIds 角色id列表
     * @param uId 用户id
     * @return
     */
    public int addRoles(List<Integer> roleIds, int uId) {
        return usersMapper.addRoles(roleIds, uId);
    }

    /**
     * 修改用户
     * @param user 用户
     * @return
     */
    public int update(Users user) {
        return usersMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除用户的角色
     * @param roleIds
     * @param uId
     * @return
     */
    public int deleteRoles(List<Integer> roleIds, int uId) {
        return usersMapper.deleteRoles(roleIds, uId);
    }

    /**
     * 通过id查找用户
     * @param uId 用户id
     * @return
     */
    public Users getUserById(int uId) {
        return usersMapper.selectByPrimaryKey(uId);
    }


    public int deleteRelation(Integer userId) {
        return usersMapper.deleteRelation(userId);
    }

    public List<Map> findAlfAndInfoByUid(Integer userId) {
        return usersMapper.selectAlfAndInfoByUid(userId);
    }
}
