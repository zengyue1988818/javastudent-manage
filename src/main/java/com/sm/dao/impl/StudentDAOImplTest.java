package com.sm.dao.impl;

import com.sm.dao.StudentDAO;
import com.sm.entity.PunishVO;
import com.sm.entity.RewardVO;
import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDAOImplTest {
    private StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();
    @Test
    public void selectAll() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void deleteStudentById() {
        int id = 1802361520;
        try {
            studentDAO.deleteStudentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectByDepartmentId() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByDepartmentId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByClassId() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByClassId(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByKeywords() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByKeywords("河");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }
    @Test
    public void updateStudent() throws SQLException{
        Student student = new Student();
        student.setId("1802343305");
        student.setAddress("湖北武汉");
        student.setPhone("17826022");
        int n = studentDAO.updateStudent(student);
        assertEquals(1,n);
    }
    @Test
    public void deleteById() throws SQLException{
        int n = studentDAO.deleteById("1802343305");
        assertEquals(1,n);
    }
    @Test
    public void insertStudent() {
        Student student = new Student();
        student.setId("1802332314");
        student.setClassId(6);
        student.setStudentName("王嘉尔");
        student.setAvatar("2.png");
        student.setBirthday(new Date());
        student.setGender("男");
        student.setAddress("江苏省南京市");
        student.setPhone("18213456879");
        try {
            int n = studentDAO.insertStudent(student);
            assertEquals(1,n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //奖励和惩罚
    @Test
    public void selectAllReward() {
        List<RewardVO> rewardVOList = null;
        try {
            rewardVOList = studentDAO.selectAllReward();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        rewardVOList.forEach(rewardVO -> System.out.println(rewardVO));
    }

    @Test
    public void selectByStuId() {
        List<RewardVO> rewardVOList = null;
        try {
            rewardVOList = studentDAO.selectByStuId("1802343390");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rewardVOList.forEach(rewardVO -> System.out.println(rewardVO));
    }
    @Test
    public void selectRedByKeywords() {
        List<RewardVO> rewardVOList = null;
        try {
            rewardVOList = studentDAO.selectRewByKeywords("三好学生");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rewardVOList.forEach(rewardVO -> System.out.println(rewardVO));
    }
    @Test
    public void deleteRewById() {
        try {
            studentDAO.deleteRewById(15);
        } catch (SQLException e) {
        }
    }
    @Test
    public void updateRew() throws SQLException {
        RewardVO rewardVO = new RewardVO();
        rewardVO.setReward("三好学生");
        rewardVO.setId(14);
        studentDAO.updateRew(rewardVO);
    }
    @Test
    public void insertRew() throws SQLException{
        RewardVO rewardVO = new RewardVO();
        rewardVO.setStudentId("1802343310");
        rewardVO.setReward("优秀学生");
        rewardVO.setRewardDate(new Date());
        studentDAO.insertRew(rewardVO);
    }
    @Test
    public void selectAllPunish() {
        List<PunishVO> punishVOList = null;
        try {
            punishVOList = studentDAO.selectAllPunish();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        punishVOList.forEach(punishVO -> System.out.println(punishVO));
    }

    @Test
    public void selectPunByKeywords() {
        List<PunishVO> punishVOList = null;
        try {
            punishVOList = studentDAO.selectPunByKeywords("机械");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        punishVOList.forEach(punishVO -> System.out.println(punishVO));
    }

    @Test
    public void deletePunById() {
        try {
            studentDAO.deletePunById(16);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePun() throws SQLException {
        PunishVO punishVO = new PunishVO();
        punishVO.setPunish("玩手机");
        punishVO.setId(15);
        studentDAO.updatePun(punishVO);
    }

    @Test
    public void insertPun() throws SQLException{
        PunishVO punishVO = new PunishVO();
        punishVO.setStudentId("1802343301");
        punishVO.setPunish("玩手机");
        punishVO.setPunishDate(new Date());
        studentDAO.insertPun(punishVO);
    }
}
