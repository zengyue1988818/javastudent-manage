package com.sm.service.impl;

import com.sm.entity.TeacherVO;
import com.sm.factory.ServiceFactory;
import com.sm.service.TeacherService;
import org.junit.Test;

import java.util.List;

public class TeacherServiceImplTest {
    private TeacherService teacherService = ServiceFactory.getTeacherServiceInstance();
    @Test
    public void getAll(){
        List<TeacherVO> teacherVOList = teacherService.selectAllTeacher();
        teacherVOList.forEach(teacherVO -> System.out.println(teacherVO));
    }
    @Test
    public void deleteTeacherById(){
        int id = 4;
        teacherService.deleteTeacherById(id);
    }
    @Test
    public void inTea(){
        TeacherVO teacherVO = new TeacherVO();
        teacherVO.setTeacherId("0005");
        teacherVO.setName("李老师");
        teacherVO.setGender("男");
        teacherVO.setTel("1502348245");
        teacherVO.setCourse("体育");
        teacherService.inTea(teacherVO);
    }
}
