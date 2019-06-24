package com.sm.frame;

import com.sm.entity.Tea;
import com.sm.entity.Teach;
import com.sm.entity.TeacherVO;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;
import org.junit.Test;
import org.testng.mustache.Model;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class ChangeFrame extends JFrame {
    private ImgPanel rootPanel;
    private JTextField telField;
    private JButton 关闭Button;
    private JButton 编辑Button;
    private JLabel teacheriDLabel;
    private JLabel nameLabel;
    private JLabel genderLabel;
    private JLabel courseLabel;
    private ImgPanel teacherPanel;
    private JLabel closeLabel;
    private TeacherMainFrame teacherMainFrame;
    private Font font;
    private AbstractButton e;

    public ChangeFrame(TeacherMainFrame teacherMainFrame) throws SQLException {
        //设置需要的背景图片
        rootPanel.setFileName("allbg.jpg");
        //组件重绘
        rootPanel.repaint();
        this.teacherMainFrame = teacherMainFrame;
        setContentPane(rootPanel);
        setUndecorated(true);
        setTitle("修改老师界面");
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        List<Teach> teachList = ServiceFactory.getTeachInsuance().selectAllTeach();
        showTeacher(teachList);
        编辑Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("编辑")){
                    telField.setEditable(true);
                    telField.setEnabled(true);
                    编辑Button.setText("保存");
                }
                if (e.getActionCommand().equals("保存")){
                    Teach teach = new Teach();
                    teach.setTel(telField.getText());
                    teach.setId(Integer.valueOf(teacheriDLabel.getText()));
                    int n = ServiceFactory.getTeachInsuance().updateTeach(teach);
                    JOptionPane.showMessageDialog(null,"修改成功！");
                    编辑Button.setText("编辑");
                    if (n == 1) {
                            System.out.println("1");
                            telField.setEnabled(false);
                            telField.setEditable(false);
                            编辑Button.setText("编辑");
                    }
                }
            }
        });
        关闭Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeFrame.this.dispose();
            }
        });
    }

    private void showTeacher(List<Teach> teachList) throws SQLException {
        teacherPanel.setFileName("backg1.jpg");
        for (Teach teach : teachList) {
            teacheriDLabel.setText(teach.getTeacherId());
            nameLabel.setText(teach.getName());
            genderLabel.setText(teach.getGender());
            telField.setText(teach.getTel());
            courseLabel.setText(teach.getCourse());
        }
    }


    public static void main(String[] args) throws Exception{
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new ChangeFrame(null);
    }
}
