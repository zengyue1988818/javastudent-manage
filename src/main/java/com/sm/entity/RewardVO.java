package com.sm.entity;

import java.util.Date;

public class RewardVO {
    private Integer id;
    private String studentName;
    private String className;
    private String avatar;
    private String departmentName;
    private String reward;
    private String studentId;
    private Date reDate;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getRewardDate() {
        return reDate;
    }

    public void setRewardDate(Date rewardDate) {
        this.reDate = rewardDate;
    }

    @Override
    public String toString() {
        return "RewardVO{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", className='" + className + '\'' +
                ", avatar='" + avatar + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", reward='" + reward + '\'' +
                ", studentId='" + studentId + '\'' +
                ", rewardDate=" + reDate +
                '}';
    }
}
