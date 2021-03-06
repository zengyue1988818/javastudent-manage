package com.sm.frame;

import com.sm.entity.Admin;
import com.sm.entity.Stu;
import com.sm.entity.Tea;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;
import com.sm.utils.ResultEntity;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminLoginFrame extends JFrame{
    private ImgPanel rootPanel;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton 重置Button;
    private JButton 登录Button;
    private JRadioButton 学生RadioButton;
    private JRadioButton 老师RadioButton;
    private JRadioButton 管理员RadioButton;
    private JLabel closeLabel;

    public AdminLoginFrame() {
        //设置需要的背景图片
        rootPanel.setFileName("3.jpg");
        //组件重绘
        rootPanel.repaint();
        setTitle("三种登录界面");
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        ButtonGroup group = new ButtonGroup();
        group.add(学生RadioButton);
        group.add(老师RadioButton);
        group.add(管理员RadioButton);

        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdminLoginFrame.this.dispose();
            }
        });
        登录Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String people = null;
                if (学生RadioButton.isSelected()){
                    people = "学生";
                }
                if (老师RadioButton.isSelected()){
                    people = "教师";
                }
                if (管理员RadioButton.isSelected()){
                    people = "管理员";
                }
                if (学生RadioButton.isSelected()){
                    //获得输入的账号和密码，注意密码框组件的使用方法
                    String account = accountField.getText().trim();
                    String password = new String(passwordField.getPassword()).trim();
                    ResultEntity resultEntity = ServiceFactory.getStuInstance().stuLogin(account,password);
                    JOptionPane.showMessageDialog(rootPanel, resultEntity.getMessage());
                    //登录成功，进入主界面，并关闭登录界面
                    if (resultEntity.getCode() == 0) {
                        new StudentMainFrame((Stu) resultEntity.getData());
                        AdminLoginFrame.this.dispose();
                    } else if (resultEntity.getCode() == 1) {  //密码错误，清空密码框
                        passwordField.setText("");
                    } else {   //账号错误，清空两个输入框
                        accountField.setText("");
                        passwordField.setText("");
                    }
                }
                if (老师RadioButton.isSelected()){
                    //获得输入的账号和密码，注意密码框组件的使用方法
                    String account = accountField.getText().trim();
                    String password = new String(passwordField.getPassword()).trim();
                    ResultEntity resultEntity = ServiceFactory.getTeaInstance().teaLogin(account,password);
                    JOptionPane.showMessageDialog(rootPanel, resultEntity.getMessage());
                    //登录成功，进入管理员主界面，并关闭登录界面
                    if (resultEntity.getCode() == 0) {
                        new TeacherMainFrame((Tea) resultEntity.getData());
                        AdminLoginFrame.this.dispose();
                    } else if (resultEntity.getCode() == 1) {  //密码错误，清空密码框
                        passwordField.setText("");
                    } else {   //账号错误，清空两个输入框
                        accountField.setText("");
                        passwordField.setText("");
                    }
                }
                if (管理员RadioButton.isSelected()) {
                    //获得输入的账号和密码，注意密码框组件的使用方法
                    String account = accountField.getText().trim();
                    String password = new String(passwordField.getPassword()).trim();
                    ResultEntity resultEntity = ServiceFactory.getAdminServiceInstance().adminLogin(account,password);
                    JOptionPane.showMessageDialog(rootPanel, resultEntity.getMessage());
                    //登录成功，进入主界面，并关闭登录界面
                    if (resultEntity.getCode() == 0) {
                        new AdminMainFrame((Admin) resultEntity.getData());
                        AdminLoginFrame.this.dispose();
                    } else if (resultEntity.getCode() == 1) {  //密码错误，清空密码框
                        passwordField.setText("");
                    } else {   //账号错误，清空两个输入框
                        accountField.setText("");
                        passwordField.setText("");
                    }
                }
            }
        });
        重置Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountField.setText("");
                passwordField.setText("");
            }
        });
    }

    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new AdminLoginFrame();
    }
}

