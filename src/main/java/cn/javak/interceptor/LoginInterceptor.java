package cn.javak.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断是否登录(登陆后session中存储user)
 * 未登录不能调用控制器方法
 *  implements HandlerInterceptor
 */
public class LoginInterceptor {
//    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.endsWith("login")){
            return true;
        }else {
            if (request.getSession().getAttribute("user")!=null){
                return true;
            }else {
                //response.sendRedirect("login.jsp");
                return true;
            }
        }
        //return false;
    }

//    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

//    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
