package dao;

import entity.Cart;
import entity.Good;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//购物车数据访问对象
public class CartDao {

    //商品查询对象
    GoodDao goodDao = new GoodDao();

    String url = "jdbc:mysql://127.0.0.1:3306/xmshop?serverTimezone=UTC";
    String db_user = "root";
    String db_pass = "zZ+411023";
    java.sql.Connection connection = null;

    //构造方法
    public CartDao() {
        try {
            //连接到数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(url, db_user, db_pass);
            System.out.println("连接到数据库成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //保存一个购物车对象
    public void saveCart(int user_id, int good_id, int buy_num) {
        try {
            String sql="select * from cart where good_id="+good_id;
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            if(resultSet.next()){
                connection.createStatement().executeUpdate("update cart set num=num+1 where good_id="+good_id);
            }else {
                //获取商品
                Good good = goodDao.findById(good_id);
                //计算价格
                float money = good.price * buy_num;
                String sql2 = String.format("insert into cart(user_id,good_id,num,buy_time,money) values(%s,%s,%s,now(),%s)", user_id, good_id, buy_num, money);
                connection.createStatement().executeUpdate(sql2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据购物车编号改变当前选择的购物车的状态
    public void changeState(int cartId){
        try {
            String sql="select * from cart where id="+cartId;
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()){
                if(resultSet.getInt("flag")==1){
                    String sql2="update cart set flag=2 where id="+cartId;
                    connection.createStatement().executeUpdate(sql2);
                }else {
                    String sql3="update cart set flag=1 where id="+cartId;
                    connection.createStatement().executeUpdate(sql3);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据数量重新设置购物车
    public void setCart(int user_id, int good_id, int buy_num) {
        try {
            //获取商品呢
            Good good = goodDao.findById(good_id);
            //计算价格
            float money = good.price * buy_num;
            String sql = String.format("update cart set money=%s,num=%s where user_id =%s and good_id=%s",money,buy_num,user_id, good_id);
            connection.createStatement().executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据用户编号查询购物车
    public List<Cart> getCartByUserId(int userId) {
        List<Cart> cartList = new ArrayList<>();
        String sql = String.format("select * from cart,good where user_id=%s  and cart.good_id=good.id", userId);
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                Cart cart = new Cart();
                cart.id = resultSet.getInt("id");
                cart.good_id = resultSet.getInt("good_id");
                cart.buy_time = resultSet.getString("buy_time");
                cart.money = resultSet.getFloat("money");
                cart.user_id = resultSet.getInt("user_id");
                cart.num = resultSet.getInt("num");
                cart.good_name = resultSet.getString("name");
                cart.good_pic = resultSet.getString("pic1");
                cart.good_price = resultSet.getFloat("price");
                cart.flag = resultSet.getInt("flag");
                cartList.add(cart);
            }
            return cartList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据id删除购物车
    public void deleteCart(int id) {
        String sql = "delete from cart where id="+id;
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //根据id查看购物车中是否全选
    public boolean isSelectAll(int userId){

        try {
            String sql="select * from cart where flag=2 and user_id="+userId;
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            if (resultSet.next()){
                //如果存在flag=2的购物单，则没有全选，返回false
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //根据用户id全选所有购物车
    public void selectAll(int userId){
        try {
            String sql="update cart set flag=1 where user_id="+userId;
            connection.createStatement().executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据用户id全不选
    public void clearAll(int userId){
        try {
            String sql="update cart set flag=2 where user_id="+userId;
            connection.createStatement().executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
