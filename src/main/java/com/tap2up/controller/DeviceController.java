package com.tap2up.controller;

import com.github.pagehelper.PageInfo;
import com.tap2up.pojo.Config;
import com.tap2up.pojo.Device;
import com.tap2up.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: Han
 * @Date: 2019/9/18 09:52
 * @Description:
 */
@RequestMapping("device")
@RestController
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }

    /**
     * 添加设备
     * @param device 设备信息
     * @param config 设备配置
     * @return 操作结果
     */
    @RequestMapping("add")
    public String addDevice(Device device, Config config){
        deviceService.addDevice(device, config);
        return "成功";
    }

    /**
     * 通过设备id（deviceid）和配置id(configid)修改设备信息和设备配置
     * @param device 设备信息
     * @param config 配置信息
     * @return 操作结果
     */
    @RequestMapping("update")
    public String updateDevice(Device device, Config config){
        deviceService.updateDevice(device, config);
        return "成功";
    }

    /**
     * 通过设备id（deviceid）删除设备（假删除）
     * @param device 设备信息（只需要id）
     * @return 操作结果
     */
    @RequestMapping("delete")
    public String deleteDevice(Device device){
        device.setIsdelete(1);
        deviceService.updateDevice(device, null);
        return "成功";
    }

    /**
     * 按分页查找设备信息
     * @param pageNum 当前页数
     * @param pageSize 页面大小
     * @param deviceSn 设备SN号
     * @return 分页信息
     */
    @RequestMapping("findPreview")
    public PageInfo findPreview(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                @RequestParam(required = false, defaultValue = "10", value = "pageSize") Integer pageSize,
                                String deviceSn){
        return deviceService.findPreview(pageNum, pageSize, deviceSn);
    }

    /**
     * 通过设备id（deviceid）查找设备信息和配置信息
     * @param id 设备id
     * @return 设备信息和配置信息
     */
    @RequestMapping("findDetail")
    public Map<String, Object> findDetail(Integer id){
        return deviceService.findDetail(id);
    }
}
