package com.sm.dao;

import com.sm.entity.StudentOne;
import com.sm.entity.StudentOneVO;

import java.sql.SQLException;
import java.util.List;

public interface StudentOneDAO {
    List<StudentOneVO> selectAllStudent() throws SQLException;
}
