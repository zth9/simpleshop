package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class regServlet extends HttpServlet {

    //创建用户查询对象
    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取http表单参数
        String name = req.getParameter("user");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");

        //通过名字查询用户
        User user = userDao.findByName(name);
        if (password.equals("")){
            req.setAttribute("msg", "密码不能为空");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
        //重定向到页面
        if (user == null) {//数据库中无相同用户名用户，可以注册
            int flag = userDao.register(name, password, password2);
            if(flag == -2) {
                req.setAttribute("msg", "服务器开小差");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }else if (flag == -1){
                req.setAttribute("msg", "两次输入密码不一致");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }else if (flag == 1){
                req.setAttribute("msg", "注册成功！");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }

        }else {
            req.setAttribute("msg", "用户名重复！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
