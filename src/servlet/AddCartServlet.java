package servlet;

import dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//加入购物车功能
@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet {

    //购物车对象访问数据库
    CartDao carDao = new CartDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取商品编号和购买数量
        int good_id = Integer.parseInt(req.getParameter("id"));
        int buyNum = 1;
        //获取会话中的登录用户编号
        int userId;
        try {
            userId = (int) req.getSession().getAttribute("userId");
        }catch (Exception e){
            //如果用户未登陆，跳转到登录页面
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
        //把购物车保存到数据库
        userId = (int) req.getSession().getAttribute("userId");
        carDao.saveCart(userId, good_id, buyNum);
        req.getRequestDispatcher("cart").forward(req, resp);
    }
}
