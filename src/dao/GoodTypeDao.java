package dao;

import entity.GoodType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//商品类型数据访问类
public class GoodTypeDao {
    String url = "jdbc:mysql://127.0.0.1:3306/xmshop?serverTimezone=UTC";
    String db_user = "root";
    String db_pass = "zZ+411023";
    java.sql.Connection connection = null;

    //构造方法
    public GoodTypeDao() {
        try {
            //连接到数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(url, db_user, db_pass);
            System.out.println("连接到数据库成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询所有商品类型
    public List<GoodType> findAll() {

        List list = new ArrayList();
        try {
            String sql = "select * from good_type";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                if(resultSet.getInt("id")==9){
                    break;
                }else {
                    GoodType goodType = new GoodType();
                    goodType.id = resultSet.getInt("id");
                    goodType.name = resultSet.getString("name");
                    goodType.num = resultSet.getInt("num");
                    list.add(goodType);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
