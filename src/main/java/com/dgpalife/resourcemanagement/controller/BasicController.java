package com.dgpalife.resourcemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class  BasicController {

    @RequestMapping("/")
    public String index(HttpSession session){
        //临时写死
        session.setAttribute("userName","maizeliang098");
        return "/index";
    }
}
