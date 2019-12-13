package dao;

import entity.User;

import java.sql.ResultSet;

//负责User对象的数据访问
public class UserDao {
    String url = "jdbc:mysql://127.0.0.1:3306/xmshop?serverTimezone=UTC";
    String db_user = "root";
    String db_pass = "zZ+411023";
    java.sql.Connection connection = null;

    //构造方法
    public UserDao() {
        try {
            //连接到数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(url, db_user, db_pass);
            System.out.println("连接到数据库成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据名字查询用户(包括用户的购物车和订单)
    public User findByName(String name) {
        User user=null;
        try {
            //SQL
            String sql1 = String.format("select * from user where name='%s'", name);
            ResultSet resultSet1 = connection.createStatement().executeQuery(sql1);
            while (resultSet1.next()) {
                user = new User();
                user.id = resultSet1.getInt("id");
                user.name = resultSet1.getString("name");
                user.password = resultSet1.getString("password");
                user.money = resultSet1.getFloat("money");
                String sql2 = String.format("select count(*) from cart,user WHERE user.name='%s' and cart.user_id=user.id", name);
                ResultSet resultSet2 = connection.createStatement().executeQuery(sql2);
                while (resultSet2.next()) {
                    user.baby_num=resultSet2.getInt("count(*)");
                }
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据id查询用户(包括用户的购物车和订单)
    public User findByUserId(int userId) {
        User user=null;
        try {
            //SQL
            String sql1 = String.format("select * from user where id=%s", userId);
            ResultSet resultSet1 = connection.createStatement().executeQuery(sql1);
            while (resultSet1.next()) {
                user = new User();
                user.id = resultSet1.getInt("id");
                user.name = resultSet1.getString("name");
                user.password = resultSet1.getString("password");
                user.money = resultSet1.getFloat("money");
                String sql2 = String.format("select count(*) from cart,user WHERE user.id=%s and cart.user_id=user.id", userId);
                ResultSet resultSet2 = connection.createStatement().executeQuery(sql2);
                while (resultSet2.next()) {
                    //计算购物车中的行数
                    user.baby_num=resultSet2.getInt("count(*)");
                }
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //注册用户(-2服务器繁忙 -1二次密码不一致 1注册成功)
    public int register(String name,String password,String password2) {
        int flag=-2;
        try {
            //SQL
                if(password.equals(password2)){//如果二次输入密码相同
                    String sql = String.format("insert into user(name,password,money) values('%s','%s',1000000)",name,password);
                    System.out.println(sql);
                    connection.createStatement().executeUpdate(sql);
                    flag=1;
                }else {//如果二次输入密码不同
                    flag=-1;
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;//代码异常
    }

}
