package com.tap2up.controller;

import com.tap2up.pojo.AlfUserLibrary;
import com.tap2up.pojo.UserInfo;
import com.tap2up.pojo.Users;
import com.tap2up.service.AlfService;
import com.tap2up.service.RoleService;
import com.tap2up.service.UserInfoService;
import com.tap2up.service.UserService;
import com.tap2up.utils.IdCardUtil;
import com.tap2up.utils.Result;
import com.tap2up.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zxb
 * Date: 2019/08/12
 * Description: 处理登录账户请求
 * Version: V1.0
 */
@RequestMapping("user/")
@RestController
public class UserController {

    private UserService userService;

    private AlfService alfService;

    private UserInfoService userInfoService;

    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, AlfService alfService, UserInfoService userInfoService, RoleService roleService) {
        this.userService = userService;
        this.alfService = alfService;
        this.userInfoService = userInfoService;
        this.roleService = roleService;
    }

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录状态
     */
    @RequestMapping(value = "login")
    public Result selectUser(@RequestParam String username, @RequestParam String password) {
        Subject currentUser = SecurityUtils.getSubject();                                     // shiro权限认证主体对象
        //currentUser.getSession().setTimeout(1000);//设置session过期时间，单位：毫秒
        if (!currentUser.isAuthenticated()) {                                                 // 满足shiro的可认证状态
            UsernamePasswordToken upToken = new UsernamePasswordToken(username, password);    // shiro权限认证类型
            upToken.setRememberMe(true);                                                     // 用户登录时效性
            try {
                currentUser.login(upToken);    // 调用realm认证用户权限
                Users user = (Users)currentUser.getPrincipal();
                user.setLogintime(new Date());
                userService.update(user);
                user.setPassword(null);
                return ResultUtil.success(user);
            } catch (IncorrectCredentialsException ice) {
                return ResultUtil.error(101,"用户名/密码不匹配！");
            } catch (LockedAccountException lae) {
//                System.out.println("账户已被冻结！");
                return ResultUtil.error(102,"账户已被冻结！");
            } catch (UnknownAccountException uae) {
//                System.out.println("账户不存在");
                return ResultUtil.error(103,"用户不存在");
            } catch (AuthenticationException ae) {
//                System.out.println(ae.getMessage());
                return ResultUtil.error(100,ae.getMessage());
            }
        }
        return ResultUtil.error(100,"失败");
    }


    /**
     * 注册用户
     * @param users 用户信息
     * @return 注册状态
     */
    @RequestMapping(value = "reg")
    public String regUser(Users users, AlfUserLibrary alfUserLibrary, UserInfo userInfo) {
        //从身份证号码中解析出生日和年龄
        String idNumber = alfUserLibrary.getIdnumber();
        if(idNumber != null && idNumber.length() >= 15){
            Map<String, String> idInfo = IdCardUtil.getBirAgeSex(idNumber);
            alfUserLibrary.setBirthday(idInfo.get("birthday"));
            userInfo.setAge(Integer.parseInt(idInfo.get("age")));
        }

        //通过组名获取组id
        Integer groupId = userInfoService.findGroupIdByGroupName(alfUserLibrary.getGroupname());
        alfUserLibrary.setGroupid(groupId);

        //存入人脸库信息并返回id
        Integer id = alfService.addStaff(alfUserLibrary);
        if(id < 0){
            return "失败";
        }
        userInfo.setId(id);
        users.setId(id);
        //以电话作为账号
        users.setUsername(userInfo.getTel());
        users.setPassword(md5(users.getUsername(),users.getPassword()));
        //保存账号信息
        Integer userId = userService.regUser(users);
        //保存用户信息
        userInfoService.addUserInfo(userInfo);
        //通过角色名得到角色id
        Integer roleId = roleService.findRidByRoleName(users.getAuth());
        //添加用户的角色
        List<Integer> rIds = new ArrayList<>();
        rIds.add(roleId);
        userService.addRoles(rIds,userId);
        return "成功";
    }


    /**
     * 给用户添加角色
     * @param roleIds 角色id列表
     * @param uId 用户id
     * @return 是否成功
     */
    @RequestMapping(value = "addRoles",method = RequestMethod.POST,produces = "application/json")
    public String addRoles(@RequestBody List<Integer> roleIds, int uId){
    /*@RequestMapping("/addRoles")
    public String addRoles(@RequestParam(value="roleIds",required=false)List<String> roleIds){*/
        int n =userService.addRoles(roleIds,uId);
        if(n > 0){
            return "成功";
        }
        return "失败 ";

    }

    /**
     * 删除用户（假删除）
     * @param user 用户
     * @return 是否成功
     */
    @RequestMapping("deleteUser")
    public String deleteUser(Users user){
        user.setIsdelete(1);
        int n =userService.update(user);
        if(n > 0){
            return "成功";
        }
        return "失败";
    }

    /**
     * 修改用户
     * @param users 用户
     * @return 是否成功
     */
    @RequestMapping("updateUser")
    public String updateUser(Users users, AlfUserLibrary alfUserLibrary, UserInfo userInfo){
        //从身份证号码中解析出生日和年龄
        String idNumber = alfUserLibrary.getIdnumber();
        if(idNumber != null && idNumber.length() >= 15){
            Map<String, String> idInfo = IdCardUtil.getBirAgeSex(idNumber);
            alfUserLibrary.setBirthday(idInfo.get("birthday"));
            userInfo.setAge(Integer.parseInt(idInfo.get("age")));
        }

        //通过组名获取组id
        Integer groupId = userInfoService.findGroupIdByGroupName(alfUserLibrary.getGroupname());
        alfUserLibrary.setGroupid(groupId);

        //修改人脸库信息并返回id
        int n = alfService.updateAlf(alfUserLibrary);
        if(n < 1){
            return "失败";
        }
        //以电话作为账号
        users.setUsername(userInfo.getTel());

        String password = users.getPassword();
        if(password != null){
            //密码MD5加密 加盐
            users.setPassword(md5(users.getUsername(),password));
        }

        //保存账号信息
        userService.update(users);
        //保存用户信息
        userInfoService.updateUserInfo(userInfo);
        //通过角色名得到角色id
        Integer roleId = roleService.findRidByRoleName(users.getAuth());
        //查找角色是否更变
        int count = roleService.isRelationExisted(roleId, users.getUserid());
        if(count == 0){
            //角色更变则删除旧角色添加新角色
            userService.deleteRelation(users.getUserid());
            //添加用户的角色
            List<Integer> rIds = new ArrayList<>();
            rIds.add(roleId);
            userService.addRoles(rIds,users.getUserid());

        }
        //没有更变则不做处理

        return "成功";
    }


    /**
     * 删除用户的角色
     * @param roleIds 角色id列表
     * @param uId 用户id
     * @return 是否成功
     */
    @RequestMapping("deleteRoles")
    public String deleteRoles(@RequestBody List<Integer> roleIds, int uId){
        int n = userService.deleteRoles(roleIds, uId);
        if(n > 0){
            return "成功";
        }
        return "失败";
    }


    /**
     * 分配用户角色
     * @param addIds 需要添加角色的id列表
     * @param delIds 需要删除角色的id列表
     * @param uId 用户id
     * @return 是否成功
     */
    @RequestMapping("assignRole")
    public String assignRole(@RequestParam("addIds") List<Integer> addIds, @RequestParam("delIds") List<Integer> delIds,int uId){
        int n0 = 0;
        int n1 = 0;
        if(addIds.size() > 0){
            n0 = userService.addRoles(addIds,uId);
        }
        if(delIds.size() > 0){
            n1 = userService.deleteRoles(delIds, uId);
        }
        if(n0 < 1){
            return "操作失败";
        }
        if(n1 < 1){
            return "操作失败";
        }
        return "成功";
    }

    /**
     * 查找所有的用户及其人脸库信息和详细信息
     * @return 查询结果
     */
    @RequestMapping("all")
    public List<Map> findAll(){
        return userService.findAlfAndInfoByUid(null);
    }

    /**
     * 通过账号id查找 账号信息及人脸库信息和用户信息
     * @param userId 账号id
     * @return 账号信息及人脸库信息和用户信息
     */
    @RequestMapping("findByUid")
    public List<Map> findAlfAndInfoByUid(Integer userId){
        return userService.findAlfAndInfoByUid(userId);
    }




    // 注册时，进行shiro加密，返回加密后的结果，如果在加入shiro之前，存在用户密码不是此方式加密的，那么将无法登录
    // 使用用户名作为盐值
    private String md5(String username, String password){
        String hashAlgorithmName = "MD5";                   // 加密方式
        ByteSource salt = ByteSource.Util.bytes(username);  // 以账号作为盐值
        int hashIterations = 11;                            // 加密11次
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }
}