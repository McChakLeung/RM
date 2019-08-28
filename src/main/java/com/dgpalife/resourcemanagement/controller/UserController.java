package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.*;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth/user")
public class UserController {

    private AjaxResult result = new AjaxResult();

    @Autowired
    private UserService userService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/auth/user/index";
    }

    /**
     * 以异步的方式查询用户页面数据，并将结果集放在一个自定义的page类中，该类封装成分页类
     * @param pageno 查询的当前页
     * @param pagesize  查询的每页包含的记录
     * @return 返回查询页面
     */
    @ResponseBody
    @RequestMapping("/index")
    public Object toIndex(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                          @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize,
                          String queryText){
        try{
            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
            if(StringUtil.isNotEmpty(queryText)){
                if(queryText.contains("%")){
                    queryText = queryText.replaceAll("%", "\\\\%");
                }
                params.put("queryText", queryText); //   \%
            }
            Page<User> page = userService.selectUserList(params);
            if(page == null){
                result.setSuccess(false);
                result.setMessage("无相关记录");
                return result;
            }
            result.setPage(page);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

}
