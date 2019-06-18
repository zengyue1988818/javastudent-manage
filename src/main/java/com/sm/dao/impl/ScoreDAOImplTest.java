package com.sm.dao.impl;

import com.sm.dao.ScoreDAO;
import com.sm.entity.Score;
import com.sm.entity.ScoreVO;
import com.sm.entity.Student;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ScoreDAOImplTest {
    private ScoreDAO scoreDAO = DAOFactory.getScoreDAOInstance();
    @Test
    public void selectAllScore(){
        List<ScoreVO> scoreVoList = null;
        try {
            scoreVoList = scoreDAO.selectAllScore();
        }catch (SQLException e){
            e.printStackTrace();
        }
        scoreVoList.forEach(scoreVO -> System.out.println(scoreVO));
    }
    @Test
    public void deleteScoreById(){
        int id = 1802343313;
        try {
            scoreDAO.deleteScoreById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void selectByKeywords(){
        List<ScoreVO> scoreVOList = null;
        try {
            scoreVOList = scoreDAO.selectByKeywords("125");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scoreVOList.forEach(scoreVO -> System.out.println(scoreVO));
    }
    @Test
    public void updateScore() throws SQLException{
        Score score = new Score();
        score.setId("1802343312");
        score.setChinese("10");
        score.setEnglish("15");
        score.setMath("20");
        int n = scoreDAO.updateScore(score);
        assertEquals(1,n);
    }
    @Test
    public void deleteById()throws SQLException{
        int n = scoreDAO.deleteById("1802343312");
        assertEquals(1,n);
    }
    @Test
    public void insertScore(){
        Score score = new Score();
        score.setId("1802343350");
        score.setClassId(2);
        score.setStudentName("王子异");
        score.setAvatar("2.jpg");
        score.setGender("男");
        score.setChinese("10");
        score.setEnglish("15");
        score.setMath("20");
        try {
            int n = scoreDAO.insertScore(score);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void selectByDepartmentId(){
        List<ScoreVO> scoreVOList = null;
        try {
            scoreVOList = scoreDAO.selectByDepartmentId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scoreVOList.forEach(scoreVO -> System.out.println(scoreVO));
    }
    @Test
    public void selectByClassId(){
        List<ScoreVO> scoreVOList = null;
        try {
            scoreVOList = scoreDAO.selectByClassId(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scoreVOList.forEach(scoreVO -> System.out.println(scoreVO));
    }
}
