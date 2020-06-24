package cn.javak.controller;

import cn.javak.pojo.GoodOrder;
import cn.javak.pojo.UserInfo;
import cn.javak.service.GoodOrderService;
import cn.javak.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GoodOrderController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GoodOrderService goodOrderServiceImpl;

    /**
     * 根据用户id访问订单
     *
     * @param state
     * @return
     */
    @RequestMapping("goodOrder")
    public String goOrder(String state) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        //获取订单
        state = String.valueOf(state == null ? 1 : Integer.parseInt(state));
        List<List<GoodOrder>> orderList = goodOrderServiceImpl.selAllOrder(userInfo.getId(), Integer.parseInt(state));
        float all_money[] = new float[orderList.size()];
        int all_num[] = new int[orderList.size()];
        for (int i = 0; i < orderList.size(); i++) {
            for (int j = orderList.get(i).size() - 1; j == 0; j--) {
                all_money[i] += orderList.get(i).get(j).money;
                all_num[i] += orderList.get(i).get(j).buy_num;
            }
        }
        request.setAttribute("orderList", orderList);
        request.setAttribute("state", state);
        request.setAttribute("all_money", all_money);
        request.setAttribute("all_num", all_num);
        return "order";
    }

    /**
     * 提交订单
     *
     * @return
     */
    @RequestMapping("addGoodOrder")
    public String addGoodOrder() {
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        goodOrderServiceImpl.updSubmitOrder(userInfo.getId());
        //刷新session
        SessionUtils.refreshSession(session);
        return "redirect:goodOrder";
    }

    /**
     * 删除订单
     *
     * @param orderId
     * @return
     */
    @RequestMapping("deleteOrder")
    public String deleteOrder(String orderId) {
        if (orderId != null) {
            goodOrderServiceImpl.delOrder(orderId);
        }
        return "redirect:goodOrder";
    }
}
