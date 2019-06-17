package com.sm.entity;

public class Stu {
    private Integer id;
    private String account;
    private String password;
    private String stuName;

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

    public String getAdminName() {
        return stuName;
    }

    public void setAdminName(String adminName) {
        this.stuName = adminName;
    }

    @Override
    public String toString() {
        return "Stu{" + "id=" + id + ", " +
                "account='" + account + '\'' + "," +
                " password='" + password + '\'' + "," +
                " stuName='" + stuName + '\'' + '}';
    }
}
