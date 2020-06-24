package cn.javak.utils;

import cn.javak.pojo.UserInfo;
import cn.javak.service.UserService;
import cn.javak.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

@Component
public class SessionUtils {
    @Autowired
    private CartServiceImpl cartServiceImpl;
    @Autowired
    private UserService userServiceImpl;

    private static SessionUtils sessionUtils;
    @PostConstruct
    public void init(){
        sessionUtils = this;
        sessionUtils.cartServiceImpl = this.cartServiceImpl;
    }
    public static void refreshSession(HttpSession session){
        UserInfo curUserInfo = (UserInfo) session.getAttribute("user");
        session.setAttribute("baby_num", sessionUtils.cartServiceImpl.selCount(curUserInfo.id));
        session.setAttribute("user", sessionUtils.userServiceImpl.selById(curUserInfo.getId()));
    }
}
