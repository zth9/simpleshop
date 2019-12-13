package servlet;

import dao.GoodDao;
import dao.UserDao;
import entity.Good;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {


    //商品查询对象
    GoodDao goodDao = new GoodDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //0.获取购物车宝贝数
        UserDao userDao = new UserDao();
        int baby_num=0;
        try {
            baby_num = userDao.findByUserId((int) req.getSession().getAttribute("userId")).baby_num;
        }catch (Exception e){
            //当没有登录时跳过该过程
        }

        //1.获取商品编号
        String id = req.getParameter("id");
        //2.根据id查询商品
        Good good = goodDao.findById(Integer.parseInt(id));

        //2.1 获取点击的图片的信息
        String pic=null;
        try {
            pic = req.getParameter("pic");
        }catch (Exception e){
            //当没有传递参数时不做赋值
        }

        //3.设置
        req.setAttribute("good", good);
        //4.跳转
        req.setAttribute("baby_num",baby_num);
        req.setAttribute("pic",pic);
        req.getRequestDispatcher("/detail.jsp").forward(req, resp);
    }
}
