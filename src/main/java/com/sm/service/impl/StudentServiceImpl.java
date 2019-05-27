package com.sm.service.impl;

import com.sm.dao.StudentDAO;
import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import com.sm.service.StudentService;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();
    @Override
    public List<StudentVO> selectAll() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentVOList;
    }

    @Override
    public void deleteStudentById(int id) {
        try {
            studentDAO.deleteStudentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StudentVO> selectByDepartmentId(int departmentId){
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByDepartmentId(departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentVOList;
    }

    @Override
    public List<StudentVO> selectByClassId(int classId) {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByClassId(classId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentVOList;
    }

    @Override
    public List<StudentVO> selectByKeywords(String keywords) {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentVOList;
    }

    @Override
    public int updateStudent(Student student) throws SQLException {
        int n=0;
        n=studentDAO.updateStudent(student);
        return n;
    }

    @Override
    public int deleteById(String id) throws SQLException {
        int n = 0;
        try {
            n = studentDAO.deleteById(id);
        } catch (SQLException e) {
            System.err.print("删除学生id出现异常");
        }
        return n;
    }
}
