package com.sm.frame;

import com.sm.ui.ImgPanel;

import javax.swing.*;

public class ChangeScoreFrame extends JFrame {
    private ImgPanel rootPanel;
    private TeacherMainFrame teacherMainFrame;

    public ChangeScoreFrame(TeacherMainFrame teacherMainFrame) {
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
    }

    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new ChangeScoreFrame(null);
    }
}
