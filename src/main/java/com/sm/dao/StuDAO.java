package com.sm.dao;

import com.sm.entity.Stu;

import java.sql.SQLException;

public interface StuDAO {
    Stu getStuByAccount(String account) throws SQLException;
}
