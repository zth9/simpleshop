package servlet;

import dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Setnum1")

public class Setnumsub extends HttpServlet {
    CartDao carDao = new CartDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        int buyNum = Integer.parseInt(req.getParameter("buy_num"));
        if(buyNum==1){
        }else {
            buyNum=buyNum-1;
        }
        //获取会话中的登录用户编号
        int userId = (int) req.getSession().getAttribute("userId");

        carDao.setCart(userId,id,buyNum);
        req.getRequestDispatcher("cart").forward(req, resp);
    }


}