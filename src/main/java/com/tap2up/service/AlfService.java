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
 * Description:
 * Version: V1.0
 */
@Service
public class AlfService {

    @Autowired
    private AlfUserLibraryMapper mapper;

    public int addStaff(AlfUserLibrary library){
        return mapper.insertSelective(library);
    }

    public AlfUserLibrary getAlfUserLibrary(int userId){
        return mapper.getAlfUserLibrarybyUserId(userId);
    }

    public Map getUserStatistics(){
        return mapper.getUserStatistics();
    }
}
