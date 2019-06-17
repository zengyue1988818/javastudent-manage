package com.sm.service.impl;

import com.sm.entity.StudentOneVO;
import com.sm.factory.ServiceFactory;
import com.sm.service.StudentOneService;
import org.junit.Test;

import java.util.List;

public class StudentOneServiceImplTest {
    private StudentOneService studentOneService = ServiceFactory.getStudentOneInstance();
    @Test
    public void selecctAllScore(){
        List<StudentOneVO> studentOneVOList = studentOneService.selectAllStu();
        studentOneVOList.forEach(studentOneVO -> System.out.println(studentOneVO));
    }
}
