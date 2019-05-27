package com.sm.factory;

import com.sm.dao.AdminDAO;
import com.sm.dao.CClassDAO;
import com.sm.dao.DepartmentDAO;
import com.sm.dao.StudentDAO;
import com.sm.dao.impl.AdminDAOImpl;
import com.sm.dao.impl.CClassDAOImpl;
import com.sm.dao.impl.DepartmentDAOImpl;
import com.sm.dao.impl.StudentDAOImpl;
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
}


