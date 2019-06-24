package com.sm.service.impl;

import com.sm.dao.TeachDAO;
import com.sm.entity.Teach;
import com.sm.entity.TeacherVO;
import com.sm.factory.DAOFactory;
import com.sm.service.TeachService;

import javax.swing.text.Document;
import java.sql.SQLException;
import java.util.List;

public class TeachServiceImpl implements TeachService {
    private TeachDAO teachDAO = DAOFactory.getTeachDAOInstance();

    @Override
    public List<Teach> selectAllTeach() {
        List<Teach> teachList = null;
        try {
            teachList = teachDAO.selectAllTeach();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachList;
    }

    @Override
    public void deleteTeachById(int id) {
        try {
            teachDAO.deleteById(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateTeach(Teach teach)  {
        int n = 0 ;
        try {
            teachDAO.updateTeach(teach);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
