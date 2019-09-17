package com.tap2up.mapper;

import com.tap2up.pojo.Config;

import java.util.Map;

public interface ConfigMapper {

    Map getConfig(String deviceSn);
    int deleteByPrimaryKey(Integer configid);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer configid);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}