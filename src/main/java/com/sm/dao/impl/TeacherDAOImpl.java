package com.sm.dao.impl;

import com.sm.dao.TeacherDAO;
import com.sm.entity.TeacherVO;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public List<TeacherVO> selectAllTeacher() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_teacher ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<TeacherVO> teacherVOList = new ArrayList<>();
        while (rs.next()) {
            TeacherVO teacherVO = new TeacherVO();
            teacherVO.setId(rs.getInt("id"));
            teacherVO.setTeacherId(rs.getString("teacher_id"));
            teacherVO.setName(rs.getString("name"));
            teacherVO.setGender(rs.getString("gender"));
            teacherVO.setTel(rs.getString("tel"));
            teacherVO.setCourse(rs.getString("course"));
            teacherVOList.add(teacherVO);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return teacherVOList;
    }

    @Override
    public void delectTeacherById(long id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_teacher WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,4);
        int n = pstmt.executeUpdate();
        System.out.println(n + "行记录受影响");
        pstmt.close();
        jdbcUtil.closeConnection();
    }

    @Override
    public int insertTeacher(TeacherVO teacherVO) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql ="INSERT INTO t_teacher (teacher_id,name,gender,tel,course) VALUES (?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,teacherVO.getTeacherId());
        pstmt.setString(2,teacherVO.getName());
        pstmt.setString(3,teacherVO.getGender());
        pstmt.setString(4,teacherVO.getTel());
        pstmt.setString(5,teacherVO.getCourse());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }


    /**
     * 封装
     * @param rs
     * @return
     * @throws SQLException
     */
    private List<TeacherVO> convertTeacher(ResultSet rs) throws SQLException{
        List<TeacherVO> teacherList = new ArrayList<>();
        while (rs.next()){
            TeacherVO teacherVO = new TeacherVO();
            teacherVO.setId(rs.getInt("id"));
            teacherVO.setTeacherId(rs.getString("teacher_id"));
            teacherVO.setName(rs.getString("name"));
            teacherVO.setGender(rs.getString("gender"));
            teacherVO.setTel(rs.getString("tel"));
            teacherVO.setCourse(rs.getString("course"));
        }
        return teacherList;
    }
}
