package com.sm.dao.impl;

import com.sm.dao.CClassDAO;
import com.sm.entity.CClass;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class CClassDAOImplTest {
    private CClassDAO cClassDAO = DAOFactory.getCClassDAOInstance();

    @Test
    public void selectByDepartmentId() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectByDepartmentId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cClassList.forEach(cClass -> System.out.println(cClass));
    }

    @Test
    public void insertCClass() {
        CClass cClass = new CClass();
        cClass.setClassName("测试班级");
        cClass.setDepartmentId(2);
        try {
            int n = cClassDAO.insertCClass(cClass);
            assertEquals(1,n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteCClassById() {
        int id = 19;
        try {
            cClassDAO.deleteCClassById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectAll() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cClassList.forEach(cClass -> System.out.println(cClass));
    }
}