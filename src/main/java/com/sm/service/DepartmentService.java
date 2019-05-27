package com.sm.service;

import com.sm.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> selectAll();

    /**
     * 根据id删除商品
     * @param id
     */
    void deleteDepartment(long id);

    /**
     * 新增院系
     * @param department
     * @return
     */
    int addDepartment(Department department);
}