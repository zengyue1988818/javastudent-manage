package com.sm.service.impl;

import com.sm.dao.ScoreDAO;
import com.sm.entity.Score;
import com.sm.entity.ScoreVO;
import com.sm.factory.DAOFactory;
import com.sm.service.ScoreService;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.SQLException;
import java.util.List;

public class ScoreServiceImpl implements ScoreService {
    private ScoreDAO scoreDAO = DAOFactory.getScoreDAOInstance();
    @Override
    public List<ScoreVO> selectAllScore() {
        List<ScoreVO> scoreVOList = null;
        try {
            scoreVOList = scoreDAO.selectAllScore();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreVOList;
    }

    @Override
    public void deleteScoreById(int id) {
        try {
            scoreDAO.deleteScoreById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ScoreVO> selectByKeywords(String keywords) {
        List<ScoreVO> scoreVOList = null;
        try {
            scoreVOList = scoreDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreVOList;
    }

    @Override
    public int updateScore(Score score) throws SQLException {
        int n = 0;
        n = scoreDAO.updateScore(score);
        return  n ;
    }

    @Override
    public int deleteById(String id) throws SQLException {
        int n = 0;
        n = scoreDAO.deleteById(id);
        return n;
    }

    @Override
    public int insertScore(Score score) throws SQLException {
        int n = 0;
        n = scoreDAO.insertScore(score);
        return n;
    }

    @Override
    public int countScoreByDepartmentId(int departmentId) {
        int n = 0;
        try {
            n = scoreDAO.countByDepartmentId(departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int countScoreByClassId(int classId) {
        int n = 0;
        try {
            n = scoreDAO.countByClassId(classId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n ;
    }

    @Override
    public List<ScoreVO> selectByDepartmentId(int departmentId) {
        List<ScoreVO>scoreVOList = null;
        try {
            scoreVOList = scoreDAO.selectByDepartmentId(departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreVOList;
    }

    @Override
    public List<ScoreVO> selectByClassId(int classId) {
        List<ScoreVO> scoreVOList = null;
        try {
            scoreVOList = scoreDAO.selectByClassId(classId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreVOList;
    }

    }

