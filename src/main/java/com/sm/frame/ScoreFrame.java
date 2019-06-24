package com.sm.frame;

import com.sm.entity.StudentOneVO;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ScoreFrame extends JFrame{
    private StudentMainFrame studentMainFrame;
    private ImgPanel rootPanel;
    private JLabel closePanel;
    private JLabel idLabel;
    private JLabel departmentLabel;
    private JLabel nameLabel;
    private JLabel genderLabel;
    private JLabel chineseLabel;
    private JLabel englishLabel;
    private JLabel mathLabel;


    public ScoreFrame(StudentMainFrame studentMainFrame){
        //设置需要的背景图片
        rootPanel.setFileName("backg2.jpg");
        //组件重绘
        rootPanel.repaint();
        this.studentMainFrame = studentMainFrame;
        setContentPane(rootPanel);
        setUndecorated(true);
        setTitle("我的成绩界面");
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        closePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScoreFrame.this.dispose();
            }
        });
        StudentOneVO studentOneVO = new StudentOneVO();
        idLabel.setText("180234338");
        departmentLabel.setText("机械院");
        chineseLabel.setText("115");
        englishLabel.setText("68");
        mathLabel.setText("138");
        genderLabel.setText("男");
        nameLabel.setText("权志龙");
    }

    public static void main(String[] args) throws Exception{
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new ScoreFrame(null);
    }
}
