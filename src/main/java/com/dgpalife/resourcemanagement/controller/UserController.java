package com.dgpalife.resourcemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    //@Autowired
    //private UserService userService;

    @RequestMapping("/login")
    public String login(String username,String password,Model model){

        //userService.selectUserByUserNameAndPassword(username,password);
        return null;
    }

}
