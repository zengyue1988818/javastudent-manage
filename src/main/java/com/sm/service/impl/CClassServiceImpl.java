package com.sm.service.impl;

import com.sm.dao.CClassDAO;
import com.sm.dao.DepartmentDAO;
import com.sm.dao.StudentDAO;
import com.sm.entity.CClass;
import com.sm.entity.Department;
import com.sm.factory.DAOFactory;
import com.sm.service.CClassService;

import javax.xml.ws.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CClassServiceImpl implements CClassService {
    private CClassDAO cClassDAO = DAOFactory.getCClassDAOInstance();
    private DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAOInstance();
    private StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();
    @Override
    public List<CClass> selectByDepartmentId(int departmentId) {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectByDepartmentId(departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cClassList;
    }

    @Override
    public int addCClass(CClass cClass) {
        int n = 0;
        try {
            n = cClassDAO.insertCClass(cClass);
        } catch (SQLException e) {
            System.out.println("新增班级信息出现异常");
        }
        return 0;
    }

    @Override
    public void deleteCClassById(int id) {
        try {
            cClassDAO.deleteCClassById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CClass> selectAll() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cClassList;
    }

    @Override
    public List<Map> selectClassInfo() {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDAO.getAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        List<Map> mapList = new ArrayList<>();
        for (Department department : departmentList){
            Map<String,Object> map = new HashMap<>();
            map.put("department",department);
            try {
                map.put("classCount",cClassDAO.countByDepartmentId(department.getId()));
            }catch (SQLException e){
                e.printStackTrace();
            }
            try {
                map.put("studentCount",studentDAO.countByDepartmentId(department.getId()));
            }catch (SQLException e){
                e.printStackTrace();
            }
            mapList.add(map);
        }
        return mapList;
}
}
