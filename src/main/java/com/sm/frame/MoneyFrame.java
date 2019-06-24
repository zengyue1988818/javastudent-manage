package com.sm.frame;

import com.sm.ui.ImgPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MoneyFrame extends JFrame{
    private ImgPanel rootPanel;
    private JLabel closeLabel;
    private JLabel moneyLabel;
    private JLabel dateLabel;
    private TeacherMainFrame teacherMainFrame;

    public MoneyFrame(TeacherMainFrame teacherMainFrame) {
        //设置需要的背景图片
        rootPanel.setFileName("backg1.jpg");
        //组件重绘
        rootPanel.repaint();
        this.teacherMainFrame = teacherMainFrame;
        setContentPane(rootPanel);
        setUndecorated(true);
        setTitle("老师薪水界面");
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MoneyFrame.this.dispose();
            }
        });
        dateLabel.setText("256天");
        moneyLabel.setText("￥33800");
    }

    public static void main(String[] args) throws Exception{
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new MoneyFrame(null);
    }
}
