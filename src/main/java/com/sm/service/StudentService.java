package com.sm.service;

import com.sm.entity.Student;
import com.sm.entity.StudentVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentVO> selectAll();
    void deleteStudentById(int id);
    List<StudentVO> selectByDepartmentId(int departmentId);
    List<StudentVO> selectByClassId(int classId);
    List<StudentVO> selectByKeywords(String keywords);
    int updateStudent (Student student) throws SQLException;

    int deleteById(String id) throws SQLException;
    int insertStudent(Student student);
    int addStudent(Student student);
//    /**
//     * 获取所有院系的完整信息（包括每个学院的自身信息，班级数，学生数）
//     * @return List<Map>
//     */
//    List<Map> selectDepartmentInfo();

    /**
     *
     * @param classId
     * @return
     */
    int countStudentByClassId(int classId);
}