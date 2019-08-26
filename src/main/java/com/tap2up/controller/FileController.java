package com.tap2up.controller;

import com.tap2up.utils.FileUtil;
import com.tap2up.utils.MyModel;
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

    /**
     * 图片上传
     * @param pic 头像照片
     * @param request 请求
     * @return 上传结果
     * @throws IOException io异常
     */
    @RequestMapping(value = "fileupload")
    @ResponseBody
    public MyModel fileupload(MultipartFile pic, HttpServletRequest request) throws IOException {
        String fileName = pic.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (suffix.equalsIgnoreCase(".png") || suffix.equalsIgnoreCase(".jpg")) {
            String path2 = FileUtil.inputFile(suffix, pic, request);
            return new MyModel("200","上传成功:"+path2);
        } else {
            return new MyModel("-1", "图片格式不正确");
        }
    }

}
