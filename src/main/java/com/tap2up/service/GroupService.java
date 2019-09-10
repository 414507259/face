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
 * Description: 用于处理用户组操作的逻辑
 * Version: V1.0
 */
@Service
public class GroupService {

    private final GroupMapper groupMapper;
    private final AlfUserLibraryMapper libraryMapper;

    @Autowired
    public GroupService(AlfUserLibraryMapper libraryMapper, GroupMapper groupMapper) {
        this.libraryMapper = libraryMapper;
        this.groupMapper = groupMapper;
    }


    /**
     *  添加用户组
     * @param group
     * @return
     */
    public int insertGroup(Group group){
        int i = groupMapper.selectByName(group.getGroupname());
        if (i > 0){
            return -1;
        }
        Long time = System.currentTimeMillis();
        group.setCtime(time);
        return groupMapper.insertSelective(group);
    }

    /**
     * 查询用户组
     * @param type  用户组类型
     * @return
     */
    public List<Map> selectGroup(String type){
        return groupMapper.selectGroup(type);
    }

    /**
     * 编辑用户组
     * @param group 用户组信息
     * @return
     */
    public int updateGroup(Group group){
        Long time = System.currentTimeMillis();
        group.setUpdatetime(time);
        return groupMapper.updateByPrimaryKeySelective(group);
    }

    /**
     * 删除用户组
     * @param id 主键
     * @return
     */
    public int deleteGroup(int id){
        int a = libraryMapper.getByGroupId(id);
        if (a > 0){
            return -1;
        }
        return groupMapper.deleteByPrimaryKey(id);
    }
}
