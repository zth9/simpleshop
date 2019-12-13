package dao;

import entity.GoodOrder;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayDao {
    String url = "jdbc:mysql://127.0.0.1:3306/xmshop?serverTimezone=UTC";
    String db_user = "root";
    String db_pass = "zZ+411023";
    java.sql.Connection connection = null;

    //构造方法
    public PayDao() {
        try {
            //连接到数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(url, db_user, db_pass);
            System.out.println("连接到数据库成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int PayByUserIdAndOrderId(int userId,String orderId) throws SQLException {
        UserDao userDao=new UserDao();
        GoodOrderDao goodOrderDao=new GoodOrderDao();
        User user = userDao.findByUserId(userId);
        ArrayList<ArrayList<GoodOrder>> orderList = goodOrderDao.getGoodOrderByUserIdAndState(userId, 1);
        float user_money = user.money;
        float order_money = 0;
        for (int i=0;i<orderList.size();i++){
            if (orderList.get(i).get(0).orderId.equals(orderId)){
                for (int j=0;j<orderList.get(i).size();j++){
                    order_money+=orderList.get(i).get(j).money;
                }
                break;
            }
            continue;
        }
        if(user_money>order_money){
            //支付成功改变订单的状态为2（未发货）同时从用户表中扣除这笔钱
            try {
                String sql3=String.format("update good_order set buy_time=now(),state=2 where user_id=%s and orderId='%s' and state=1;",userId,orderId);
                System.out.println(sql3);
                connection.createStatement().executeUpdate(sql3);
                String sql2 = String.format("update user set money=%s where id=%s;",user_money-order_money,userId);
                connection.createStatement().executeUpdate(sql2);
            }catch (Exception e){
                e.printStackTrace();
            }
            return 1;
        }else {
            return 0;
        }
    }

}
