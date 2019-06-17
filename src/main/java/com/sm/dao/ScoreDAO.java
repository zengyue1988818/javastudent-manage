package com.sm.dao;

import com.sm.entity.Score;
import com.sm.entity.ScoreVO;

import java.sql.SQLException;
import java.util.List;

public interface ScoreDAO {
    /**
     * 查询所有学生
     * @return
     * @throws SQLException
     */
    List<ScoreVO> selectAllScore() throws SQLException;

    /**
     * 根据id删除学生
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteScoreById(int id) throws SQLException;

    /**
     * 根据关键词查询学生
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<ScoreVO> selectByKeywords(String keywords) throws SQLException;

    /**
     * 更新
     * @param score
     * @return
     * @throws SQLException
     */
    int updateScore(Score score) throws SQLException;

    /**
     * 根据id删除信息
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteById(String id) throws SQLException;

    /**
     * 新增信息
     * @param score
     * @return
     * @throws SQLException
     */
    int insertScore(Score score) throws SQLException;

    /**
     * 根据院系id统计学生人数
     * @param departmentId
     * @return
     * @throws SQLException
     */
    int countByDepartmentId(int departmentId) throws SQLException;

    /**
     * 根据班级id统计学生人数
     * @param classId
     * @return
     * @throws SQLException
     */
    int countByClassId(int classId) throws SQLException;

    List<ScoreVO> selectByDepartmentId(int departmentId) throws SQLException;
    List<ScoreVO> selectByClassId(int classId) throws SQLException;


}

