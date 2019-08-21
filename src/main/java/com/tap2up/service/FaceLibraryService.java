package com.tap2up.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tap2up.mapper.UserInfoMapper;
import com.tap2up.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/08/20 11:46
 * Description:
 * Version: V1.0
 */
@Service
public class FaceLibraryService {

    @Autowired
    UserInfoMapper userInfoMapper =null;

    public static Long total = 0L;

    public int insertFaceInfo(UserInfo userInfo){
        return userInfoMapper.insertSelective(userInfo);
    }

    public List synchronizeFaceLibrary( int currentPage, int pageSize){
        PageHelper.startPage(currentPage, pageSize);
        List<UserInfo> list = userInfoMapper.getUserinfo();
        PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(list);
        try {
        if (total == 0)
        total = pageInfo.getTotal(); //获取总记录数
        return list;
        }finally {
            updata(list);
        }
    }

    /**
     * 将状态改为未更新
     * @param list 用户信息
     */
    public void updata(List<UserInfo> list){
        for (int i = 0; i < list.size(); i++) {
            userInfoMapper.updateIsupdateByPrimaryKey(0,list.get(i).getId());
        }
    }
}
