package cn.javak.controller;

import cn.javak.pojo.Cart;
import cn.javak.pojo.Good;
import cn.javak.pojo.UserInfo;
import cn.javak.service.CartService;
import cn.javak.service.GoodService;
import cn.javak.service.UserService;
import cn.javak.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartServiceImpl;
    @Autowired
    private GoodService goodServiceImpl;
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private HttpServletRequest request;

    /**
     * 访问购物车
     *
     * @return
     */
    @RequestMapping("cart")
    public String goCart() {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        List<Cart> cartList = cartServiceImpl.selAll(userInfo.getId());
        //总金额
        float all = 0;
        //总数量（选择的数目以及购物车中全部数目）
        int select = 0;
        int sum = 0;
        Good good;
        Cart cart;
        for (int i = 0; i <= cartList.size() - 1; i = i + 1) {
            cart = cartList.get(i);
            sum++;
            if (cartList.get(i).flag == 1) {
                select++;
                all = all + cart.money;
            }
            good = goodServiceImpl.selById(cart.getGood_id());
            cart.setGood_name(good.name);
            cart.setGood_pic(good.pic1);
            cart.setGood_price(good.price);
        }
        //全选为true 未全选未false
        boolean flag2 = flag2 = cartServiceImpl.selNoSelect(userInfo.getId()).size() > 0 ? false : true;
        request.setAttribute("flag2", flag2);
        request.setAttribute("cartList", cartList);
        request.setAttribute("all", all);
        request.setAttribute("sum", sum);
        request.setAttribute("select", select);
        return "cart";
    }

    /**
     * 新增购物车记录
     *
     * @param goodId
     * @return
     */
    @RequestMapping("addCart")
    public String addCart(@RequestParam(required = true) int goodId) {
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        int userId = userInfo.getId();//用户id
        Good good = goodServiceImpl.selById(goodId);//商品
        Cart newCart = new Cart();
        newCart.setUser_id(userId);
        newCart.setGood_id(goodId);
        newCart.setNum(1);
        newCart.setMoney(good.price);
        cartServiceImpl.insCart(newCart);
        System.out.println("用户[" + userInfo.name + "]新增一条购物车记录[" + good.name + "]");
        //刷新Session
        SessionUtils.refreshSession(session);
        return "redirect:/cart";
    }

    /**
     * 添加商品数
     *
     * @param cart
     * @return
     */
    @RequestMapping("addNum")
    public String addNum(Cart cart) {
        cart = cartServiceImpl.selByCart(cart);
        Good good = goodServiceImpl.selById(cart.good_id);
        cart.num++;
        cart.money += good.price;
        cartServiceImpl.updCart(cart);
        return "redirect:cart";
    }

    /**
     * 减少商品数
     *
     * @param cart
     * @return
     */
    @RequestMapping("divNum")
    public String divNum(Cart cart) {
        cart = cartServiceImpl.selByCart(cart);
        if (cart.num > 1) {
            Good good = goodServiceImpl.selById(cart.good_id);
            cart.num--;
            cart.money -= good.price;
            cartServiceImpl.updCart(cart);
        }
        return "redirect:cart";
    }

    /**
     * 选择购物记录
     *
     * @param cart
     * @return
     */
    @RequestMapping("select")
    public String selectGood(Cart cart) {
        if (cart.id != 0) {
            cartServiceImpl.updFlag(cart);
        } else {
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
            cartServiceImpl.updCartListStatus(userInfo.getId());
        }
        return "redirect:cart";
    }

    /**
     * 删除购物记录
     *
     * @param cart
     * @return
     */
    @RequestMapping("deleteCart")
    public String deleteCart(Cart cart) {
        cartServiceImpl.delCart(cart.id);
        //刷新Session
        SessionUtils.refreshSession(request.getSession());
        return "redirect:cart";
    }
}
