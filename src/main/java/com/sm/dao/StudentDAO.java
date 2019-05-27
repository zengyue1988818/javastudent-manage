package com.sm.dao;

import com.sm.entity.Department;
import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.factory.ServiceFactory;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    /**
     * 查询所有学生（视图对象）
     * @return List<StudentVO>
     * @throws SQLException
     */
    List<StudentVO> selectAll() throws SQLException;

    /**
     * 根据id删除学生
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteStudentById(int id) throws SQLException;

    /**
     * 根据院系id查询学生
     * @param departmentId
     * @return
     * @throws SQLException
     */
    List<StudentVO> selectByDepartmentId(int departmentId) throws SQLException;

    /**
     * 根据班级id查询学生
     * @param classId
     * @return
     * @throws SQLException
     */
    List<StudentVO> selectByClassId(int classId) throws SQLException;

    /**
     * 根据关键词查询学生
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<StudentVO> selectByKeywords(String keywords)throws SQLException;
}
