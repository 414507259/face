package com.tap2up.controller;

import com.tap2up.service.AlfService;
import com.tap2up.utils.BASE64DecodedMultipartFile;
import com.tap2up.utils.FileUtil;
import com.tap2up.utils.MyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/08/19 11:02
 * Description: 处理文件上传下载的请求
 * Version: V1.0
 */
@Controller
public class FileController {
    final AlfService alfService ;

    public FileController(AlfService alfService) {
        this.alfService = alfService;
    }

    /**
     * 图片上传
     * @param base64 头像照片
     * @param request 请求
     * @return 上传结果
     * @throws IOException io异常
     */
    @RequestMapping(value = "fileupload")
    @ResponseBody
    public String fileupload(String base64, HttpServletRequest request) throws IOException {
        MultipartFile pic = BASE64DecodedMultipartFile.base64ToMultipart(base64);
        return alfService.fileupload(pic,request);
    }

}
