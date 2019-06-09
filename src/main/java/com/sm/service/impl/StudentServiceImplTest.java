package com.sm.service.impl;

import com.sm.dao.StudentDAO;
import com.sm.entity.PunishVO;
import com.sm.entity.RewardVO;
import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.factory.ServiceFactory;
import com.sm.service.StudentService;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceImplTest {
    private StudentService studentService = ServiceFactory.getStudentServiceInstance();
    @Test
    public void selectAll() {
        List<StudentVO> studentVOList = studentService.selectAll();
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void deleteStudentById() {
        int id = 1802387514;
        studentService.deleteStudentById(id);
    }

    @Test
    public void selectByDepartmentId() {
        List<StudentVO> studentVOList = null;
        studentVOList = studentService.selectByDepartmentId(1);
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByClassId() {
        List<StudentVO> studentVOList = null;
        studentVOList = studentService.selectByClassId(4);
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByKeywords() {
        List<StudentVO> studentVOList = null;
        studentVOList = studentService.selectByKeywords("河");
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }
   @Test
    public void updateStudent() throws SQLException{
        Student student = new Student();
        student.setId("1802343323");
        student.setAddress("江苏淮安");
        student.setPhone("1782605544");
        int n = studentService.updateStudent(student);
        assertEquals(1,n);
   }
    @Test
    public void deleteById() throws SQLException{
        int n = studentService.deleteById("1802343315");
        assertEquals(1,n);
    }
    @Test
    public void addStudent() throws SQLException{
        Student student = new Student();
        student.setId("1802340");
        student.setClassId(1);
        student.setStudentName("陈伟霆");
        student.setAvatar("2.jpg");
        student.setBirthday(new Date());
        student.setGender("男");
        student.setAddress("江苏南京");
        student.setPhone("13782561");
        int n = studentService.addStudent(student);
        System.out.println(n);
    }
    //奖励和处罚
    @Test
    public void selectAllReward() {
        List<RewardVO> rewardVOList = studentService.selectAllReward();
        rewardVOList.forEach(rewardVO -> System.out.println(rewardVO));
    }

    @Test
    public void selectByStuId() {
        List<RewardVO> rewardVOList = studentService.selectByStuId("1802343310");
        rewardVOList.forEach(rewardVO -> System.out.println(rewardVO));
    }

    @Test
    public void selectRewByKeywords() {
        List<RewardVO> rewardVOList = studentService.selectRewByKeywords("赵");
        rewardVOList.forEach(rewardVO -> System.out.println(rewardVO));
    }

    @Test
    public void delRewById() {
        studentService.delRewById(16);
    }

    @Test
    public void upRew() {
        RewardVO rewardVO = new RewardVO();
        rewardVO.setId(14);
        rewardVO.setReward("国家奖学金");
        studentService.upRew(rewardVO);
    }

    @Test
    public void inRew() {
        RewardVO rewardVO = new RewardVO();
        rewardVO.setStudentId("21");
        rewardVO.setReward("三好学生");
        rewardVO.setRewardDate(new Date());
        studentService.inRew(rewardVO);
    }

    @Test
    public void selectAllPunish() {
        List<PunishVO> punishVOList = studentService.selectAllPunish();
        punishVOList.forEach(punishVO -> System.out.println(punishVO));
    }

    @Test
    public void selectPunByKeywords() {
        List<PunishVO> punishVOList = studentService.selectPunByKeywords("陈");
        punishVOList.forEach(punishVO -> System.out.println(punishVO));
    }

    @Test
    public void delPunById() {
        studentService.delPunById(17);
    }

    @Test
    public void upPun() {
        PunishVO punishVO = new PunishVO();
        punishVO.setId(15);
        punishVO.setPunish("处分");
        studentService.upPun(punishVO);
    }

    @Test
    public void inPun() {
        PunishVO punishVO = new PunishVO();
        punishVO.setStudentId("1802343301");
        punishVO.setPunish("处分");
        punishVO.setPunishDate(new Date());
        studentService.inPun(punishVO);
    }


}