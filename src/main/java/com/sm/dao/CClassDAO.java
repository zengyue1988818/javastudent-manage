package com.sm.dao;

import com.sm.entity.CClass;

import java.sql.SQLException;
import java.util.List;

public interface CClassDAO {
    /**
     * 按照院系id查询班级
     * @param departmentId
     * @return
     * @throws SQLException
     */
    List<CClass> selectByDepartmentId(int departmentId) throws SQLException;

    /**
     * 新增班级
     * @param cClass
     * @return
     * @throws SQLException
     */
    int insertCClass(CClass cClass)throws SQLException;
    int deleteCClassById(int id) throws SQLException;
    List<CClass> selectAll()throws SQLException;
}
