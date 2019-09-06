package com.tap2up.controller;

import com.tap2up.pojo.Group;
import com.tap2up.service.GroupService;
import com.tap2up.utils.MyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/09/05 14:56
 * Description:
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "group")
public class GroupController {

    @Autowired
    private GroupService groupService;


    /**
     * 添加用户组
     * @param group
     * @return
     */
    @RequestMapping(value = "insertGroup")
    @ResponseBody
    public MyModel insertGroup(Group group){
        int i = groupService.insertGroup(group);
        System.out.println(group);
        if (i > 0){
            return new MyModel("200","添加成功");
        }
        return new MyModel("-1","添加失败");
    }


    /**
     * 查询所有用户组
     * @return
     */
    @RequestMapping(value = "selectGroup")
    @ResponseBody
    public MyModel selectGroup(String type){
        if (type == ""){
            type = null;
        }
        List list = groupService.selectGroup(type);
        if (list != null){
        return new MyModel("200","成功",list);
        }
        return new MyModel("-1","失败");
    }

    /**
     * 修改用户组
     * @param group
     * @return
     */
    @RequestMapping(value = "updateGroup")
    @ResponseBody
    public MyModel updateGroup(Group group){
        int i = groupService.updateGroup(group);
        if (i > 0){
            return new MyModel("200","成功");
        }
        return new MyModel("-1","失败");
    }

    /**
     * 删除用户组
     * @param id 主键
     * @return
     */
    @RequestMapping(value = "deleteGroup")
    @ResponseBody
    public MyModel deleteGroup(Integer id){
        if (id == null){
            return new MyModel("0","主键不能为空");
        }
        int i = groupService.deleteGroup(id);
        if (i > 0){
            return new MyModel("200","成功");
        }else if(i  == -1){
            return new MyModel("0","该用户组下还有用户");
        }
        return new MyModel("-1","失败");
    }
}
