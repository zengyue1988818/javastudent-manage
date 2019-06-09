package com.sm.dao;

import com.sm.entity.*;
import com.sm.factory.ServiceFactory;
import com.sun.org.apache.xerces.internal.xs.StringList;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    /**
     * 查询所有学生（视图对象）
     * @return List<StudentVO>
     * @throws SQLException
     */
    List<StudentVO> selectAll() throws SQLException;

    /**
     * 根据id删除学生
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteStudentById(int id) throws SQLException;

    /**
     * 根据院系id查询学生
     * @param departmentId
     * @return
     * @throws SQLException
     */
    List<StudentVO> selectByDepartmentId(int departmentId) throws SQLException;

    /**
     * 根据班级id查询学生
     * @param classId
     * @return
     * @throws SQLException
     */
    List<StudentVO> selectByClassId(int classId) throws SQLException;

    /**
     * 根据关键词查询学生
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<StudentVO> selectByKeywords(String keywords)throws SQLException;

    /**
     * 更新学生信息
     * @param student
     * @return int
     * @throws SQLException
     */
    int updateStudent(Student student)throws SQLException;

    /**
     * 根据id删除学生信息
     * @param id
     * @return int
     * @throws SQLException
     */
    int deleteById(String id) throws SQLException;

    /**
     * 新增学生
     * @param student
     * @return
     * @throws SQLException
     */
    int insertStudent(Student student)throws SQLException;

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



    //奖励和惩罚模块

    /**
     * 查询奖励学生
     * @return
     * @throws SQLException
     */
    List<RewardVO> selectAllReward() throws SQLException;

    List<RewardVO> selectByStuId(String id) throws SQLException;

    List<RewardVO> selectRewByKeywords(String keywords) throws SQLException;
    /**
     * 查看惩罚学生
     * @return List<PunishVO>
     * @throws SQLException
     */
    List<PunishVO> selectAllPunish() throws SQLException;

    List<PunishVO> selectPunByKeywords(String keywords) throws SQLException;
    /**
     *奖励
     */
    int deleteRewById(int rewId) throws SQLException;

    int updateRew(RewardVO rewardVO) throws SQLException;

    int insertRew(RewardVO rewardVO) throws SQLException;

    /**
     * 惩罚
     */
    int deletePunById(int punId) throws SQLException;

    int updatePun(PunishVO punishVO) throws SQLException;

    int insertPun(PunishVO punishVO) throws SQLException;

}
