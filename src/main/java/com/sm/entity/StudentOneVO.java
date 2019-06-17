package com.sm.entity;

public class StudentOneVO {
    private String id;
    private String departmentName;
    private String className;
    private String studentName;
    private String gender;
    private String chinese;
    private String english;
    private String avatar;
    private String math;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    @Override
    public String toString() {
        return "StudentOneVO{" + "id='" + id + '\'' + "," +
                " departmentName='" + departmentName + '\'' + ", " +
                "className='" + className + '\'' + ", " +
                "studentName='" + studentName + '\'' + "," +
                " gender='" + gender + '\'' + ", " +
                "chinese='" + chinese + '\'' + ", " +
                "english='" + english + '\'' + ", " +
                "avatar='" + avatar + '\'' + ", " +
                "math='" + math + '\'' + '}';
    }
}
