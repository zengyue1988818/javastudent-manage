package com.sm.dao.impl;

import com.sm.dao.StudentOneDAO;
import com.sm.entity.ScoreVO;
import com.sm.entity.StudentOne;
import com.sm.entity.StudentOneVO;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentOneDAOImpl implements StudentOneDAO {
    @Override
    public List<StudentOneVO> selectAllStudent() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t_one.*,t_class.class_name,t_department.department_name\n" +
                "FROM t_one\n" +
                "LEFT JOIN t_class\n" +
                "ON t_one.class_id = t_class.id\n" +
                "LEFT JOIN t_department\n" +
                "ON t_class.department_id = t_department.id";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<StudentOneVO> studentOneVOList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentOneVOList;
    }
    private List<StudentOneVO> convert(ResultSet rs)throws SQLException{
        List<StudentOneVO> studentOneVOList = new ArrayList<>();
        while (rs.next()){
            StudentOneVO  studentOne = new StudentOneVO();
            studentOne.setId(rs.getString("id"));
            studentOne.setStudentName(rs.getString("student_name"));
            studentOne.setClassName(rs.getString("class_name"));
            studentOne.setGender(rs.getString("gender"));
            studentOne.setAvatar(rs.getString("avatar"));
            studentOne.setChinese(rs.getString("chinese"));
            studentOne.setEnglish(rs.getString("english"));
            studentOne.setMath(rs.getString("math"));
            studentOne.setDepartmentName(rs.getString("department_name"));
            studentOneVOList.add(studentOne);
        }
        return studentOneVOList;
    }
}
