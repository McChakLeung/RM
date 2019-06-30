package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(String username, String password,Boolean remember_me, Model model, HttpSession session){

        User user = userService.selectUserByUserNameAndPassword(username,password);
        if(user == null){
            model.addAttribute("errorMessage","用户名或密码不正确");
            return "login";
        }
        if(remember_me==true){
            session.setMaxInactiveInterval(7*24*60*60);
        }
        session.setAttribute("user",user);
        return "redirect:/index";
    }

}
