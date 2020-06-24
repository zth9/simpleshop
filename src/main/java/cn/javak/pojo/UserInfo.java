package cn.javak.pojo;

import java.util.List;

//用户类
public class UserInfo {
    //本表a
    public int id;
    public String name;
    public String password;
    public float money;
    //购物车表
    public int baby_num;

    public List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

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
