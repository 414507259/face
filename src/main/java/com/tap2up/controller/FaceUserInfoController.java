package com.tap2up.controller;

import com.tap2up.mapper.UserInfoMapper;
import com.tap2up.pojo.UserInfo;
import com.tap2up.service.FaceLibraryService;
import com.tap2up.utils.MyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 张雪彬
 * Date: 2019/08/19 16:13
 * Description:
 * Version: V1.0
 */
@Controller
public class FaceUserInfoController {

    private static boolean isupdate = true;
    private static Long total = 0L;

    @Autowired
    FaceLibraryService faceLibraryService = null;

    /**
     * 注册人脸库
     * @param userInfo 用户信息
     * @return 注册结果
     */
    @RequestMapping(value = "insertFaceInfo")
    @ResponseBody
    public MyModel insertFaceInfo(UserInfo userInfo){
        if (userInfo == null){
            return new MyModel("-1","参数为空");
        }
        int i = faceLibraryService.insertFaceInfo(userInfo);
        if (i > 0){
            isupdate = true;
            return new MyModel("200","成功");
        }else {
            return new MyModel("-1","失败");
        }
    }

    /**
     * 同步人脸库
     * @return 人脸库信息
     */
    @RequestMapping(value = "synchronize")
    @ResponseBody
    public MyModel synchronizeFaceLibrary(){
        if (isupdate){    //判断是否有更新
            List list = faceLibraryService.synchronizeFaceLibrary((int)(total/10)+1,10);
//            isupdate = false;
            if (list != null && FaceLibraryService.total != 0 && total >= 0){  //确认数据库还有未更新信息
                if (total == 0){
                    total = FaceLibraryService.total;
                }
                    total = total - 100;
                return new MyModel("200","总更新条数:"+FaceLibraryService.total,list);
            }else {
                FaceLibraryService.total = 0L;
                total = 0L;
                isupdate = false;
                return new MyModel("0","无更新");
            }
        }else {
            return new MyModel("0","无更新");
        }
    }
}
