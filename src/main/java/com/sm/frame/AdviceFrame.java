package com.sm.frame;

import com.sm.ui.ImgPanel;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdviceFrame extends JFrame {
    private StudentMainFrame studentMainFrame;
    private ImgPanel rootPanel;
    private JLabel closeLabel;
    private JLabel chineseLabel;
    private JLabel englishLabel;
    private JLabel mathLabel;


    public AdviceFrame(StudentMainFrame studentMainFrame){
        //设置需要的背景图片
        rootPanel.setFileName("backg3.jpg");
        //组件重绘
        rootPanel.repaint();
        this.studentMainFrame = studentMainFrame;
        setContentPane(rootPanel);
        setUndecorated(true);
        setTitle("老师建议界面");
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        closeLabel.addComponentListener(new ComponentAdapter() {
        });
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdviceFrame.this.dispose();
            }
        });
        chineseLabel.setText("善于观察，仔细思考，勤奋写作，经常反思");
        englishLabel.setText("课前做好上课的充分准备"+"自习课保持安静，自觉，有计划，高效率的学习");
        mathLabel.setText("自信，自强，学会沟通，构建和谐的人际关系");
    }

    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new AdviceFrame(null);
    }
}
