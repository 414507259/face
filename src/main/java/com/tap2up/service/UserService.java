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
    @Autowired
    private UsersMapper usersMapper;
    public Users getUser(String username){
        return usersMapper.getUser(username);
    }
    public void regUser(String username,String password){
        Users users = new Users();
        users.setUsername(username);
        users.setPassword(password);
        usersMapper.insertSelective(users);
    }
}
