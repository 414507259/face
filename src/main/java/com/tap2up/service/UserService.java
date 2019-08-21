package com.tap2up.service;

import com.tap2up.mapper.UsersMapper;
import com.tap2up.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void regUser(Users users){
        usersMapper.insertSelective(users);
    }
}
