package com.tap2up.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tap2up.mapper.ConfigMapper;
import com.tap2up.mapper.DeviceMapper;
import com.tap2up.pojo.Config;
import com.tap2up.pojo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Han
 * @Date: 2019/9/18 09:39
 * @Description:
 */
@Service
public class DeviceService {

    private final DeviceMapper deviceMapper;

    private final ConfigMapper configMapper;

    @Autowired
    public DeviceService(DeviceMapper deviceMapper, ConfigMapper configMapper){
        this.deviceMapper = deviceMapper;
        this.configMapper = configMapper;
    }

    public void addDevice(Device device, Config config){
        deviceMapper.insertSelective(device);
        configMapper.insertSelective(config);
    }

    public void updateDevice(Device device, Config config){
        deviceMapper.updateByPrimaryKeySelective(device);
        if(config != null){
            configMapper.updateByPrimaryKeySelective(config);
        }
    }

    public PageInfo findPreview(Integer pageNum, Integer pageSize,String deviceSn){
        PageHelper.startPage(pageNum, pageSize);
        List<Map> devices = deviceMapper.selectPreview(deviceSn);
        PageInfo pageInfo = new PageInfo(devices, 5);
        return pageInfo;
    }


    public Map<String, Object> findDetail(Integer id) {
        return deviceMapper.selectDetailById(id);
    }
}
