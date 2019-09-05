package com.tap2up.service;

import com.tap2up.mapper.AlfUserLibraryMapper;
import com.tap2up.mapper.GroupMapper;
import com.tap2up.pojo.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/09/05 14:57
 * Description:
 * Version: V1.0
 */
@Service
public class GroupService {

    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private AlfUserLibraryMapper libraryMapper;


    public int insertGroup(Group group){
        Long time = System.currentTimeMillis();
        group.setCtime(time);
        return groupMapper.insertSelective(group);
    }

    public List<Map> selectGroup(){
        return groupMapper.selectGroup();
    }

    public int updateGroup(Group group){
        Long time = System.currentTimeMillis();
        group.setUpdatetime(time);
        return groupMapper.updateByPrimaryKeySelective(group);
    }

    public int deleteGroup(int id){
        int a = libraryMapper.getByGroupId(id);
        if (a > 0){
            return -1;
        }
        return groupMapper.deleteByPrimaryKey(id);
    }
}
