package com.sm.factory;

import com.sm.dao.*;
import com.sm.dao.impl.*;
import com.sm.entity.Department;

import java.sql.SQLException;
import java.util.List;

public class DAOFactory {
    public static AdminDAO getAdminDAOInstance() {
        return new AdminDAOImpl();
    }
    public static DepartmentDAO getDepartmentDAOInstance() {
        return new DepartmentDAOImpl();
    }
    public static CClassDAO getCClassDAOInstance(){
        return new CClassDAOImpl();
    }
    public static StudentDAO getStudentDAOInstance(){
        return new StudentDAOImpl();
    }
    public static  TeacherDAO getTeacherDAOInstance(){
        return new TeacherDAOImpl();
    }
    public static ScoreDAO getScoreDAOInstance(){
        return new ScoreDAOImpl();
    }
    public static StudentOneDAO getStudentOneDAOInstance(){
        return new StudentOneDAOImpl();
    }
    public static StuDAO getStuDAOInstance(){
        return new StuDAOImpl();
    }
    public static TeaDAO getTeaDAOInstance(){
        return new TeaDAOImpl();
    }
    public static TeachDAO getTeachDAOInstance(){
        return new TeachDAOImpl();
    }
}


