package com.sm.dao.impl;

import com.sm.dao.StudentDAO;
import com.sm.entity.Department;
import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.utils.JDBCUtil;
import com.sun.org.apache.xerces.internal.xs.StringList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StudentDAOImpl implements StudentDAO {
    public List<StudentVO> selectAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t_student.*,t_class.class_name,t_department.department_name\n" +
                "FROM t_student\n" +
                "LEFT JOIN t_class\n" +
                "ON t_student.class_id = t_class.id\n" +
                "LEFT JOIN t_department\n" +
                "ON t_class.department_id = t_department.id";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> studentList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentList;
    }

    @Override
    public int deleteStudentById(int id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE  FROM t_student WHERE id = " + id;
        PreparedStatement pstmt = connection.prepareStatement(sql);
        int n = pstmt.executeUpdate();
        pstmt.close();
        jdbcUtil.closeConnection();
        return n;
    }

    @Override
    public List<StudentVO> selectByDepartmentId(int departmentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t_student.*,t_class.class_name,t_department.department_name\n" +
                "FROM t_student\n" +
                "LEFT JOIN t_class\n" +
                "ON t_student.class_id = t_class.id\n" +
                "LEFT JOIN t_department\n" +
                "ON t_class.department_id = t_department.id\n" +
                "WHERE t_department.id = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,departmentId);
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> studentList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentList;
    }

    @Override
    public List<StudentVO> selectByClassId(int classId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t_student.*,t_class.class_name,t_department.department_name\n" +
                "FROM t_student\n" +
                "LEFT JOIN t_class\n" +
                "ON t_student.class_id = t_class.id\n" +
                "LEFT JOIN t_department\n" +
                "ON t_class.department_id = t_department.id\n" +
                "WHERE t_student.class_id = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,classId);
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> studentList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentList;
    }

    @Override
    public List<StudentVO> selectByKeywords(String keywords) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t_student.*,t_class.class_name,t_department.department_name\n" +
                "FROM t_student\n" +
                "LEFT JOIN t_class\n" +
                "ON t_student.class_id = t_class.id\n" +
                "LEFT JOIN t_department\n" +
                "ON t_class.department_id = t_department.id\n" +
                "WHERE t_student.student_name LIKE ? OR t_student.address LIKE ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,"%" + keywords + "%");
        pstmt.setString(2,"%" + keywords + "%");
        ResultSet rs = pstmt.executeQuery();
        List<StudentVO> studentList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentList;
    }
    @Override
    public int updateStudent(Student student) throws SQLException {
        JDBCUtil jdbcUtil =JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "UPDATE t_student SET address = ? , phone = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,student.getAddress());
        pstmt.setString(2,student.getPhone());
        pstmt.setString(3,student.getId());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int deleteById(String id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_student WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,id);
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return  n;
    }

    private List<StudentVO> convert(ResultSet rs)throws SQLException{
        List<StudentVO> studentList = new ArrayList<>();
        while (rs.next()){
            StudentVO student = new StudentVO();
            student.setId(rs.getString("id"));
            student.setStudentName(rs.getString("student_name"));
            student.setGender(rs.getString("gender"));
            student.setAvatar(rs.getString("avatar"));
            student.setPhone(rs.getString("phone"));
            student.setAddress(rs.getString("address"));
            student.setBirthday(rs.getDate("birthday"));
            student.setDepartmentName(rs.getString("department_name"));
            student.setClassName(rs.getString("class_name"));
            studentList.add(student);
        }
        return studentList;
    }
}
