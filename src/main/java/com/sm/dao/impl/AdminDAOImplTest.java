package com.sm.dao.impl;

import com.sm.dao.AdminDAO;
import com.sm.entity.Admin;
import com.sm.factory.DAOFactory;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class AdminDAOImplTest {
    private AdminDAO adminDAO = DAOFactory.getAdminDAOInstance();
    @Test
    public void getAdminByAccount() {
        try {
            Admin admin = adminDAO.getAdminByAccount("aaa@qq.com");
            if (admin != null) {
                System.out.println(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

