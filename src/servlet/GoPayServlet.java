package servlet;

import dao.GoodOrderDao;
import dao.PayDao;
import dao.UserDao;
import entity.GoodOrder;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/goPay")
public class GoPayServlet extends HttpServlet {

    PayDao payDao=new PayDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取用户的id
        int userId = Integer.parseInt(req.getParameter("userId"));
        String orderId = req.getParameter("orderId");

        //返回该用户支付结果（0余额不足1支付成功）
        int flag = 0;
        try {
            flag = payDao.PayByUserIdAndOrderId(userId,orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(flag==1){//余额充足
            req.getRequestDispatcher("goodOrder?state=2").forward(req,resp);
        }else {
            req.getRequestDispatcher("goodOrder?state=1").forward(req,resp);
        }
    }
}
