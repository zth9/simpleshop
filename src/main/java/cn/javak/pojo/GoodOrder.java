package cn.javak.pojo;

//订单实体
public class GoodOrder {

    //本表
    public int id;
    public int good_id;
    public int user_id;
    public float money;//数量乘以单价
    public int buy_num;
    public int state;
    public String orderId;
    public String buy_time;
    //用户表
    public String user_name;
    //商品表
    public String good_name;
    public String good_pic;
    public float price;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    public String getGood_pic() {
        return good_pic;
    }

    public void setGood_pic(String good_pic) {
        this.good_pic = good_pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGood_id() {
        return good_id;
    }

    public void setGood_id(int good_id) {
        this.good_id = good_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getBuy_num() {
        return buy_num;
    }

    public void setBuy_num(int buy_num) {
        this.buy_num = buy_num;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(String buy_time) {
        this.buy_time = buy_time;
    }
}