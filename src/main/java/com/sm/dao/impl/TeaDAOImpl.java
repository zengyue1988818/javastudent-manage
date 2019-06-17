package com.sm.dao.impl;

import com.sm.dao.TeaDAO;
import com.sm.entity.Admin;
import com.sm.entity.Tea;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeaDAOImpl implements TeaDAO {
    @Override
    public Tea getTeaByAccount(String account) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_tea WHERE account = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();
        Tea tea = null;
        while (rs.next()) {
            int id = rs.getInt("id");
            String teaAccount = rs.getString("account");
            String password = rs.getString("password");
            String adminName = rs.getString("tea_name");
            tea = new Tea();
            tea.setId(id);
            tea.setAccount(teaAccount);
            tea.setPassword(password);
            tea.setTeaName(adminName);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return tea;
    }
}
