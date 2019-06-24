package com.sm.dao.impl;

import com.sm.dao.TeachDAO;
import com.sm.entity.Teach;
import com.sm.entity.TeacherVO;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeachDAOImpl implements TeachDAO {
    @Override
    public List<Teach> selectAllTeach() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_teach ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Teach> teachList = new ArrayList<>();
        while (rs.next()) {
            Teach teach = new Teach();
            teach.setId(rs.getInt("id"));
            teach.setTeacherId(rs.getString("teacher_id"));
            teach.setName(rs.getString("name"));
            teach.setGender(rs.getString("gender"));
            teach.setTel(rs.getString("tel"));
            teach.setCourse(rs.getString("course"));
            teachList.add(teach);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return teachList;
    }

    @Override
    public int updateTeach(Teach teach) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection =jdbcUtil.getConnection();
        String sql = "UPDATE t_teach SET tel = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,teach.getTel());
        pstmt.setInt(2,teach.getId());
        int n = pstmt.executeUpdate();
        pstmt.close();connection.close();
        return  n ;
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE REOM t_teach WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,id);
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return  n ;
    }
}
