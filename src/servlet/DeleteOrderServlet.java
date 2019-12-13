package servlet;

import dao.GoodOrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//订单
@WebServlet("/deleteOrder")
public class DeleteOrderServlet extends HttpServlet {

    //购物车数据库操作对象
    GoodOrderDao goodOrderDao = new GoodOrderDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取要删除的订单编号
        String orderId = req.getParameter("orderId");

        //2.执行删除
        goodOrderDao.deleteOrderByOrderId(orderId);


        //3.跳转
        req.getRequestDispatcher("/goodOrder?state=1").forward(req,resp);

    }
}