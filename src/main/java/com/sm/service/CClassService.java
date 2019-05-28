package com.sm.service;

import com.sm.entity.CClass;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CClassService {
    List<CClass> selectByDepartmentId(int departmentId) throws SQLException;
    int addCClass(CClass cClass);
    void deleteCClassById(int id);
    List<CClass> selectAll();

    /**
     * 获取所有院系的完整信息（包括每个学院的自身信息，班级数，学生数）
     * @return
     */
    List<Map> selectClassInfo();
}
