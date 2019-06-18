package com.sm.frame;

import com.sm.entity.TeacherVO;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class AddTeacherFrame extends JFrame {
    private AdminMainFrame adminMainFrame;
    private ImgPanel rootPanel;
    private JRadioButton 男RadioButton;
    private JTextField dianhuatextField;
    private JTextField kechengtextField1;
    private JRadioButton 女RadioButton;
    private JButton 新增Button;
    private JComboBox 工号comboBox;
    private JLabel closeLabel;
    private JTextField gonghaotextField;
    private JTextField xingmingtextField;
    private JComboBox xingmingcomboBox;


    public AddTeacherFrame(AdminMainFrame adminMainFrame){
        //设置需要的背景图片
        rootPanel.setFileName("allbg.jpg");
        //组件重绘
        rootPanel.repaint();
        this.adminMainFrame = adminMainFrame;
        setContentPane(rootPanel);
        setUndecorated(true);
        setTitle("新增老师界面");
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        TeacherVO tip1 = new TeacherVO();
//        tip1.setTeacherId("请选择自己的工号");
//        工号comboBox.addItem(tip1);
//        TeacherVO tip2 = new TeacherVO();
//        tip2.setName("请选择自己的姓名");
//        xingmingcomboBox.addItem(tip2);
//        List<TeacherVO> teacherVOList = ServiceFactory.getTeacherServiceInstance().selectAllTeacher();
//        for (TeacherVO teacherVO: teacherVOList){
//            工号comboBox.addItem(teacherVO);
//        }
//        List<TeacherVO> teacherVOList1 = ServiceFactory.getTeacherServiceInstance().selectAllTeacher();
//        for (TeacherVO teacherVO : teacherVOList1){
//            xingmingcomboBox.addItem(teacherVO);
//        }

        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddTeacherFrame.this.dispose();
            }
        });

        新增Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gender = null;
                if (男RadioButton.isSelected()){
                    gender = "男";
                }
                if (女RadioButton.isSelected()){
                    gender = "女";
                }
              TeacherVO teacherVO = new TeacherVO();
              teacherVO.setTeacherId(gonghaotextField.getText());
              teacherVO.setName(xingmingtextField.getText());
              teacherVO.setGender(gender);
              teacherVO.setTel(dianhuatextField.getText());
              teacherVO.setCourse(kechengtextField1.getText());
              int n = ServiceFactory.getTeacherServiceInstance().inTea(teacherVO);
              if (n == 1){
                  JOptionPane.showMessageDialog(rootPanel,"新增成功");
                  AddTeacherFrame.this.dispose();
                  List<TeacherVO> teacherVOList = ServiceFactory.getTeacherServiceInstance().selectAllTeacher();
//                  adminMainFrame.showStudentTable(teacherVOList);
              }
            }
        });
        ButtonGroup group = new ButtonGroup();
        group.add(男RadioButton);
        group.add(女RadioButton);
    }

    public static void main(String[] args) throws Exception{
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new AddTeacherFrame(null);
    }
}













