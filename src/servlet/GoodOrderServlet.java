package servlet;

import dao.GoodOrderDao;
import dao.UserDao;
import entity.GoodOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/goodOrder")
public class GoodOrderServlet extends HttpServlet {

    GoodOrderDao goodOrderDao = new GoodOrderDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //获取购物车宝贝数
        UserDao userDao = new UserDao();
        int baby_num = userDao.findByUserId((int) req.getSession().getAttribute("userId")).baby_num;
        int userId = (int) req.getSession().getAttribute("userId");

        //获取订单类型
        int state = Integer.parseInt(req.getParameter("state"));
        //查询用户对应的订单状态的所有订单
        ArrayList<ArrayList<GoodOrder>> orderList = goodOrderDao.getGoodOrderByUserIdAndState(userId, state);

        float all_money[] = new float[orderList.size()];
        int all_num[] = new int[orderList.size()];
        for (int i=0;i<orderList.size();i++){
            for (int j=0;j<orderList.get(i).size();j++){
                all_money[i]+=orderList.get(i).get(j).money;
                all_num[i]+=orderList.get(i).get(j).buy_num;
            }
        }
        req.setAttribute("orderList", orderList);
        req.setAttribute("state",state);
        req.setAttribute("all_money",all_money);
        req.setAttribute("all_num",all_num);
        req.setAttribute("baby_num",baby_num);
        req.getRequestDispatcher("/order.jsp").forward(req, resp);

    }
}