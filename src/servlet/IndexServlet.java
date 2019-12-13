package servlet;

import dao.GoodDao;
import dao.GoodTypeDao;
import dao.UserDao;
import entity.Good;
import entity.GoodType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//Servlet用于提供后台数据处理并跳转
//1.获取参数
//2.处理数据
//3.转发
@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    //商品类型查询对象
    GoodTypeDao goodTypeDao = new GoodTypeDao();

    //商品查询对象
    GoodDao goodDao=new GoodDao();
    UserDao userDao=new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //0.获取购物车中宝贝数
        int baby_num=0;
        try {
            baby_num = userDao.findByUserId((int) req.getSession().getAttribute("userId")).baby_num;
        }catch (Exception e){
            //当没有登录时跳过该过程
        }
        //1.获取用户选择的商品分类编号
        String goodTypeId = req.getParameter("goodTypeId");
        if (goodTypeId == null || goodTypeId.length() == 0) goodTypeId = "1";

        //2.根据商品分类查询商品
        List<Good> goodList = goodDao.findByGoodTypeId(Integer.parseInt(goodTypeId));

        //3.查询所有商品分类
        List<GoodType> goodTypeList = goodTypeDao.findAll();

        //4.设置
        req.setAttribute("goodTypeList", goodTypeList);
        req.setAttribute("goodList",goodList);
        req.setAttribute("baby_num",baby_num);

        //5.重定向到显示页面
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }
}