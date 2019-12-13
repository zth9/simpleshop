package dao;

import entity.picture;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class pictureDao {

    String url = "jdbc:mysql://127.0.0.1:3306/xmshop?serverTimezone=UTC";
    String db_user = "root";
    String db_pass = "zZ+411023";
    java.sql.Connection connection = null;

    //构造方法
    public pictureDao() {
        try {
            //连接到数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(url, db_user, db_pass);
            System.out.println("连接到数据库成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<picture> findById(int id) {

        try {
            //创建SQL
            List<picture> pictureList = new ArrayList<>();
            String sql = String.format("select * from picture where good_id=%s", id);
            System.out.println(sql);
            //查询
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            //读取
            picture pic=null;
            while (resultSet.next()) {
                pic = new picture();
                pic.id = resultSet.getInt("id");
                pic.good_id= resultSet.getInt("good_id");
                pic.pic = resultSet.getString("pic");
                pictureList.add(pic);
            }
            return pictureList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public picture findBypictureId(int id) {

        try {
            String sql = String.format("select * from picture where id=%s", id);
            System.out.println(sql);
            //查询
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            //读取
            picture pic2=null;
            while (resultSet.next()) {
                pic2 = new picture();
                pic2.id = resultSet.getInt("id");
                pic2.good_id= resultSet.getInt("good_id");
                pic2.pic = resultSet.getString("pic");
            }
            return pic2;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
