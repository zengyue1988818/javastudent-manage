package com.sm.factory;

import com.sm.service.AdminService;
import com.sm.service.DepartmentService;
import com.sm.service.StuService;
import com.sm.service.impl.*;

public class ServiceFactory {
    public static AdminService getAdminServiceInstance() {
        return new AdminServiceImpl();
    }
    public static DepartmentService getDepartmentServiceInstance() {
        return new DepartmentServiceImpl();
    }
    public static CClassServiceImpl getCClassServiceInstance(){
        return new CClassServiceImpl();
    }
    public static StudentServiceImpl getStudentServiceInstance(){
        return new StudentServiceImpl();
    }
    public static TeacherServiceImpl getTeacherServiceInstance(){
        return new TeacherServiceImpl();
    }
    public static ScoreServiceImpl getScoreServiceInstance(){
        return new ScoreServiceImpl();
    }
    public static StudentOneServiceImpl getStudentOneInstance(){
        return new StudentOneServiceImpl();
    }
    public static StuServiceImpl getStuInstance(){
        return new StuServiceImpl();
    }
    public static TeaServiceImpl getTeaInstance(){
        return new TeaServiceImpl();
    }
}
