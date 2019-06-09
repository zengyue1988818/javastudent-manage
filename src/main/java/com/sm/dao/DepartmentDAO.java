package com.sm.dao;

import com.sm.entity.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {
    /**
     * 查询所有院系
     * @return
     * @throws SQLException
     */
    List<Department> getAll() throws SQLException;

    /**
     * 根据id删除商品
     * @param id
     * @return
     * @throws SQLException
     */
    void deleteDepartmentById(long id) throws SQLException;

    /**
     * 新增院系
     * @param department
     * @return
     * @throws SQLException
     */
    int insertDepartment(Department department) throws SQLException;



}
