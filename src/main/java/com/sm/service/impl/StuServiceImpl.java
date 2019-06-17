package com.sm.service.impl;

import com.sm.dao.StuDAO;
import com.sm.entity.Admin;
import com.sm.entity.Stu;
import com.sm.factory.DAOFactory;
import com.sm.service.StuService;
import com.sm.utils.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

public class StuServiceImpl implements StuService {
    private StuDAO stuDAO = DAOFactory.getStuDAOInstance();
    @Override
    public ResultEntity stuLogin(String account, String password) {
        ResultEntity resultEntity = new ResultEntity();
        Stu stu = null;
        try {
            stu = stuDAO.getStuByAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //根据账号查找到了记录
        if (stu != null) {
            //比较密码，此时需要将客户端传过来的密码进行MD5加密后才能比对
            if (DigestUtils.md5Hex(password).equals(stu.getPassword())) {
                resultEntity.setCode(0);
                resultEntity.setMessage("登录成功");
                resultEntity.setData(stu);
            } else {  //记录存在，密码输入错误
                resultEntity.setCode(1);
                resultEntity.setMessage("密码错误");
            }
        } else {  //账号不存在
            resultEntity.setCode(2);
            resultEntity.setMessage("账号不存在");
        }
        return resultEntity;
    }
}
