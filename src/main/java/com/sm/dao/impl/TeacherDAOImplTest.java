package com.sm.dao.impl;

import com.sm.dao.TeacherDAO;
import com.sm.entity.TeacherVO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TeacherDAOImplTest {
        private TeacherDAO teacherDAO = DAOFactory.getTeacherDAOInstance();
        @Test
    public void selectAll(){
            List<TeacherVO> teacherList = null;
            try {
                teacherList = teacherDAO.selectAllTeacher();
            }catch (SQLException e){
                e.printStackTrace();
            }
            teacherList.forEach(teacher -> System.out.println(teacher));
        }
        @Test
    public void delectTeacherById()throws SQLException{
            teacherDAO.delectTeacherById(4);
        }
        @Test
    public void insertTeacher() throws SQLException{
            TeacherVO teacherVO = new TeacherVO();
            teacherVO.setTeacherId("0005");
            teacherVO.setName("李老师");
            teacherVO.setGender("男");
            teacherVO.setTel("1502348245");
            teacherVO.setCourse("面向对象程序设计");
            teacherDAO.insertTeacher(teacherVO);
        }
}
