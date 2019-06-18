package com.sm.dao.impl;

import com.sm.dao.ScoreDAO;
import com.sm.entity.Score;
import com.sm.entity.ScoreVO;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAOImpl implements ScoreDAO {
    @Override
    public List<ScoreVO> selectAllScore() throws SQLException {
            JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
            Connection connection = jdbcUtil.getConnection();
            String sql = "SELECT t_score.*,t_class.class_name,t_department.department_name\n" +
                    "FROM t_score\n" +
                    "LEFT JOIN t_class\n" +
                    "ON t_score.class_id = t_class.id\n" +
                    "LEFT JOIN t_department\n" +
                    "ON t_class.department_id = t_department.id";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<ScoreVO> scoreList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return scoreList;
    }

    @Override
    public int deleteScoreById(int id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE  FROM t_score WHERE id = " + id;
        PreparedStatement pstmt = connection.prepareStatement(sql);
        int n = pstmt.executeUpdate();
        pstmt.close();
        jdbcUtil.closeConnection();
        return n;
    }

    @Override
    public List<ScoreVO> selectByKeywords(String keywords) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t_score.*,t_class.class_name,t_department.department_name\n" +
                "FROM t_score\n" +
                "LEFT JOIN t_class\n" +
                "ON t_score.class_id = t_class.id\n" +
                "LEFT JOIN t_department\n" +
                "ON t_class.department_id = t_department.id\n" +
                "WHERE t_score.student_name LIKE ? OR t_score.chinese LIKE ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,"%" + keywords + "%");
        pstmt.setString(2,"%" + keywords + "%");
        ResultSet rs = pstmt.executeQuery();
        List<ScoreVO> studentList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return studentList;
    }

    @Override
    public int updateScore(Score score) throws SQLException {
        JDBCUtil jdbcUtil =JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "UPDATE t_score SET chinese = ? , english = ?,math = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,score.getChinese());
        pstmt.setString(2,score.getEnglish());
        pstmt.setString(3,score.getMath());
        pstmt.setString(4,score.getId());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int deleteById(String id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_score WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,id);
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return  n;
    }

    @Override
    public int insertScore(Score score) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_score VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,score.getId());
        pstmt.setInt(2,score.getClassId());
        pstmt.setString(3,score.getStudentName());
        pstmt.setString(4,score.getAvatar());
        pstmt.setString(5,score.getGender());
        pstmt.setString(6,score.getChinese());
        pstmt.setString(7,score.getEnglish());
        pstmt.setString(8,score.getMath());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int countByDepartmentId(int departmentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT COUNT(*) FROM t_score t_score LEFT JOIN t_class t_class ON t_score.class_id = t_class.id\n"
                + "LEFT JOIN t_department t_department ON t_class.department_id = t_department.id\n"
                + "WHERE t_department.id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,departmentId);
        ResultSet rs = pstmt.executeQuery();
        int rowCount = 0;
        if (rs.next()){
            rowCount = rs.getInt(1);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rowCount;

    }

    @Override
    public int countByClassId(int classId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT COUNT(*) FROM t_score  WHERE class_id=? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, classId);
        ResultSet rs = pstmt.executeQuery();
        int rowCount = 0;
        if (rs.next()) {
            rowCount = rs.getInt(1);
        }
        return rowCount;
    }

    @Override
    public List<ScoreVO> selectByDepartmentId(int departmentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t_score.*,t_class.class_name,t_department.department_name\n" +
                "FROM t_score\n" +
                "LEFT JOIN t_class\n" +
                "ON t_score.class_id = t_class.id\n" +
                "LEFT JOIN t_department\n" +
                "ON t_class.department_id = t_department.id\n" +
                "WHERE t_department.id = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,departmentId);
        ResultSet rs = pstmt.executeQuery();
        List<ScoreVO> scoreList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return scoreList;
    }

    @Override
    public List<ScoreVO> selectByClassId(int classId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t_score.*,t_class.class_name,t_department.department_name\n" +
                "FROM t_score\n" +
                "LEFT JOIN t_class\n" +
                "ON t_score.class_id = t_class.id\n" +
                "LEFT JOIN t_department\n" +
                "ON t_class.department_id = t_department.id\n" +
                "WHERE t_student.class_id = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,classId);
        ResultSet rs = pstmt.executeQuery();
        List<ScoreVO> scoreList = convert(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return scoreList;
    }

    private List<ScoreVO> convert(ResultSet rs)throws SQLException{
        List<ScoreVO> scoreList = new ArrayList<>();
        while (rs.next()){
            ScoreVO  score = new ScoreVO();
            score.setId(rs.getString("id"));
            score.setStudentName(rs.getString("student_name"));
            score.setClassName(rs.getString("class_id"));
            score.setGender(rs.getString("gender"));
            score.setAvatar(rs.getString("avatar"));
            score.setChinese(rs.getString("chinese"));
            score.setEnglish(rs.getString("english"));
            score.setMath(rs.getString("math"));
            score.setDepartmentName(rs.getString("department_name"));
            scoreList.add(score);
        }
        return scoreList;
    }
}
