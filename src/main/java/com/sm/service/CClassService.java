package com.sm.service;

import com.sm.entity.CClass;

import java.sql.SQLException;
import java.util.List;

public interface CClassService {
    List<CClass> selectByDepartmentId(int departmentId) throws SQLException;
    int addCClass(CClass cClass);
    void deleteCClassById(int id);
    List<CClass> selectAll();
}
