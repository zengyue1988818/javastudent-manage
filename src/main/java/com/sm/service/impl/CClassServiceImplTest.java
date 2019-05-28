package com.sm.service.impl;

import com.sm.dao.CClassDAO;
import com.sm.entity.CClass;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFactory;
import com.sm.service.CClassService;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CClassServiceImplTest {
    private CClassService cClassService = ServiceFactory.getCClassServiceInstance();
    @Test
    public void selectByDepartmentId() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassService.selectByDepartmentId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cClassList.forEach(cClass -> System.out.println(cClass));
    }

    @Test
    public void addCClass() {
        CClass cClass = new CClass();
        cClass.setClassName("测试班级2");
        cClass.setDepartmentId(4);
        int n = cClassService.addCClass(cClass);
    }

    @Test
    public void deleteCClassById() {
        int id =20;
        cClassService.deleteCClassById(id);
    }

    @Test
    public void selectAll() {
        List<CClass> cClassList = cClassService.selectAll();
        cClassList.forEach(cClass -> System.out.println(cClass));
    }
    @Test
    public void selectDepartmentInfo(){
        List<Map> mapList = cClassService.selectClassInfo();
        mapList.forEach(map -> {
            System.out.println(map.get("department" + "," + map.get("classCount") + "个班，"
            + map.get("studentCount") + "个学生"));
        });
    }
}
