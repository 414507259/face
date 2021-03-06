package com.tap2up.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: zxb
 * Date: 2019/08/19
 * Description:
 * Version: V1.0
 */
public class FileUtil {

    public static String inputFile(String suffix,MultipartFile userHead, HttpServletRequest request) throws IOException {
            String uuid = getUUID();
            String str = request.getServletContext().getRealPath("");
//        str.substring(0, str.lastIndexOf("ROOT")) +
            str = str.substring(0, str.lastIndexOf("face")) +"file" + File.separator;
//            System.out.println(str);
            String path = str + uuid + suffix;
//            String path2 = "file/" + uuid + suffix;
            File newFile = new File(path);
            FileUtils.copyInputStreamToFile(userHead.getInputStream(), newFile);
            return uuid;
        }

    /**
     * 获取uuid
     * @return uuid
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
