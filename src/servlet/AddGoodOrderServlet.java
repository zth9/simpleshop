package servlet;

import dao.CartDao;
import dao.GoodOrderDao;
import entity.Cart;
import entity.GoodOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//创建订单功能
@WebServlet("/addGoodOrder")
public class AddGoodOrderServlet extends HttpServlet {


    CartDao cartDao = new CartDao();

    GoodOrderDao goodOrderDao = new GoodOrderDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = (int) req.getSession().getAttribute("userId");
        //获取用户端的购物车中所有商品
        List<Cart> cartList = cartDao.getCartByUserId(userId);

        //获取购物车中的商品进入订单中
        float sum_money = 0;

        //初始化订单号
        String orderId="";
        for (int i = 0; i <= cartList.size() - 1; i = i + 1) {
            Cart cart = cartList.get(i);
            if(cart.flag==1){
                orderId = cart.buy_time;
                break;
            }
        }
        for (int i = 0; i <= cartList.size() - 1; i = i + 1) {
            Cart cart = cartList.get(i);
            //添加到订单
            if(cart.flag==1){
                sum_money = sum_money + cart.money;
                goodOrderDao.addGoodOrder(userId, cart.good_id, cart.num, cart.money,orderId);
                //清除当前购物车
                cartDao.deleteCart(cart.id);
            }
        }
        if (sum_money==0){
            req.getRequestDispatcher("cart").forward(req, resp);
        }

        //跳转到订单页
        req.setAttribute("sum_money", sum_money);
        req.getRequestDispatcher("/goodOrder?state=1").forward(req, resp);
    }
}
