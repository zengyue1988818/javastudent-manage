package com.sm.service;

import com.sm.entity.StudentVO;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    List<StudentVO> selectAll();
    void deleteStudentById(int id);
    List<StudentVO> selectByDepartmentId(int departmentId);
    List<StudentVO> selectByClassId(int classId);
    List<StudentVO> selectByKeywords(String keywords);
}
