package Listener;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.*;

@WebListener
public class iListener implements ServletRequestListener, ServletRequestAttributeListener, HttpSessionListener, HttpSessionAttributeListener, ServletContextListener, ServletContextAttributeListener {
    //监听Request对象销毁
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        //System.out.println("iListener.requestDestroyed");
    }
    //监听Request对象创建
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //System.out.println("iListener.requestInitialized");
    }
    //监听Request作用域数据添加
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
    }
    //监听Request作用域数据移除
    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
    }
    //监听Request作用域数据更改
    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
    }
    /***********************************************************************************************/
    //监听Session对象创建
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }
    //监听Session对象销毁
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Integer onlineNumber = (Integer)se.getSession().getServletContext().getAttribute("onlineNumber");
        se.getSession().getServletContext().setAttribute("onlineNumber",onlineNumber-1);
    }
    //监听Session对象添加数据
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        //System.out.println("Session对象添加数据    "+se.getName()+"-->"+se.getValue());
        if (se.getName().equals("name")){
            Integer onlineNumber = (Integer)se.getSession().getServletContext().getAttribute("onlineNumber");
            se.getSession().getServletContext().setAttribute("onlineNumber",onlineNumber+1);
        }
    }
    //监听Session对象删除数据
    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
    }
    //监听Session对象更改数据
    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
    }
    /***********************************************************************************************/
    //监听ServletContext对象创建
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("onlineNumber",0);
        String s = "";
        BufferedReader br = null;
        try {//读取主页的点击率
            String filePath = sce.getServletContext().getRealPath("/WEB-INF/classes/Listener/clickNum.txt");
            br = new BufferedReader(new FileReader(filePath));//读取classes文件夹下的文件
            s = br.readLine();
            System.out.println("读取到的数据-->"+s);
            int i = Integer.parseInt(s);
            sce.getServletContext().setAttribute("clickNum",i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //监听ServletContext对象销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        BufferedWriter bw = null;
        try {
            String filePath = sce.getServletContext().getRealPath("/WEB-INF/classes/Listener/clickNum.txt");
            bw = new BufferedWriter(new FileWriter(filePath));
            Integer clickNum = (Integer)sce.getServletContext().getAttribute("clickNum");
            bw.write(clickNum+"");
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //监听ServletContext对象添加数据
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
    }
    //监听ServletContext对象删除数据
    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
    }
    //监听ServletContext对象更改数据
    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
    }
}