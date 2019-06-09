package com.sm.dao.impl;

import com.sm.dao.StudentDAO;
import com.sm.entity.*;
import com.sm.utils.JDBCUtil;
import com.sun.org.apache.xerces.internal.xs.StringList;

import java.sql.*;
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

    @Override
    public int insertStudent(Student student) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_student VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,student.getId());
        pstmt.setInt(2,student.getClassId());
        pstmt.setString(3,student.getStudentName());
        pstmt.setString(4,student.getAvatar());
        pstmt.setString(5,student.getGender());
        pstmt.setDate(6,new Date(student.getBirthday().getTime()));
        pstmt.setString(7,student.getAddress());
        pstmt.setString(8,student.getPhone());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int countByDepartmentId(int departmentId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT COUNT(*) FROM t_student t_student LEFT JOIN t_class t_class ON t_student.class_id = t_class.id\n"
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
        String sql = "SELECT COUNT(*) FROM t_student  WHERE class_id=? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, classId);
        ResultSet rs = pstmt.executeQuery();
        int rowCount = 0;
        if (rs.next()) {
            rowCount = rs.getInt(1);
        }
        return rowCount;
    }
   //奖励和惩罚
    @Override
    public List<RewardVO> selectAllReward() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.student_name,logo,t2.class_name,t3.department_name,t4.*\n" +
                "FROM t_reward t4\n" +
                "LEFT JOIN t_student t1\n" +
                "ON t4.`student_id` = t1.`id`\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.`class_id` = t2.`id`\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.`department_id` = t3.`id`";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<RewardVO> rewardVOList = convertReword(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rewardVOList;
    }

    @Override
    public List<RewardVO> selectByStuId(String id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.student_name,logo,t2.class_name,t3.department_name,t4.*\n" +
                "FROM t_reward t4\n" +
                "LEFT JOIN t_student t1\n" +
                "ON t4.`student_id` = t1.`id`\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.`class_id` = t2.`id`\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.`department_id` = t3.`id`"+
                "WHERE t4.student_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery();
        List<RewardVO> rewardVOList = convertReword(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rewardVOList;
    }

    @Override
    public List<RewardVO> selectRewByKeywords(String keywords) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.student_name,logo,t2.class_name,t3.department_name,t4.*\n" +
                "FROM t_reward t4\n" +
                "LEFT JOIN t_student t1\n" +
                "ON t4.`student_id` = t1.`id`\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.`class_id` = t2.`id`\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.`department_id` = t3.`id`" +
                "WHERE t1.student_name LIKE ? OR t2.class_name LIKE ? OR t3.department_name LIKE ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,"%" + keywords + "%");
        pstmt.setString(2,"%" + keywords + "%");
        pstmt.setString(3,"%" + keywords + "%");
        ResultSet rs = pstmt.executeQuery();
        List<RewardVO> rewardVOList = convertReword(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rewardVOList;
    }

    @Override
    public List<PunishVO> selectAllPunish() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.student_name,logo,t2.class_name,t3.department_name,t4.*\n" +
                "FROM t_punish t4\n" +
                "LEFT JOIN t_student t1\n" +
                "ON t4.`student_id` = t1.`id`\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.`class_id` = t2.`id`\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.`department_id` = t3.`id`";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<PunishVO> punishVOList = convertPunish(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return punishVOList;
    }

    @Override
    public List<PunishVO> selectPunByKeywords(String keywords) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.student_name,logo,t2.class_name,t3.department_name,t4.*\n" +
                "FROM t_punish t4\n" +
                "LEFT JOIN t_student t1\n" +
                "ON t4.`student_id` = t1.`id`\n" +
                "LEFT JOIN t_class t2\n" +
                "ON t1.`class_id` = t2.`id`\n" +
                "LEFT JOIN t_department t3\n" +
                "ON t2.`department_id` = t3.`id`" +
                "WHERE t1.student_name LIKE ? OR t2.class_name LIKE ? OR t3.department_name LIKE ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,"%" + keywords + "%");
        pstmt.setString(2,"%" + keywords + "%");
        pstmt.setString(3,"%" + keywords + "%");
        ResultSet rs = pstmt.executeQuery();
        List<PunishVO> punishVOList = convertPunish(rs);
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return punishVOList;
    }

    @Override
    public int deleteRewById(int rewId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_reward  WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,rewId);
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int updateRew(RewardVO rewardVO) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "UPDATE t_reward SET reward = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,rewardVO.getReward());
        pstmt.setInt(2,rewardVO.getId());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int insertRew(RewardVO rewardVO) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_reward (student_id,reward,redate) VALUES (?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,rewardVO.getStudentId());
        pstmt.setString(2,rewardVO.getReward());
        pstmt.setDate(3,new Date(rewardVO.getRewardDate().getTime()));
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int deletePunById(int punId) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_punish  WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,punId);
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int updatePun(PunishVO punishVO) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "UPDATE t_punish SET punish = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,punishVO.getPunish());
        pstmt.setInt(2,punishVO.getId());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }

    @Override
    public int insertPun(PunishVO punishVO) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_punish (student_id,punish,pudate) VALUES (?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,punishVO.getStudentId());
        pstmt.setString(2,punishVO.getPunish());
        pstmt.setDate(3,new Date(punishVO.getPunishDate().getTime()));
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
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
    private List<RewardVO> convertReword(ResultSet rs) throws SQLException{
        List<RewardVO> rewardVOList = new ArrayList<>();
        while (rs.next()){
            RewardVO rewardVO = new RewardVO();
//            rewardVO.setAvatar(rs.getString("avatar"));
            rewardVO.setId(rs.getInt("id"));
            rewardVO.setStudentName(rs.getString("student_name"));
            rewardVO.setClassName(rs.getString("class_name"));
            rewardVO.setDepartmentName(rs.getString("department_name"));
            rewardVO.setReward(rs.getString("reward"));
            rewardVO.setStudentId(rs.getString("student_id"));
            rewardVO.setRewardDate(rs.getDate("redate"));
            rewardVOList.add(rewardVO);
        }
        return rewardVOList;
    }

    private List<PunishVO> convertPunish(ResultSet rs) throws SQLException{
        List<PunishVO> punishVOList  = new ArrayList<>();
        while (rs.next()){
            PunishVO punishVO = new PunishVO();
//            punishVO.setAvatar(rs.getString("avatar"));
            punishVO.setId(rs.getInt("id"));
            punishVO.setStudentName(rs.getString("student_name"));
            punishVO.setClassName(rs.getString("class_name"));
            punishVO.setDepartmentName(rs.getString("department_name"));
            punishVO.setPunish(rs.getString("punish"));
            punishVO.setStudentId(rs.getString("student_id"));
            punishVO.setPunishDate(rs.getDate("pudate"));
            punishVOList.add(punishVO);
        }
        return punishVOList;
    }
}