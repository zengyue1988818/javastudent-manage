package com.sm.dao;

import com.sm.entity.TeacherVO;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDAO {
    /**
     * 查询所有老师（视图对象）
     * @return
     * @throws SQLException
     */
     List<TeacherVO> selectAllTeacher() throws SQLException;

    /**
     * 根据id删除老师
     * @param id
     * @throws SQLException
     */
    void delectTeacherById(long id) throws SQLException;

    /**
     * 新增老师
     * @param teacherVO
     * @return
     * @throws SQLException
     */
    int insertTeacher(TeacherVO teacherVO) throws SQLException;

}
