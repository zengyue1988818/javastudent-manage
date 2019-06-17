package com.sm.service.impl;

import com.sm.entity.Score;
import com.sm.entity.ScoreVO;
import com.sm.factory.ServiceFactory;
import com.sm.service.ScoreService;
import org.junit.Test;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ScoreServiceImplTest {
    private ScoreService scoreService = ServiceFactory.getScoreServiceInstance();
    @Test
    public void selecctAllScore(){
        List<ScoreVO> scoreVOList = scoreService.selectAllScore();
        scoreVOList.forEach(scoreVO -> System.out.println(scoreVO));
    }
    @Test
    public void deleteScoreById(){
        int id = 1802343313;
        scoreService.deleteScoreById(id);
    }
    @Test
    public void selectByKeywords(){
        List<ScoreVO> scoreVOList = null;
        scoreVOList = scoreService.selectByKeywords("125");
        scoreVOList.forEach(scoreVO -> System.out.println(scoreVO));
    }
    @Test
    public void updateScore() throws SQLException{
        Score score = new Score();
        score.setId("1802343312");
        score.setChinese("101");
        score.setEnglish("15");
        score.setMath("20");
        int n = scoreService.updateScore(score);
        assertEquals(1,n);
    }
    @Test
    public void insertScore() throws SQLException{
        Score score = new Score();
        score.setId("1802343313");
        score.setClassId(1);
        score.setStudentName("鹿晗");
        score.setAvatar("2.jpg");
        score.setGender("男");
        score.setChinese("10");
        score.setEnglish("15");
        score.setMath("20");
        int n = scoreService.insertScore(score);
        System.out.println(n);
    }
}
