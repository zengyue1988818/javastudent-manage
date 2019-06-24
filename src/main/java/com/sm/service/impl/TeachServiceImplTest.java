package com.sm.service.impl;

import com.sm.entity.Teach;
import com.sm.entity.TeacherVO;
import com.sm.factory.ServiceFactory;
import com.sm.service.TeachService;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TeachServiceImplTest {
    private TeachService teachService = ServiceFactory.getTeachInsuance();
    @Test
    public void getAll(){
        List<Teach> teachList = teachService.selectAllTeach();
        teachList.forEach(teacherVO -> System.out.println(teacherVO));
    }
    @Test
    public void deleteTeachById(){
        int id = 1;
        teachService.deleteTeachById(id);
    }
    @Test
    public void updateTeach() throws SQLException {
        Teach teach = new Teach();
        teach.setId(1);
        teach.setTel("555555");
            int n = teachService.updateTeach(teach);
        assertEquals(0,n);
    }
}
