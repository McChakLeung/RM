package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.MD5Util;
import com.dgpalife.resourcemanagement.model.Permission;
import com.dgpalife.resourcemanagement.model.Role;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.PermissionService;
import com.dgpalife.resourcemanagement.service.RoleService;
import com.dgpalife.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.*;
import java.util.*;

@Controller
public class  BasicController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request){
// { public String toLogin(HttpServletRequest request){
//        //从HttpServletRequest对象中获取cookies，判断当前客户端是否存在loginCode的cookies
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null) { //如果客户端禁用了Cookie，那么无法获取Cookie信息
//            //遍历cookies对象，并获取logiCode的cookie，用于分割数据
//            for (Cookie cookie : cookies) {
//                if ("loginCode".equals(cookie.getName())) {
//                    String[] split = cookie.getValue().split("&");
//                    //判断经过分割的split的长度，如果等于2，则说明数据正确
//                    if(split.length==2){
//                        //再次截取数组第一个数据loginacct=zhangsan,获取新数组的第二个值zhangsan，作为登录用户
//                        String loginacct = split[0].split("=")[1];
//                        String password = split[1].split("=")[1];
//                    }
//                }
//            }
//        }
        return "/login";
    }


    /**
     * 以异步请求的方式登录：接收页面请求的参数(loginacct登录名，userpswd登录密码，type登录类型)，并传递给后台查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/preLogin")
    public Object preLogin(String loginacct, String password, HttpSession session, HttpServletResponse response){

        AjaxResult result = new AjaxResult();


        try {
            //创建一个map来接收参数
            Map<String,Object> params = new HashMap();
            params.put("loginacct",loginacct);
            params.put("password", MD5Util.digest(password));

            //根据传入的值查询客户是否存在
            User user = userService.selectUserByLoginAccAndUserPassword(params);
            //判断是否能查询到user对象，如果查询不到，则说明用户名或密码错误
            if(user == null){
                result.setSuccess(false);
                result.setMessage("用户名或密码错误，请确认后重新登录");
                return result;
            }
            //创建一个Const工具类，存放常量
            session.setAttribute(Const.LOGIN_USER,user);
            //查询用户登陆角色
            List roleList = roleService.queryRoleInfo(user.getId());
//            if(roleList==null){
//                result.setMessage("当前用户无登陆权限");
//                result.setSuccess(false);
//            }
            //查询当前用户登陆的角色所拥有的权限

            //判断传入的remember_me的值，如果是true，则创建cookie对象
//            if(remember_me){
//                String loginCode = "loginacct="+ params.get("loginacct") + "&passsword=" + params.get("password");
//                Cookie cookie = new Cookie("loginCode",loginCode);
//                cookie.setMaxAge(60*60*24*7);
//                cookie.setPath("/");
//                response.addCookie(cookie);
//            }

            result.setDatas(roleList);
            result.setSuccess(roleList.size()>0);

        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("登录错误，请联系管理员处理！");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/doLogin/{roleId}")
    public String doLogin(@PathVariable Long roleId, HttpSession session){

        //获取当前session中的User对象
        User user = (User)session.getAttribute(Const.LOGIN_USER);

        //查询当前登陆的角色，并将登陆的角色存放在session中
        Role role = roleService.queryRoleByRoleId(roleId);
        session.setAttribute(Const.ROLE,role);

        //创建Map集合接收参数
        Map<String,Object> params = new HashMap<>();
        params.put("userId",user.getId());
        params.put("roleId",roleId);

        //查询当前用户所选择的角色，获取登陆权限菜单列表
        List<Permission> permissionList = permissionService.queryPermissionByUserIDAndRoleID(params);

        //设置父节点list
        Permission permissionRoot = null;

        //创建一个map集合
        Map<Integer,Permission> map = new HashMap<>();

        //创建一个set集合，存放权限uri
        Set<String> myUris = new HashSet<String>();

        //提前遍历内层循环
        for(Permission innerPermission : permissionList){
            map.put(innerPermission.getId(),innerPermission);
            myUris.add("/" + innerPermission.getUrl());
        }

        session.setAttribute(Const.MY_URIS,myUris);

        //循环遍历
        for(Permission permission : permissionList){

            //判断当前对象是否为根节点
            if(permission.getPid() == null){
                permissionRoot = permission;
            }else{
                Permission parent = map.get(permission.getPid());
                parent.getChildren().add(permission);
            }
        }
        //将查询结果放到session中
        session.setAttribute("permissionRoot", permissionRoot);

        return "/index";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/toLogin";
    }

    @RequestMapping("/auth")
    public String auth(){
        return "auth";
    }

    @RequestMapping("/openQuery")
    public String openQuery(){
        return "/commons/container";
    }
}
