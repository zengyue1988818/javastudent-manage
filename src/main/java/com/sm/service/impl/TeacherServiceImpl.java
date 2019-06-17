package com.sm.service.impl;

import com.sm.dao.TeacherDAO;
import com.sm.entity.TeacherVO;
import com.sm.factory.DAOFactory;
import com.sm.service.TeacherService;

import java.sql.SQLException;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private TeacherDAO teacherDAO = DAOFactory.getTeacherDAOInstance();


    @Override
    public List<TeacherVO> selectAllTeacher() {
        List<TeacherVO> teacherVOList = null;
        try {
            teacherVOList = teacherDAO.selectAllTeacher();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherVOList;
    }

    @Override
    public void deleteTeacherById(int id) {
        try {
            teacherDAO.delectTeacherById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int inTea(TeacherVO teacherVO) {
        int n = 0;
        try {
            n = teacherDAO.insertTeacher(teacherVO);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return n ;
    }
}
