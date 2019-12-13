package servlet;

import dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/select")
public class SelectServlet extends HttpServlet {
    CartDao cartDao=new CartDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cartId;
        int userId = (int)req.getSession().getAttribute("userId");
        try{
            //全选
            cartId = Integer.parseInt(req.getParameter("cartId"));
        }catch (Exception e){
            boolean flag = cartDao.isSelectAll(userId);
            if(flag){
                //如果全选,则全不选
                cartDao.clearAll(userId);
                req.getRequestDispatcher("cart").forward(req,resp);
            }else {
                //如果全不选,则全选
                cartDao.selectAll(userId);
                req.setAttribute("flag",true);
                req.getRequestDispatcher("cart").forward(req,resp);
            }
        }
        //单选
        cartId = Integer.parseInt(req.getParameter("cartId"));
        //改变当前选择的购物车的状态
        cartDao.changeState(cartId);
        //跳转
        if (cartDao.isSelectAll((int)req.getSession().getAttribute("userId"))){
            req.setAttribute("flag",true);
            req.getRequestDispatcher("cart").forward(req,resp);
        }else {
            req.setAttribute("flag",false);
            req.getRequestDispatcher("cart").forward(req,resp);
        }
    }
}
