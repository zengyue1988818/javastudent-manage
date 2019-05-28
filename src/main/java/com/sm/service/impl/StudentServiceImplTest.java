package com.sm.service.impl;

import com.sm.dao.StudentDAO;
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

}