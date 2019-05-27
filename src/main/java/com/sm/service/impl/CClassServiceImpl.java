package com.sm.service.impl;

import com.sm.dao.CClassDAO;
import com.sm.entity.CClass;
import com.sm.entity.Department;
import com.sm.factory.DAOFactory;
import com.sm.service.CClassService;

import javax.xml.ws.Service;
import java.sql.SQLException;
import java.util.List;

public class CClassServiceImpl implements CClassService {
    private CClassDAO cClassDAO = DAOFactory.getCClassDAOInstance();
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
}