package com.tap2up.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tap2up.mapper.RecordMapper;
import com.tap2up.pojo.Record;
import com.tap2up.utils.MyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/08/26 15:51
 * Description: 用于处理通勤信息的逻辑
 * Version: V1.0
 */
@Service
public class RecordService {

    private final RecordMapper recordMapper;

    @Autowired
    public RecordService(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    /**
     *   添加打卡记录
     * @param record 刷脸用户信息
     * @return 添加结果
     */
    public int addRecord(Record record){
        Long time = System.currentTimeMillis();
        record.setTimestamp(time);
        return recordMapper.insertSelective(record);
    }

    /**
     * 获取用户通勤信息
     * @return
     */
    public MyModel getRecord(Integer currentPage, Integer pageSize,Long beginTime,Long endTime,Integer type,
                          String group,String name){
        if (beginTime != null && beginTime.equals(endTime)){
         endTime = endTime + 86400000;
        }
        if (name == "")
            name = null;
        if (group == "" || "所有".equals(group))
            group = null;

        PageHelper.startPage(currentPage, pageSize);
        List<Map<String,Object>> list = recordMapper.getRecord(beginTime,endTime,type,group,name);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
        Long total = pageInfo.getTotal(); //获取总记录数
        MyModel myModel = new MyModel("200",""+total,list);
        return myModel;
    }



    public int deleteRecord(int id){
        return recordMapper.deleteByPrimaryKey(id);
    }
    public void deleteRecords(int[] id){
        for (int i = 0; i < id.length; i++) {
            deleteRecord(id[i]);
        }
    }

    public MyModel getUserStatistics(Integer currentPage, Integer pageSize,Long beginTime,Long endTime,Integer type,
                                 String group,String name){
        if (beginTime != null && beginTime.equals(endTime)){
            endTime = endTime + 86400000;
        }
        if (name == "")
            name = null;
        if (group == "" || "所有".equals(group))
            group = null;
        PageHelper.startPage(currentPage, pageSize);
        List<Map> list = recordMapper.getUserStatistics(beginTime,endTime,type,group,name);
        PageInfo<Map> pageInfo = new PageInfo<Map>(list);
        Long total = pageInfo.getTotal(); //获取总记录数
        MyModel myModel = new MyModel("200",""+total,list);
        return myModel;
    }

    /**
     * 获取近7天的通勤总计
     * @return
     */
    public List<Map> statistics(){
        Long  time = System.currentTimeMillis();  //当前时间的时间戳
        List<Map> list = new ArrayList<>();
        long zero = time/(1000*3600*24)*(1000*3600*24) - TimeZone.getDefault().getRawOffset()+60*60*24*1000;
        for (int i = 0; i < 7; i++) {
            long time2 = zero - 60*60*24*1000;
            int a  = recordMapper.getTodayCount(time2,zero,null);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(time2);
            String res = simpleDateFormat.format(date);
            Map map = new HashMap();
            map.put("date",res);
            map.put("data",a);
            list.add(map);
            zero = time2;
        }
        return list;
    }


    public Map count(){
        int a  = recordMapper.getTodayCount(0L,9999999999999L,null);
        int b  = recordMapper.getTodayCount(0L,9999999999999L,2);
        List<Map> list = new ArrayList<>();
        Map map = new HashMap();
        map.put("count",a);
        map.put("count2",b);
        return map;
    }
}
