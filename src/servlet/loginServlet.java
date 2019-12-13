package servlet;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("用户已经开始登录了...");

        //获取http表单参数
        String name = req.getParameter("user");
        String password = req.getParameter("password");


        //通过名字查询用户
        User user = userDao.findByName(name);


        //重定向到页面
        if (user == null) {
            req.setAttribute("msg", "用户不存在");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
        if (!user.password.equals(password)) {
            req.setAttribute("msg", "密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("name",user.name);
            req.getSession().setAttribute("userId", user.id);
            req.getRequestDispatcher("index").forward(req, resp);
        }
    }

}

