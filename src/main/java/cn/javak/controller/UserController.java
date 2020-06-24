package cn.javak.controller;

import cn.javak.pojo.UserInfo;
import cn.javak.service.CartService;
import cn.javak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private CartService cartServiceImpl;
    @Autowired
    HttpServletRequest request;

    /**
     * 登录 用户登录
     * @param userInfo
     * @return
     */
    @RequestMapping("/loginUser")
    public String login(UserInfo userInfo){
        boolean success = userServiceImpl.login(userInfo);
        if (success){
            UserInfo curUserInfo = userServiceImpl.selByName(userInfo.name);
            request.getSession().setAttribute("user", curUserInfo);
            return "redirect:index";
        }else {
            request.setAttribute("msg", "用户名或密码错误");
            return "login";
        }
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping("/logoutUser")
    public String logOut(){
        request.getSession().removeAttribute("user");
        return "redirect:/logout";
    }

    /**
     * 注册
     * @param name
     * @param password
     * @param password2
     * @return
     */
    @RequestMapping("/register")
    public String register(@RequestParam(required = true) String name, @RequestParam(required = true)String password, @RequestParam(required = true)String password2){
        UserInfo userInfo = userServiceImpl.selByName(name);
        if (userInfo ==null && name!="" && password!="" && password.equals(password2)){
            UserInfo regUserInfo = new UserInfo();
            regUserInfo.setName(name);
            regUserInfo.setPassword(password);
            regUserInfo.setMoney(1000000);
            userServiceImpl.insUser(regUserInfo);
            request.setAttribute("msg", "注册成功！");
            return "redirect:/login.jsp";
        }
        if (userInfo !=null){
            request.setAttribute("msg", "注册失败,已存在该用户名");
        }
        return "redirect:/login.jsp";
    }
    @RequestMapping("/authenticationOk")
    public String authenticationOk(){
        SecurityContextImpl spring_security_context = (SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        String name = spring_security_context.getAuthentication().getName();
        UserInfo userInfo = userServiceImpl.selByName(name);
        userInfo.setBaby_num(cartServiceImpl.selCount(userInfo.getId()));
        request.getSession().setAttribute("user", userInfo);
        return "redirect:index";
    }
}
