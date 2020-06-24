package cn.javak.controller;

import cn.javak.pojo.UserInfo;
import cn.javak.service.CartService;
import cn.javak.service.GoodService;
import cn.javak.service.GoodTypeService;
import cn.javak.service.UserService;
import cn.javak.service.impl.CartServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private GoodTypeService goodTypeServiceImpl;
    @Autowired
    private GoodService goodServiceImpl;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CartService cartServiceImpl;
    @Autowired
    private UserService userServiceImpl;

    /**
     * 访问主页
     * @param goodTypeId
     * @return
     */
    @RequestMapping("/index")
    public String index(String goodTypeId){
        request.setAttribute("goodTypeList", goodTypeServiceImpl.selAll());
        request.setAttribute("goodList", goodServiceImpl.selByGoodTypeId(goodTypeId==null?1:Integer.valueOf(goodTypeId)));
        UserInfo curUserInfo = (UserInfo) request.getSession().getAttribute("user");
        return "index";
    }
}
