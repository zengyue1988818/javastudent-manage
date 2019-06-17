package com.sm.dao.impl;

import com.sm.dao.TeaDAO;
import com.sm.entity.Tea;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

public class TeaDAOImplTest {
    private TeaDAO teaDAO = DAOFactory.getTeaDAOInstance();
    @Test
    public void getTeaByAccount() {
        try {
            Tea tea = teaDAO.getTeaByAccount("aaa@qq.com");
            if (tea != null) {
                System.out.println(tea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
