package com.dgpalife.resourcemanagement.interceptor;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class CommonInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //AjaxResult result = new AjaxResult();
        //从session中获取登陆用户是否存在
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Const.LOGIN_USER);

        if(user == null) {
//            String XRequested =request.getHeader("X-Requested-With");
//            if("XMLHttpRequest".equals(XRequested)){
//                //result.setMessage("IsAjax");
//                response.getWriter().write("IsAjax");
//            }else{
//                response.sendRedirect(request.getContextPath() + "/toLogin");
//            }
//            return false;
            response.sendRedirect(request.getContextPath() + "/toLogin");
            return false;
        }
        return true;
//        }else{
//            response.sendRedirect(request.getContextPath() + "/toLogin");
//            return false;
//        }
    }
}
