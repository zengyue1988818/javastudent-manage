package com.sm.frame;

import com.eltima.components.ui.DatePicker;
import com.sm.entity.*;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddRewardFrame extends JFrame {
    private AdminMainFrame adminMainFrame;
    private ImgPanel rootPanel;
    private JComboBox<Department> adddepartcomboBox;
    private JComboBox<CClass> addclasscomboBox;
    private JComboBox<StudentVO> addnamecomboBox;
    private JTextField rewardtextField;
    private JPanel datePanel;
    private JLabel closeLabel;
    private JButton 新增Button;
    private JLabel addIdLabel;
    private String uploadFileUrl;
    private File file;
    private int departmentId;

    public AddRewardFrame(AdminMainFrame adminMainFrame) {
        //设置需要的背景图片
        rootPanel.setFileName("bg4.jpg");
        //组件重绘
        rootPanel.repaint();
        this.adminMainFrame = adminMainFrame;
        setContentPane(rootPanel);
        setUndecorated(true);
        setTitle("新增学生奖励界面");
        setSize(1920,1080);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Department tip1 = new Department();
        tip1.setDepartmentName("请选择院系");
        adddepartcomboBox.addItem(tip1);
        CClass tip2 = new CClass();
        tip2.setClassName("请选择班级");
        addclasscomboBox.addItem(tip2);
        StudentVO tip3 = new StudentVO();
        tip3.setStudentName("请选择学生");
        addnamecomboBox.addItem(tip3);
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        for (Department department : departmentList){
            adddepartcomboBox.addItem(department);
        }
        List<CClass> classList =ServiceFactory.getCClassServiceInstance().selectAll();
        for (CClass cClass : classList){
            addclasscomboBox.addItem(cClass);
        }
        List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectAll();
        for (StudentVO student : studentList){
            addnamecomboBox.addItem(student);
        }

        DatePicker datepick = getDatePicker();
        datePanel.add(datepick);
        datePanel.revalidate();

        adddepartcomboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int index = adddepartcomboBox.getSelectedIndex();
                    //排除第一项信息
                    if (index != 0 ){
                  departmentId = adddepartcomboBox.getItemAt(index).getId();
                 //二级联动
                        List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(departmentId);
                    addclasscomboBox.removeAllItems();
                    CClass tip = new CClass();
                    tip.setClassName("请选择班级");
                    addclasscomboBox.addItem(tip);
                    for (CClass cClass : cClassList) {
                        addclasscomboBox.addItem(cClass);
                    }
                    }
                }
            }
        });
        addclasscomboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int index = addclasscomboBox.getSelectedIndex();
                    if (index != 0){
                        int classId = addclasscomboBox.getItemAt(index).getId();
                        //三级联动
//                        List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().getStuClassId(classId);
                        addnamecomboBox.removeAllItems();
                        StudentVO tip = new StudentVO();
                        tip.setStudentName("请选择学生");
                        addnamecomboBox.addItem(tip);
                        for (StudentVO student:studentList){
                            addnamecomboBox.addItem(student);
                        }
                    }
                }
            }
        });
        addnamecomboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int index = addnamecomboBox.getSelectedIndex();
                    if (index != 0 ){
                        String studentId = addnamecomboBox.getItemAt(index).getId();
                        addIdLabel.setText(studentId);
                    }
                }
            }
        });
        新增Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RewardVO rewardVO = new RewardVO();
                rewardVO.setStudentId(addIdLabel.getText());
                rewardVO.setReward(rewardtextField.getText());
                rewardVO.setRewardDate((Date) datepick.getValue());
               int n = ServiceFactory.getStudentServiceInstance().inRew(rewardVO);
                if (n  == 1){
                    JOptionPane.showMessageDialog(rootPanel,"新增成功");
                    AddRewardFrame.this.dispose();
                    List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectAll();
                    adminMainFrame.showStudentTable(studentList);
                }
            }
        });

        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddRewardFrame.this.dispose();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new AddRewardFrame(null);
    }

    private static DatePicker getDatePicker() {
        final DatePicker datepick;
        // 格式
        String DefaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.PLAIN, 18);
        Dimension dimension = new Dimension(200, 30);
        int[] hilightDays = {1, 3, 5, 7};
        int[] disabledDays = {4, 6, 5, 9};
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datepick = new DatePicker(date, DefaultFormat, font, dimension);
//        datepick.setLocation(137, 83);//设置起始位置
        /*
        //也可用setBounds()直接设置大小与位置
        datepick.setBounds(137, 83, 177, 24);
        */
        // 设置一个月份中需要高亮显示的日子
        datepick.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datepick.setDisableddays(disabledDays);
        // 设置国家
        datepick.setLocale(Locale.CHINA);
        // 设置时钟面板可见
//        datepick.setTimePanleVisible(true);
        return datepick;
    }
}

















