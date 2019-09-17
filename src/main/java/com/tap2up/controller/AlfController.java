package com.tap2up.controller;

import com.tap2up.pojo.AlfUserLibrary;
import com.tap2up.service.AlfService;
import com.tap2up.utils.AlfModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
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

    @Autowired
    public AlfController(AlfService alfService) {
        this.alfService = alfService;
    }

    @RequestMapping(value = "login")
    public String login(String account,String password) throws Exception {
        System.out.println(account);
        System.out.println(password);
        return null;
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
    public AlfModel fileupload(MultipartFile pic, HttpServletRequest request) throws IOException {
        if (pic == null){
            return new AlfModel("-1","图片为空");
        }
        String str = alfService.fileupload(pic,request);
        if ("图片格式不正确".equals(str)){
            return new AlfModel("-1","图片格式不正确");
        }
        return new AlfModel("0","ok");
    }

    @RequestMapping(value = "config")
    public Map config(String deviceSn, String timestamp, String token, String sign){
        return alfService.config(deviceSn);
    }
}
