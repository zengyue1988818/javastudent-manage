package com.sm.dao.impl;

import com.sm.dao.StudentOneDAO;
import com.sm.entity.ScoreVO;
import com.sm.entity.StudentOneVO;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class StudentOneDAOImplTest {
    private StudentOneDAO studentOneDAO = DAOFactory.getStudentOneDAOInstance();
    @Test
    public void selectAllStudent() {
        List<StudentOneVO> studentVOList = null;
        try {
            studentVOList = studentOneDAO.selectAllStudent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }
}
