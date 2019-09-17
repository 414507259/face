package com.tap2up.mapper;

import com.tap2up.pojo.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RecordMapper {


    int getTodayCount(@Param("beginTime") Long beginTime, @Param("endTime") Long endTime,@Param("type") Integer type);

    List<Map<String,Object>> getRecord(@Param("beginTime") Long beginTime, @Param("endTime") Long endTime,
                        @Param("type") Integer type, @Param("group") String group, @Param("name") String name);

    List<Map> getUserStatistics(@Param("beginTime") Long beginTime, @Param("endTime") Long endTime,
                                 @Param("type") Integer type, @Param("group") String group, @Param("name") String name);
    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);
}