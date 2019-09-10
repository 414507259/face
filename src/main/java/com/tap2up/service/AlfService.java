package com.tap2up.service;

import com.tap2up.mapper.AlfUserLibraryMapper;
import com.tap2up.pojo.AlfUserLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/08/30 15:57
 * Description: 用于处理阿尔法设备的逻辑
 * Version: V1.0
 */
@Service
public class AlfService {

    private final AlfUserLibraryMapper mapper;

    @Autowired
    public AlfService(AlfUserLibraryMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 添加用户
     * @param library 用户信息
     * @return 主键
     */
    public int addStaff(AlfUserLibrary library){
        Long time = System.currentTimeMillis();
        library.setCreateat(time);
        mapper.insertSelective(library);
        return library.getId();
    }

    /**
     *  根据用户id获取用户信息
     * @param userId
     * @return
     */
    public AlfUserLibrary getAlfUserLibrary(int userId){
        return mapper.getAlfUserLibrarybyUserId(userId);
    }


    /**
     * 编辑用户
     * @param alfUserLibrary
     * @return
     */
    public int updateAlf(AlfUserLibrary alfUserLibrary){
        Long time = System.currentTimeMillis();
        alfUserLibrary.setUserupdatedat(time);
        return mapper.updateByPrimaryKeySelective(alfUserLibrary);
    }
}
