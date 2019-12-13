package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "MyFilter", urlPatterns = { "/index" })// "*.xx"以.xx结尾的都会被拦截;以名字为结尾了单独过滤.如果有多个过滤器过滤一个Servlet则依次(范围由广到窄)执行
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //System.out.println("MyFilter.init执行初始化方法");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //System.out.println("MyFilter.doFilter入方向过滤");
        //设置请求编码格式
        servletRequest.setCharacterEncoding("utf-8");
        //设置相应编码格式
        servletResponse.setCharacterEncoding("utf-8");

        /**
         * 增加主页点击量
         */
        Integer clickNum = (Integer) servletRequest.getServletContext().getAttribute("clickNum");
        servletRequest.getServletContext().setAttribute("clickNum",clickNum+1);

        //servletResponse.setContentType("text/html; charset=utf-8");//针对resp.getWriter().write("XXX");避免出现字符集不匹配
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        //System.out.println("MyFilter.doFilter出方向过滤");
        /*HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        //判断Session是否失效,如果失效则让用户重新登录
        if (session.getAttribute("user")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            ((HttpServletResponse)servletResponse).sendRedirect("/login.jsp");
        }*/
    }

    @Override
    public void destroy() {
        //System.out.println("MyFilter.destroy执行销毁方法");
    }
}
