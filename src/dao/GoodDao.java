package dao;

import entity.Good;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//商品数据库访问类
public class GoodDao {

    String url = "jdbc:mysql://127.0.0.1:3306/xmshop?serverTimezone=UTC";
    String db_user = "root";
    String db_pass = "zZ+411023";
    java.sql.Connection connection = null;

    //构造方法
    public GoodDao() {
        try {
            //连接到数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(url, db_user, db_pass);
            System.out.println("连接到数据库成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据分类编号查询所有商品
    public List<Good> findByGoodTypeId(int goodTypeId) {

        List<Good> goodList = new ArrayList<>();

        try {
            //创建SQL
            String sql = String.format("select * from good where good_type_id=%s", goodTypeId);
            System.out.println(sql);
            //查询
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            //读取
            while (resultSet.next()) {
                Good good = new Good();
                good.id = resultSet.getInt("id");
                good.name = resultSet.getString("name");
                good.good_type_id = resultSet.getInt("good_type_id");
                good.detail = resultSet.getString("detail");
                good.price = resultSet.getFloat("price");
                good.pic1 = resultSet.getString("pic1");
                good.pic2 = resultSet.getString("pic2");
                good.pic3 = resultSet.getString("pic3");
                good.pic4 = resultSet.getString("pic4");
                goodList.add(good);
            }
            return goodList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据商品id查询商品
    public Good findById(int id) {

        try {
            //创建SQL
            String sql = String.format("select * from good where id=%s", id);
            System.out.println(sql);
            //查询
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            //读取
            Good good=null;
            while (resultSet.next()) {
                good = new Good();
                good.id = resultSet.getInt("id");
                good.name = resultSet.getString("name");
                good.good_type_id = resultSet.getInt("good_type_id");
                good.detail = resultSet.getString("detail");
                good.price = resultSet.getFloat("price");
                good.pic1 = resultSet.getString("pic1");
                good.pic2 = resultSet.getString("pic2");
                good.pic3 = resultSet.getString("pic3");
                good.pic4 = resultSet.getString("pic4");
            }
            return good;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    //根据商品名查询商品
    public List<Good> findByName(String name) {

        try {
            //创建SQL
            String sql = "select * from good where name Like '%"+name+"%'";
            System.out.println(sql);
            //查询
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            //读取
            List<Good> goodlist=new ArrayList<>();
            while (resultSet.next()) {
                Good good = new Good();
                good.id = resultSet.getInt("id");
                good.name = resultSet.getString("name");
                good.good_type_id = resultSet.getInt("good_type_id");
                good.detail = resultSet.getString("detail");
                good.price = resultSet.getFloat("price");
                good.pic1 = resultSet.getString("pic1");
                good.pic2 = resultSet.getString("pic2");
                good.pic3 = resultSet.getString("pic3");
                goodlist.add(good);
            }
            return goodlist;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
