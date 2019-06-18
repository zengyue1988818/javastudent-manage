package com.sm.frame;

import com.sm.entity.TeacherVO;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ChangeFrame extends JFrame {
    private ImgPanel rootPanel;
    private JLabel closeLabel;
    private JButton 修改Button;
    private JLabel genderLabel;
    private JLabel nameLabel;
    private JLabel idLabel;
    private JTextField courseField;
    private JTextField telField;
    private TeacherMainFrame teacherMainFrame;

    public ChangeFrame(TeacherMainFrame teacherMainFrame){
        //设置需要的背景图片
        rootPanel.setFileName("allbg.jpg");
        //组件重绘
        rootPanel.repaint();
        this.teacherMainFrame = teacherMainFrame;
        setContentPane(rootPanel);
        setUndecorated(true);
        setTitle("修改老师界面");
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        List<TeacherVO> teacherVOList = ServiceFactory.getTeacherServiceInstance().selectAllTeacher();
        showTeacher(teacherVOList);

        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChangeFrame.this.dispose();
            }
        });
    }

    private void showTeacher(List<TeacherVO> teacherVOList) {
    }


    public static void main(String[] args) throws Exception{
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new ChangeFrame(null);
    }
}
