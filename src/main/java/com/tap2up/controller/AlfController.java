package com.tap2up.controller;

import com.tap2up.pojo.AlfUserLibrary;
import com.tap2up.service.AlfService;
import com.tap2up.utils.AlfModel;
import com.tap2up.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/08/29 11:43
 * Description:
 * Version: V1.0
 */
@Controller
@RequestMapping(value = "alf")
public class AlfController {

    @Autowired
    public AlfService alfService;

    @RequestMapping(value = "login")
    @ResponseBody
    public String login(){

        return null;
    }

    @RequestMapping(value = "add/staff")
    @ResponseBody
    public AlfModel addStaff(AlfUserLibrary library){
        int i = alfService.addStaff(library);
        if (i > 0){
            return new AlfModel("0","ok");
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
    @ResponseBody
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
    @ResponseBody
    public AlfModel fileupload(MultipartFile pic, HttpServletRequest request) throws IOException {
        String fileName = pic.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (suffix.equalsIgnoreCase(".png") || suffix.equalsIgnoreCase(".jpg")) {
            String path = FileUtil.inputFile(suffix, pic, request);
            return new AlfModel("200","上传成功:");
        } else {
            return new AlfModel("-1", "图片格式不正确");
        }
    }
}
