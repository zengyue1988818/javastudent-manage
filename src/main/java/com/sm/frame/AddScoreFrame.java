package com.sm.frame;

import com.sm.entity.CClass;
import com.sm.entity.Department;
import com.sm.entity.ScoreVO;
import com.sm.entity.TeacherVO;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AddScoreFrame extends JFrame{
    private AdminMainFrame adminMainFrame;
    private ImgPanel rootPanel;
    private JComboBox yuanxicomboBox;
    private JComboBox banjicomboBox;
    private JTextField nameField;
    private JTextField chineseField;
    private JTextField mathField;
    private JRadioButton 男RadioButton;
    private JRadioButton 女RadioButton;
    private JButton 新增Button;
    private JTextField englishField;
    private JLabel closeLabel;
    private JTextField xuehaoField;

    public AddScoreFrame(AdminMainFrame adminMainFrame){
        //设置需要的背景图片
        rootPanel.setFileName("allbg.jpg");
        //组件重绘
        rootPanel.repaint();
        this.adminMainFrame = adminMainFrame;
        setContentPane(rootPanel);
        setUndecorated(true);
        setTitle("新增学生成绩界面");
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Department tip = new Department();
        tip.setDepartmentName("请选择院系");
        yuanxicomboBox.addItem(tip);
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        for (Department department : departmentList){
            yuanxicomboBox.addItem(department);
        }
        CClass tip1 = new CClass();
        tip1.setClassName("请选择班级");
        banjicomboBox.addItem(tip1);
        List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectAll();
        for (CClass cClass : cClassList) {
            banjicomboBox.addItem(cClass);
        }



        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddScoreFrame.this.dispose();
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
                ScoreVO scoreVO = new ScoreVO();
                scoreVO.setId(xuehaoField.getText());
                scoreVO.setStudentName(nameField.getText());
                scoreVO.setChinese(chineseField.getText());
                scoreVO.setEnglish(englishField.getText());
                scoreVO.setMath(mathField.getText());
                scoreVO.setGender(gender);
//                int n = ServiceFactory.getScoreServiceInstance().insertScore(scoreVO);
//                if (n == 1)
                        {
                    JOptionPane.showMessageDialog(rootPanel,"新增成功");
                    AddScoreFrame.this.dispose();
                    List<ScoreVO> scoreVOList = ServiceFactory.getScoreServiceInstance().selectAllScore();
                  adminMainFrame.showStudentTable(scoreVOList);
                }
            }
        });
        ButtonGroup group = new ButtonGroup();
        group.add(男RadioButton);
        group.add(女RadioButton);
    }

    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new AddScoreFrame(null);
    }
}
