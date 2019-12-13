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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("value");
        GoodDao good=new GoodDao();
        GoodTypeDao goodTypeDao = new GoodTypeDao();

        UserDao userDao=new UserDao();
        int baby_num=0;
        try {
            baby_num = userDao.findByUserId((int) req.getSession().getAttribute("userId")).baby_num;
        }catch (Exception e){
            //当没有登录时跳过该过程
        }


        List<Good> goodlist=good.findByName(name);
        List<GoodType> goodTypeList = goodTypeDao.findAll();
        if(goodlist==null){
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("goodList", goodlist);
            req.setAttribute("goodTypeList", goodTypeList);
            req.setAttribute("baby_num",baby_num);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
