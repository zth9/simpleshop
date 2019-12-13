package dao;

import entity.Good;
import entity.GoodOrder;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//订单数据库操作类
public class GoodOrderDao {
    String url = "jdbc:mysql://127.0.0.1:3306/xmshop?serverTimezone=UTC";
    String db_user = "root";
    String db_pass = "zZ+411023";
    java.sql.Connection connection = null;

    //构造方法
    public GoodOrderDao() {
        try {
            //连接到数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(url, db_user, db_pass);
            System.out.println("连接到数据库成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加订单
    public void addGoodOrder(int userId, int goodId, int buyNum, float money, String orderId) {
        try {
            String sql1 = "insert into good_order(user_id,good_id,buy_num,buy_time,money,state,orderId) values(%s,%s,%s,now(),%s,1,'%s')";
            sql1 = String.format(sql1, userId, goodId, buyNum, money, orderId);
            connection.createStatement().executeUpdate(sql1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据用户编号和订单状态查询订单
    public ArrayList<ArrayList<GoodOrder>> getGoodOrderByUserIdAndState(int user_id, int state) {
        ArrayList<GoodOrder> goodOrderList = new ArrayList<>();
        ArrayList<ArrayList<GoodOrder>> orderList = new ArrayList<>();
        try {
            //三表连接查询
            String sql =
                    "select " +
                            "   user.name as user_name, " +
                            "   good.pic1,good.name as good_name, " +
                            "   good_order.id,good_order.buy_time,good_order.buy_num,good_order.state,good_order.money,good_order.orderId " +
                            "from " +
                            "   good_order,good,user " +
                            "where " +
                            "       good_order.user_id=user.id and good_order.good_id=good.id " +
                            "    and  " +
                            "       user_id=%s and state=%s" +
                            "  order by orderId desc";
            sql = String.format(sql, user_id, state);
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                GoodOrder goodOrder = new GoodOrder();
                //如果小列表中有订单
                if (!goodOrderList.isEmpty()) {
                    //判断如果属于同一订单号，将此订单插入小列表，并跳出本次循环
                    if (resultSet.getString("orderId").equals(goodOrderList.get(goodOrderList.size() - 1).orderId)) {
                        goodOrder.id = resultSet.getInt("id");
                        goodOrder.buy_num = resultSet.getInt("buy_num");
                        goodOrder.buy_time = resultSet.getString("buy_time");
                        goodOrder.state = resultSet.getInt("state");
                        goodOrder.money = resultSet.getFloat("money");
                        //用户
                        goodOrder.user_name = resultSet.getString("user_name");
                        //商品
                        goodOrder.good_name = resultSet.getString("good_name");
                        goodOrder.good_pic = resultSet.getString("pic1");
                        goodOrder.price = goodOrder.money / goodOrder.buy_num;//非查表得
                        goodOrder.orderId = resultSet.getString("orderId");
                        goodOrderList.add(goodOrder);
                        continue;
                    }
                    else {
                        //将小列表插入大列表
                        orderList.add(new ArrayList<>(goodOrderList));
                        //后清空小列表，并把当前遍历的订单插入清空后的小列表中，跳出本次循环
                        goodOrderList.clear();
                        goodOrder.id = resultSet.getInt("id");
                        goodOrder.buy_num = resultSet.getInt("buy_num");
                        goodOrder.buy_time = resultSet.getString("buy_time");
                        goodOrder.state = resultSet.getInt("state");
                        goodOrder.money = resultSet.getFloat("money");
                        //用户
                        goodOrder.user_name = resultSet.getString("user_name");
                        //商品
                        goodOrder.good_name = resultSet.getString("good_name");
                        goodOrder.good_pic = resultSet.getString("pic1");
                        goodOrder.price = goodOrder.money / goodOrder.buy_num;//非查表得
                        goodOrder.orderId = resultSet.getString("orderId");
                        goodOrderList.add(goodOrder);
                        continue;
                    }
                }
                //如果小列表中无订单，插入小列表
                else {
                    goodOrder.id = resultSet.getInt("id");
                    goodOrder.buy_num = resultSet.getInt("buy_num");
                    goodOrder.buy_time = resultSet.getString("buy_time");
                    goodOrder.state = resultSet.getInt("state");
                    goodOrder.money = resultSet.getFloat("money");
                    //用户
                    goodOrder.user_name = resultSet.getString("user_name");
                    //商品
                    goodOrder.good_name = resultSet.getString("good_name");
                    goodOrder.good_pic = resultSet.getString("pic1");
                    goodOrder.price = goodOrder.money / goodOrder.buy_num;//非查表得
                    goodOrder.orderId = resultSet.getString("orderId");
                    goodOrderList.add(goodOrder);
                    continue;
                }
            }
            //add中添加对象而不是对象的引用，这样可以避免clear小列表后使大列表原来所指的内容也跟着不见
            orderList.add(new ArrayList<>(goodOrderList));
            goodOrderList.clear();
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据订单id删除订单
    public void deleteOrderByOrderId(String  orderId) {
        try {
            String sql = "delete from good_order where orderId='%s';";
            sql = String.format(sql, orderId);
            connection.createStatement().executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}