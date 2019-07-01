package com.dgpalife.resourcemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class  BasicController {

    @RequestMapping("/")
    public String index(){
//        if(session.getAttribute("username") ==null || "".equals(session.getAttribute("username"))){
//            return "login";
//        }
        //model.addAttribute("session",session);
        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

}
