package com.tap2up.mapper;

import com.tap2up.pojo.Device;

import java.util.List;
import java.util.Map;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer deviceid);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer deviceid);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Map> selectPreview(String deviceSn);

    Map<String, Object> selectDetailById(Integer id);
}