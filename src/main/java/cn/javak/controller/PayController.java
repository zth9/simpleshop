package cn.javak.controller;

import cn.javak.pojo.UserInfo;
import cn.javak.service.GoodOrderService;
import cn.javak.service.UserService;
import cn.javak.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PayController {
    @Autowired
    private GoodOrderService goodOrderServiceImpl;
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private HttpServletRequest request;

    /**
     * 根据金额支付订单
     * @param orderId
     * @return
     */
    @RequestMapping("pay")
    public String pay(@RequestParam(required = true) String orderId){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        if (userInfo !=null){
            UserInfo selUserInfo = userServiceImpl.selById(userInfo.id);
            float orderMoney = goodOrderServiceImpl.selMoney(orderId);
            if (selUserInfo.money>=orderMoney){
                //扣钱
                selUserInfo.money = selUserInfo.money-orderMoney;
                userServiceImpl.updMoney(selUserInfo);
                //改变订单状态
                goodOrderServiceImpl.updPayOrder(orderId);
                //刷新Session
                SessionUtils.refreshSession(request.getSession());
                return "redirect:goodOrder?state=2";
            }
            return "redirect:goodOrder?state=1";
        }
        return "redirect:/login.jsp";
    }

    /**
     * 由订单前往支付界面
     * @param orderId
     * @return
     */
    @RequestMapping("goPay")
    public String goPay(String orderId){
        if (request.getSession().getAttribute("user")!=null){
            float all_money = goodOrderServiceImpl.selMoney(orderId);
            request.setAttribute("all_money", all_money);
            request.setAttribute("orderId", orderId);
            return "pay";
        }
        return "login";
    }
}
