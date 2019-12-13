package servlet;

import dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//删除购物车功能
@WebServlet("/deleteCart")
public class DeleteCartServlet extends HttpServlet {

    //购物车数据库操作对象
    CartDao cartDao = new CartDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取要删除的购物车编号
        String id = req.getParameter("id");

        //2.执行删除
        cartDao.deleteCart(Integer.parseInt(id));

        //3.跳转
        req.getRequestDispatcher("cart").forward(req,resp);

    }
}
