package com.sm.service.impl;

import com.sm.dao.StudentDAO;
import com.sm.entity.*;
import com.sm.factory.DAOFactory;
import com.sm.service.StudentService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();

    @Override
    public List<StudentVO> selectAll() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentVOList;
    }

    @Override
    public void deleteStudentById(int id) {
        try {
            studentDAO.deleteStudentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StudentVO> selectByDepartmentId(int departmentId) {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByDepartmentId(departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentVOList;
    }

    @Override
    public List<StudentVO> selectByClassId(int classId) {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByClassId(classId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentVOList;
    }

    @Override
    public List<StudentVO> selectByKeywords(String keywords) {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentVOList;
    }

    @Override
    public int updateStudent(Student student) throws SQLException {
        int n = 0;
        n = studentDAO.updateStudent(student);
        return n;
    }

    @Override
    public int deleteById(String id) throws SQLException {
        int n = 0;
        try {
            n = studentDAO.deleteById(id);
        } catch (SQLException e) {
            System.err.print("删除学生id出现异常");
        }
        return n;
    }

    @Override
    public int insertStudent(Student student) {
        int n = 0;
        try {
            n = studentDAO.insertStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int addStudent(Student student) {
        int n = 0;
        try {
            n = studentDAO.insertStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int countStudentByClassId(int classId) {
        int n = 0;
        try {
            n = studentDAO.countByClassId(classId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
   //奖励和惩罚
    @Override
    public List<RewardVO> selectAllReward() {
        List<RewardVO> rewardVOList = null;
        try {
            rewardVOList = studentDAO.selectAllReward();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewardVOList;
    }

    @Override
    public List<RewardVO> selectByStuId(String id) {
        List<RewardVO> rewardVOList = null;
        try {
            rewardVOList = studentDAO.selectByStuId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewardVOList;
    }

    @Override
    public List<RewardVO> selectRewByKeywords(String keywords) {
        List<RewardVO> rewardVOList = null;
        try {
            rewardVOList = studentDAO.selectRewByKeywords(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewardVOList;
    }

    @Override
    public List<PunishVO> selectAllPunish() {
        List<PunishVO> punishVOList = null;
        try {
            punishVOList = studentDAO.selectAllPunish();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return punishVOList;
    }

    @Override
    public List<PunishVO> selectPunByKeywords(String keywords) {
        List<PunishVO> punishVOList = null;
        try {
            punishVOList = studentDAO.selectPunByKeywords(keywords);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return punishVOList;
    }

    @Override
    public int delRewById(int rewId) {
        int n = 0;
        try {
            n = studentDAO.deleteRewById(rewId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  n;    }

    @Override
    public int upRew(RewardVO rewardVO) {
        int n = 0;
        try {
            n = studentDAO.updateRew(rewardVO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  n;
    }

    @Override
    public int inRew(RewardVO rewardVO) {
        int n = 0;
        try {
            n = studentDAO.insertRew(rewardVO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  n;
    }

    @Override
    public int delPunById(int punId) {
        int n = 0;
        try {
            n = studentDAO.deletePunById(punId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  n;
    }

    @Override
    public int upPun(PunishVO punishVO) {
        int n = 0;
        try {
            n = studentDAO.updatePun(punishVO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  n;
    }

    @Override
    public int inPun(PunishVO punishVO) {
        int n = 0;
        try {
            n = studentDAO.insertPun(punishVO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  n;
    }
}
