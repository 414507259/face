package com.tap2up.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tap2up.mapper.RecordMapper;
import com.tap2up.pojo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/08/26 15:51
 * Description:
 * Version: V1.0
 */
@Service
public class RecordService {
    @Autowired
    private RecordMapper recordMapper;

    public int addRecord(Record record){
        Long time = System.currentTimeMillis();
        record.setTimestamp(time);
        return recordMapper.insertSelective(record);
    }

    public List getRecord(int currentPage, int pageSize){
        PageHelper.startPage(currentPage, pageSize);
        List<Map> list = recordMapper.getRecord();
        PageInfo<Map> pageInfo = new PageInfo<Map>(list);
        Long total = pageInfo.getTotal(); //获取总记录数
        Map map = new HashMap();
        map.put("total",total);
        list.add(map);
        return list;
    }
    public int deleteRecord(int id){
        return recordMapper.deleteByPrimaryKey(id);
    }
    public void deleteRecords(int[] id){
        for (int i = 0; i < id.length; i++) {
            deleteRecord(id[i]);
        }
    }
}
