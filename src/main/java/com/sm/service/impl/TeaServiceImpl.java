package com.sm.service.impl;

import com.sm.dao.AdminDAO;
import com.sm.dao.TeaDAO;
import com.sm.entity.Admin;
import com.sm.entity.Stu;
import com.sm.entity.Tea;
import com.sm.factory.DAOFactory;
import com.sm.service.TeaService;
import com.sm.utils.ResultEntity;
import com.sun.istack.internal.NotNull;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

public class TeaServiceImpl implements TeaService {
    private TeaDAO teaDAO = DAOFactory.getTeaDAOInstance();


    @Override
    public ResultEntity teaLogin(String account, String password) {
        ResultEntity resultEntity = new ResultEntity();
        Tea tea = null;
        try {
            tea = teaDAO.getTeaByAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //根据账号查找到了记录
        if (tea != null) {
            //比较密码，此时需要将客户端传过来的密码进行MD5加密后才能比对
            if (DigestUtils.md5Hex(password).equals(tea.getPassword())) {
                resultEntity.setCode(0);
                resultEntity.setMessage("登录成功");
                resultEntity.setData(tea);
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

