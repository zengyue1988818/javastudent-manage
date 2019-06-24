package com.sm.entity;

public class Teach {
    private Integer id;
    private String teacherId;
    private String name;
    private String gender;
    private String tel;
    private String course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Teach{" + "id=" + id + "," +
                " teacherId='" + teacherId + '\'' + "," +
                " name='" + name + '\'' + ", " +
                "gender='" + gender + '\'' + ", " +
                "tel='" + tel + '\'' + "," +
                " course='" + course + '\'' + '}';
    }
}
