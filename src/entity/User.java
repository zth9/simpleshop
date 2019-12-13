package entity;

//用户类
public class User {
    //本表
    public int id;
    public String name;
    public String password;
    public float money;
    //购物车表
    public int baby_num;

    public int getBaby_num() {
        return baby_num;
    }

    public void setBaby_num(int baby_num) {
        this.baby_num = baby_num;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
