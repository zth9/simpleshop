package servlet;

import dao.CartDao;
import entity.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//查看自己的购物车功能
@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    //购物车查询对象
    CartDao cartDao=new CartDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //获取个人的购物车记录
        int userId = (int) req.getSession().getAttribute("userId");
        List<Cart> cartList = cartDao.getCartByUserId(userId);

        //总金额
        float all=0;
        //总数量（选择的数目以及购物车中全部数目）
        int select=0;
        int sum=0;
        for(int i=0;i<=cartList.size()-1;i=i+1){
            sum+=cartList.get(i).num;
            if(cartList.get(i).flag==1){
                select+=cartList.get(i).num;
                all=all+cartList.get(i).money;
            }
        }
        boolean flag2=false;
        try{
            flag2=cartDao.isSelectAll(userId);
        }catch (Exception e){
        }

        //设置
        req.setAttribute("flag2",flag2);
        req.setAttribute("cartList", cartList);
        req.setAttribute("all", all);
        req.setAttribute("sum", sum);
        req.setAttribute("select", select);
        //跳转显示
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }
}
