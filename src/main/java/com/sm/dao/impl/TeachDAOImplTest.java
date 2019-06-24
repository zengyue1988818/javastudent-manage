package com.sm.dao.impl;

import com.sm.dao.TeachDAO;
import com.sm.entity.Teach;
import com.sm.entity.TeacherVO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TeachDAOImplTest {
    private TeachDAO teachDAO = DAOFactory.getTeachDAOInstance();
    @Test
    public void selectAllTeach(){
        List<Teach> teachList = null;
        try {
            teachList = teachDAO.selectAllTeach();
        }catch (SQLException e){
            e.printStackTrace();
        }
        teachList.forEach(teacher -> System.out.println(teacher));
    }
    @Test
    public void updateTeach() throws SQLException{
        Teach teach = new Teach();
        teach.setId(1);
        teach.setTel("2000000");
        int n = teachDAO.updateTeach(teach);
        assertEquals(1,n);
    }
    @Test
    public void deleteById(){
        int id = 2;
        try {
            teachDAO.deleteById(id);
        } catch (SQLException e) {

        }
    }
}
