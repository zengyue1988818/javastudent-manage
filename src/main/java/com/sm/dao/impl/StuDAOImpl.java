package com.sm.dao.impl;

import com.sm.dao.StuDAO;
import com.sm.entity.Admin;
import com.sm.entity.Stu;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StuDAOImpl implements StuDAO {
    @Override
    public Stu getStuByAccount(String account) throws SQLException {
            JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
            Connection connection = jdbcUtil.getConnection();
            String sql = "SELECT * FROM t_stu WHERE account = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, account);
            ResultSet rs = pstmt.executeQuery();
            Stu stu = null;
            while (rs.next()) {
                int id = rs.getInt("id");
                String stuAccount = rs.getString("account");
                String password = rs.getString("password");
                String adminName = rs.getString("stu_name");
                stu = new Stu();
                stu.setId(id);
                stu.setAccount(stuAccount);
                stu.setPassword(password);
                stu.setAdminName(adminName);
            }
            rs.close();
            pstmt.close();
            jdbcUtil.closeConnection();
            return stu;
    }
}
