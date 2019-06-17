package com.sm.entity;

public class Tea {
    private Integer id;
    private String account;
    private String password;
    private String teaName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    @Override
    public String toString() {
        return "Tea{" + "id=" + id + "," +
                " account='" + account + '\'' + "," +
                " password='" + password + '\'' + ", " +
                "teaName='" + teaName + '\'' + '}';
    }
}
