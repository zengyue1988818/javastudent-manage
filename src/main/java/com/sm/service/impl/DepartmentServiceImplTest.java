package com.sm.service.impl;

import com.sm.entity.Department;
import com.sm.factory.ServiceFactory;
import com.sm.service.DepartmentService;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DepartmentServiceImplTest {

    private DepartmentService departmentService =
            ServiceFactory.getDepartmentServiceInstance();

    @Test
    public void selectAll() {
        List<Department> departmentList = departmentService.selectAll();
        departmentList.forEach(department -> System.out.println(department));
    }
    @Test
    public void deleteDepartmentById() throws SQLException {
        departmentService.deleteDepartment(35);
    }
    @Test
    public void addDepartment(){
        Department department = new Department();
        department.setDepartmentName("测试院系2");
        department.setLogo("https://zystudent-manage.oss-cn-beijing.aliyuncs.com/img5f785718-0d40-430c-96b5-1c69411d7f2d.jpg");
        int n = departmentService.addDepartment(department);
    }
    @Test
    public void selectDepartmentInfo(){
        List<Map> mapList = departmentService.selectDepartmentInfo();
        mapList.forEach(map -> {
            System.out.println(map.get("department") + ","
                    + map.get("classCount") + "个班，" + map.get("studentCount" + "个学生"));
        });
    }
}