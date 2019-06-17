package com.sm.service.impl;

import com.sm.factory.ServiceFactory;
import com.sm.service.AdminService;
import com.sm.service.StuService;
import com.sm.utils.ResultEntity;
import org.testng.annotations.Test;

public class StuServiceImplTest {
    private StuService stuService = ServiceFactory.getStuInstance();
    @Test
    public void stuLogin() {
        ResultEntity resultEntity = stuService.stuLogin("aaa@qq.com", "aaa");
        System.out.println(resultEntity);
    }
}
