package com.sm.dao;

import com.sm.entity.Teach;
import com.sm.entity.TeacherVO;

import java.sql.SQLException;
import java.util.List;

public interface TeachDAO {
    /**
     * 查询老师
     * @return
     * @throws SQLException
     */
    List<Teach> selectAllTeach() throws SQLException;
    int updateTeach(Teach teach) throws SQLException;
    int deleteById(Integer id) throws SQLException;
}
