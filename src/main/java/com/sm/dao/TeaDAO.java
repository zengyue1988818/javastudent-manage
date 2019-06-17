package com.sm.dao;


import com.sm.entity.Tea;

import java.sql.SQLException;

public interface TeaDAO {
    Tea getTeaByAccount(String account) throws SQLException;
}
