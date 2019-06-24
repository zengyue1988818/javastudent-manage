package com.sm.service;

import com.sm.entity.Teach;

import java.sql.SQLException;
import java.util.List;

public interface TeachService {
    List<Teach> selectAllTeach();
    void deleteTeachById(int id);
    int updateTeach(Teach teach);
}
