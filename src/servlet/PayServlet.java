package servlet;

import dao.GoodOrderDao;
import dao.UserDao;
import entity.GoodOrder;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/pay")
public class PayServlet extends HttpServlet {

    UserDao userDao=new UserDao();
    GoodOrderDao goodOrderDao=new GoodOrderDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过用户名和ID获取用户的余额以及要支付的订单List
        int userId = (int)req.getSession().getAttribute("userId");
        String orderId = req.getParameter("orderId");
        User user = userDao.findByUserId(userId);
        ArrayList<ArrayList<GoodOrder>> orderList=goodOrderDao.getGoodOrderByUserIdAndState(userId,1);
        //根据订单号获取订单金额。
        float all_money=0;
        for (int i=0;i<orderList.size();i++){
            if (orderList.get(i).get(0).orderId.equals(orderId)){
                for (int j=0;j<orderList.get(i).size();j++){
                    all_money+=orderList.get(i).get(j).money;
                }
                break;
            }else {
                continue;
            }
        }
        //将余额,订单金额和订单传递给req再传递给支付页面
        req.setAttribute("user_money",user.money);
        req.setAttribute("all_money",all_money);
        req.setAttribute("orderId",orderId);
        req.getRequestDispatcher("/pay.jsp").forward(req,resp);
    }
}
