package com.tap2up.service;

import com.tap2up.mapper.AlfUserLibraryMapper;
import com.tap2up.mapper.ImageMapper;
import com.tap2up.pojo.AlfUserLibrary;
import com.tap2up.pojo.Image;
import com.tap2up.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/08/30 15:57
 * Description: 用于处理阿尔法设备的逻辑
 * Version: V1.0
 */
@Service
public class AlfService {

    private final AlfUserLibraryMapper mapper;
    private ImageMapper imageMapper;

    @Autowired
    public AlfService(AlfUserLibraryMapper mapper, ImageMapper imageMapper) {
        this.mapper = mapper;
        this.imageMapper = imageMapper;
    }

    /**
     * 添加用户
     * @param library 用户信息
     * @return 主键
     */
    public int addStaff(AlfUserLibrary library){
        Long time = System.currentTimeMillis();
        library.setCreateat(time);
        String uuid = FileUtil.getUUID();
        library.setUserid(uuid);
        mapper.insertSelective(library);
        return library.getId();
    }

    /**
     *  根据用户id获取用户信息
     * @param userId
     * @return
     */
    public AlfUserLibrary getAlfUserLibrary(int userId){
        return mapper.getAlfUserLibrarybyUserId(userId);
    }


    /**
     * 编辑用户
     * @param alfUserLibrary
     * @return
     */
    public int updateAlf(AlfUserLibrary alfUserLibrary){
        Long time = System.currentTimeMillis();
        alfUserLibrary.setUserupdatedat(time);
        return mapper.updateByPrimaryKeySelective(alfUserLibrary);
    }


    /**
     * 文件上传
     * @param pic 图片
     * @param request
     * @return
     * @throws IOException
     */
    public String fileupload(MultipartFile pic, HttpServletRequest request) throws IOException {
        String fileName = pic.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (suffix.equalsIgnoreCase(".png") || suffix.equalsIgnoreCase(".jpg")) {
            String path = FileUtil.inputFile(suffix, pic, request);
            Image image = new Image();
            image.setMid(path);
            Long time = System.currentTimeMillis();
            image.setCtime(time);
            image.setPath("face/file/"+path+suffix);
            imageMapper.insertSelective(image);
            return path;
        } else {
            return  "图片格式不正确";
        }
    }
}
