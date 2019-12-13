package entity;

//购物车类
public class Cart {

    //购物车表
    public int id;
    public int user_id;
    public int good_id;
    public  int num;
    public String buy_time;
    public float money;
    public int flag;


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    //商品表的名字和图片
    public String good_name;
    public String good_pic;
    public float good_price;


    public Cart(){}

    public float getGood_price() {
        return good_price;
    }

    public void setGood_price(float good_price) {
        this.good_price = good_price;
    }

    public Cart(int id, int user_id, int good_id, int num, String buy_time) {
        this.id = id;
        this.user_id = user_id;
        this.good_id = good_id;
        this.num = num;
        this.buy_time = buy_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGood_id() {
        return good_id;
    }

    public void setGood_id(int good_id) {
        this.good_id = good_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(String buy_time) {
        this.buy_time = buy_time;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
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
}
