package com.sm.service;

import com.sm.entity.TeacherVO;

import java.util.List;

public interface TeacherService {
    List<TeacherVO> selectAllTeacher();
    void deleteTeacherById(int id);
    int inTea(TeacherVO teacherVO);
}
