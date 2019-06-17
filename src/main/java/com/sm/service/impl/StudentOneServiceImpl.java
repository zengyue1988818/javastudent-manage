package com.sm.service.impl;

import com.sm.dao.StudentOneDAO;
import com.sm.entity.StudentOneVO;
import com.sm.factory.DAOFactory;
import com.sm.service.StudentOneService;

import java.sql.SQLException;
import java.util.List;

public class StudentOneServiceImpl implements StudentOneService {
    private StudentOneDAO studentOneDAO = DAOFactory.getStudentOneDAOInstance();
    @Override
    public List<StudentOneVO> selectAllStu() {
        List<StudentOneVO> studentOneVOList = null;
        try {
            studentOneVOList = studentOneDAO.selectAllStudent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentOneVOList;
    }
}
