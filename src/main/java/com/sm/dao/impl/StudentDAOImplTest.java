package com.sm.dao.impl;

import com.sm.dao.StudentDAO;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDAOImplTest {
    private StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();
    @Test
    public void selectAll() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void deleteStudentById() {
        int id = 1802361520;
        try {
            studentDAO.deleteStudentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectByDepartmentId() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByDepartmentId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByClassId() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByClassId(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByKeywords() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByKeywords("河");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }
}