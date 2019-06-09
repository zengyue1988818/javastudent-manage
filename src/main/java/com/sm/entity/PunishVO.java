package com.sm.entity;

import java.util.Date;

public class PunishVO {
    private Integer id;
    private String studentName;
    private String avatar;
    private String className;
    private String departmentName;
    private String punish;
    private String studentId;
    private Date puDate;

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

    public String getPunish() {
        return punish;
    }

    public void setPunish(String punish) {
        this.punish = punish;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getPunishDate() {
        return puDate;
    }

    public void setPunishDate(Date punishDate) {
        this.puDate = punishDate;
    }


    @Override
    public String toString() {
        return "PunishVO{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", className='" + className + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", punish='" + punish + '\'' +
                ", studentId='" + studentId + '\'' +
                ", punishDate=" + puDate +
                '}';
    }
}
