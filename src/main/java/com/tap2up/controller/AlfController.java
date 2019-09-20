package com.tap2up.controller;

import com.alibaba.fastjson.JSON;
import com.tap2up.pojo.AlfUserLibrary;
import com.tap2up.service.AlfService;
import com.tap2up.utils.AlfModel;
import com.tap2up.utils.EncryptUtils;
import com.tap2up.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/08/29 11:43
 * Description:
 * Version: V1.0
 */
@RestController
@RequestMapping(value = "alf")
public class AlfController {

    private final AlfService alfService;
    public static String aes = "1234563216545555";
    private volatile AlfModel alfModel = new AlfModel();

    @Autowired
    public AlfController(AlfService alfService) {
        this.alfService = alfService;
    }

    /**
     * 用于设备登录
     * @param account
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "login")
    public Map<String,String> login(String account,String password){
        if (account == null || password == null){
            try {
                return alfModel.Encrypt("-1","Parameter is not null");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int a = alfService.login(account,password);
        if (a > 0){
            Map<String,String> map = new HashMap<>();
            map.put("key",aes);
            map.put("token", FileUtil.getUUID());
            map.put("AESPassWord",aes);
            return alfModel.Encrypt2("0","ok",map);
        }else {
            return null;
        }

    }


    /**
     * 添加人脸库
     * @param library 人脸库的对象
     * @return
     */
    @RequestMapping(value = "add/staff")
    public AlfModel addStaff(AlfUserLibrary library){
        int i = alfService.addStaff(library);
        if (i > 0){
            return new AlfModel("0",i+"");
        }else {
            return new AlfModel("-2","人员 id 不可用");
        }
    }

    /**
     * 根据用户id获取用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    @RequestMapping(value = "getAlfUserLibrary")
    public AlfModel getAlfUserLibrary(@RequestParam(required = true,defaultValue = "0") int userId){
        if (userId == 0){
            return new AlfModel("-1","userID不能为空");
        }
        AlfUserLibrary alfUserLibrary = alfService.getAlfUserLibrary(userId);
        List list = new ArrayList();
        list.add(alfUserLibrary);
        return new AlfModel("0","ok",list);
    }




    @RequestMapping(value = "image/uploading")
    public Map fileupload(MultipartFile pic, HttpServletRequest request) throws IOException {
        if (pic == null){
            return alfModel.Encrypt("-1","Parameter is not null");
        }
        String str = alfService.fileupload(pic,request);
        if ("图片格式不正确".equals(str)){
            return alfModel.Encrypt("-1","格式不正确");
        }
        return alfModel.Encrypt("0","ok");
    }

    /**
     * 获取配置信息
     * @param deviceSn 设备sn
     * @param timestamp 时间戳
     * @param token
     * @param sign
     * @return
     */
    @RequestMapping(value = "config")
    public Map config(String deviceSn, Long timestamp, String token, String sign){
        if (deviceSn == null){
            return alfModel.Encrypt("-1","Parameter is not null");
        }
        Map map = alfService.config(deviceSn);
        return alfModel.Encrypt2("0","ok",map);
    }

    /**
     * 获取配置更新时间
     * @param deviceSn
     * @param timestamp
     * @param token
     * @param sign
     * @return
     */
    @RequestMapping(value = "serverConfig/time")
    public Map serverConfig(String deviceSn, String timestamp, String token, String sign){
        if (deviceSn == null){
            return alfModel.Encrypt("-1","Parameter is not null");
        }
        Map<String,Long> map = new HashMap<>();
        map.put("configUpTime",1557884520000L);
        map.put("groupUpTime",1557884520000L);
        map.put("companyUpTime",1557884520000L);
        return alfModel.Encrypt2("0","ok",map);
    }

    /**
     * 获取用户组列表
     * @param deviceSn
     * @param timestamp
     * @param token
     * @param sign
     * @return
     */
    @RequestMapping(value = "group")
    public Map group(String deviceSn, Long timestamp, String token, String sign){
        AlfModel alfModel = new AlfModel();
        if (deviceSn == null){
            return alfModel.Encrypt("-1","Parameter is not null");
        }
        List<Map> list = alfService.group();
        for (Map p:list) {
            p.put("deviceSn",deviceSn);
        }
        return alfModel.Encrypt2("0","ok",list);
    }

    /**
     * 根据组id获取成员
     * @param groupId
     * @param timestamp
     * @param token
     * @param sign
     * @return
     */
    @RequestMapping(value = "group/info")
    public Map groupInfo(Integer groupId, Long timestamp, String token, String sign){
        if (groupId == null){
            return alfModel.Encrypt("-1","Parameter is not null");
        }
        List list = alfService.groupInfo(groupId);
        return alfModel.Encrypt2("0","ok",list);
    }
}
