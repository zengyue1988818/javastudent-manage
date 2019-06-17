package com.sm.dao.impl;

import com.sm.dao.StuDAO;
import com.sm.entity.Stu;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

public class StuDAOImplTest {
    private StuDAO stuDAO = DAOFactory.getStuDAOInstance();

    @Test
    public void getStuByAccount() {
        try {
            Stu stu = stuDAO.getStuByAccount("aaa@qq.com");
            if (stu != null) {
                System.out.println(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
