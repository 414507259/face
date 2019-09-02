package com.tap2up.controller;

import com.tap2up.pojo.Record;
import com.tap2up.service.RecordService;
import com.tap2up.utils.MyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/08/26 15:44
 * Description:打卡记录管理
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    /**
     * 上传通勤记录
     * @param record
     * @return
     */
    @RequestMapping(value = "addRecord")
    @ResponseBody
    public MyModel addRecord(Record record){
        int a = recordService.addRecord(record);
        if (a > 0){
        return new MyModel("200","添加成功");
        }else {
            return new MyModel("-1","添加失败");
        }
    }

    /**
     * 获取通勤记录
     * @param currentPage 当前页数
     * @param pageSize 每页显示数
     * @return 结果
     */
    @RequestMapping(value = "getRecord")
    @ResponseBody
    public MyModel getRecord(Integer currentPage, Integer pageSize,@RequestParam(required = false) Long beginTime,
                             @RequestParam(required = false) Long endTime,@RequestParam(defaultValue = "0") Integer type,
                             @RequestParam(required = false) String group,@RequestParam(required = false) String name){
        return recordService.getRecord(currentPage,pageSize,beginTime,endTime,type,group,name);
    }

    @RequestMapping(value = "deleteRecord")
    @ResponseBody
    public MyModel deleteRecord(int id){
        recordService.deleteRecord(id);
        return new MyModel("200","删除成功");
    }

    @RequestMapping(value = "deleteRecords")
    @ResponseBody
    public MyModel deleteRecords(int[] id){
        recordService.deleteRecords(id);
        return new MyModel("200","删除成功");
    }
}
