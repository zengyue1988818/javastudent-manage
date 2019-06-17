package com.sm.service;

import com.sm.entity.Score;
import com.sm.entity.ScoreVO;

import java.sql.SQLException;
import java.util.List;

public interface ScoreService {
    List<ScoreVO> selectAllScore();
    void deleteScoreById(int id);
    List<ScoreVO> selectByKeywords(String keywords);
    int updateScore(Score score) throws SQLException;
    int deleteById(String id) throws SQLException;
    int insertScore(Score score) throws SQLException;
    int countScoreByDepartmentId(int departmentId);
    int countScoreByClassId(int classId);
    List<ScoreVO> selectByDepartmentId(int departmentId);
    List<ScoreVO> selectByClassId(int classId);
}
