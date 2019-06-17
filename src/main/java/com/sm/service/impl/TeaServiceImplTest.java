package com.sm.service.impl;

import com.sm.factory.ServiceFactory;
import com.sm.service.StuService;
import com.sm.service.TeaService;
import com.sm.utils.ResultEntity;
import org.testng.annotations.Test;

public class TeaServiceImplTest {
    private StuService stuService = ServiceFactory.getStuInstance();
    private TeaService teaService = ServiceFactory.getTeaInstance();
    @Test
    public void teaLogin() {
        ResultEntity resultEntity = teaService.teaLogin("aaa@qq.com", "aaa");
        System.out.println(resultEntity);
    }
}
