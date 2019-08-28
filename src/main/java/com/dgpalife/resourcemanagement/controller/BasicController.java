package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.MD5Util;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class  BasicController {

    @Autowired
    private UserService userService;

    private AjaxResult result = new AjaxResult();

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }


    /**
     * 异步登录
     * @param username 登录账号
     * @param password 登录密码
     * @param remember_me  是否记住我
     * @param session
     * @return 返回异步结果对象
     */

    @ResponseBody
    @RequestMapping("/login")
    public Object login(String loginacct, String password,String remember_me, HttpSession session){

        try{
            User user = userService.selectUserByUserNameAndPassword(loginacct, MD5Util.digest(password));
            if(user == null){
                result.setMessage("用户名或密码错误，请确认后重新登录");
                result.setSuccess(false);
                return result;
            }

            session.setAttribute(Const.LOGIN_USER,user);
            if("on".equals(remember_me) && remember_me !=null){
                session.setMaxInactiveInterval(604800);
            }
            result.setSuccess(true);

        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("登录异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/toLogin";
    }


}
