package com.sm.frame;

import com.sm.entity.Department;
import com.sm.entity.ScoreVO;
import com.sm.entity.Tea;
import com.sm.entity.TeacherVO;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class TeacherMainFrame extends JFrame {
    private ImgPanel rootPanel;
    private JButton 院系公报Button;
    private JButton 老师简介Button;
    private JButton 学生成绩Button;
    private JPanel centerPanel;
    private JPanel teacherPanel;
    private JPanel studentPanel;
    private JPanel departmentPanel;
    private JPanel tablePanel;
    private JPanel infoPanel;
    private JButton 修改成绩Button;
    private JComboBox decomboBox;
    private JComboBox classcomboBox;
    private JPasswordField searchField;
    private JButton 搜索Button;
    private JButton 初始界面Button;
    private JButton 修改我的信息Button;
    private JPanel teaPanel;
    private JPanel topPanel;
    private JButton 查看我的工资条Button;
    private JPanel decenterPanel;
    private JPanel detopPanel;
    private JScrollPane departScroll;
    private JScrollPane teacherScroll;
    private JLabel teacherLabel;
    private JPanel tabelPanel;
    private Font font;
    private Tea tea;

    public TeacherMainFrame(Tea tea) {
        font = new Font("微软雅黑",Font.BOLD,20);
        //设置需要的背景图片
        rootPanel.setFileName("allbg.jpg");
        //组件重绘
        rootPanel.repaint();

        this.tea = tea;
        teacherLabel.setText("欢迎您："+ tea.getTeaName());
        //窗体基本属性
        setTitle("老师界面");
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //获取centerPanel的布局,获取的是LayoutManager，向下转型为cardLayout
        CardLayout cardLayout = (CardLayout) centerPanel.getLayout();

        老师简介Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card1");
                List<TeacherVO> teacherVOList = ServiceFactory.getTeacherServiceInstance().selectAllTeacher();
                showTeacher(teacherVOList);
            }

        });


        学生成绩Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card2");
                //调用显示学生成绩数据的办法
                List<ScoreVO> scoreList = ServiceFactory.getScoreServiceInstance().selectAllScore();
                showStudentTable(scoreList);
            }
        });


        院系公报Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card3");
                showDepartments();
            }

        });

        修改我的信息Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    new ChangeFrame(TeacherMainFrame.this);
            }
        });
    }


    private void showStudentTable(List<ScoreVO> scoreList) {
        tablePanel.removeAll();
        //创建表格
        JTable table = new JTable();
        //表格数据模型
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        //表头内容
        model.setColumnIdentifiers(new String[]{"学号", "院系", "班级", "姓名", "性别", "语文", "英语", "数学", "头像"});
        //遍历List,转成Obiect数组
        for (ScoreVO score : scoreList) {
            Object[] object = new Object[]{score.getId(), score.getDepartmentName(), score.getClassName(),
                    score.getStudentName(), score.getGender(), score.getChinese(), score.getEnglish(), score.getMath(), score.getAvatar()};
            model.addRow(object);
        }
        //将最后一列隐藏头像地址不显示在表格中
        TableColumn tc = table.getColumnModel().getColumn(8);
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
        //获得表头
        JTableHeader head = table.getTableHeader();
        //表头居中
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        head.setDefaultRenderer(hr);
        //设置表头大小
        head.setPreferredSize(new Dimension(head.getWidth(), 40));
        //设置表头字体
        head.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        //设置表头行高
        table.setRowHeight(35);
        //表格背景色
        table.setBackground(new Color(242, 178, 187));
        //表格内容居中
        DefaultTableCellHeaderRenderer r = new DefaultTableCellHeaderRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        //表格加入滚动面板，水平垂直方向带滚动条
        JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablePanel.add(scrollPane);
        tablePanel.revalidate();
    }

    private void showDepartments() {
        decenterPanel.removeAll();
        //从service层获取到所有院系列表
        List <Map> departmentList = ServiceFactory.getDepartmentServiceInstance().selectDepartmentInfo();
        GridLayout gridLayout = new GridLayout(5,2,10,10);
        decenterPanel.setLayout(gridLayout);
        for (Map map : departmentList) {
            //给每个院系对象创建一个面板
            JPanel depPanel = new JPanel();
            Department department = (Department) map.get("department");
            int classCount = (int) map.get("classCount");
            int studentCount = (int) map.get("studentCount");
            depPanel.setBackground(new Color(242, 233, 216));
            depPanel.setPreferredSize(new Dimension(200, 300));
            //将院系名称设置给面板标题
            depPanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));
            depPanel.setFont(font);
            //新建一个Label用来放置院系logo，并指定大小
            JLabel logoLabel = new JLabel("<html><img src='" + department.getLogo() + "'/></html>");
            logoLabel.setPreferredSize(new Dimension(200,200));
            depPanel.add(logoLabel);
            JLabel infoLabel = new JLabel("班级"+classCount+"个，学生"+studentCount+"人");
            depPanel.add(infoLabel);
            decenterPanel.add(depPanel);
            decenterPanel.revalidate();
        }
    }

    private void showTeacher(List<TeacherVO> teacherVOList) {
        GridLayout gridLayout = new GridLayout(0,3,20,20);
        teaPanel.setBorder(new EmptyBorder(15,10,15,10));
        teaPanel.setBounds(100,100,200,300);
        teaPanel.setLayout(gridLayout);
        teaPanel.setBackground(Color.PINK);
        for (TeacherVO teacherVO : teacherVOList){
            //给每个老师创建一个面板
            JPanel jPanel1 = new JPanel();
            jPanel1.setLayout(new GridLayout(7,1));
            jPanel1.setPreferredSize(new Dimension(200,400));
            System.out.println("1");
            JLabel deLabel = new JLabel("老师工号：" + teacherVO.getTeacherId());
            deLabel.setBounds(50,50,200,200);
            deLabel.setFont(font);
            jPanel1.add(deLabel);
            JLabel nameLabel = new JLabel("老师姓名：" + teacherVO.getName());
            nameLabel.setBounds(250,50,200,200);
            nameLabel.setFont(font);
            jPanel1.add(nameLabel);
            JLabel genderLabel = new JLabel("老师性别：" + teacherVO.getGender());
            genderLabel.setBounds(550,50,200,200);
            genderLabel.setFont(font);
            jPanel1.add(genderLabel);
            JLabel telLabel = new JLabel("老师电话：" + teacherVO.getTel());
            telLabel.setBounds(750,50,200,200);
            telLabel.setFont(font);
            jPanel1.add(telLabel);
            JLabel classLabel = new JLabel("老师课程：" + teacherVO.getCourse());
            classLabel.setBounds(950,50,200,200);
            classLabel.setFont(font);
            jPanel1.add(classLabel);
            teaPanel.add(jPanel1);
        }
    }






    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new TeacherMainFrame(DAOFactory.getTeaDAOInstance().getTeaByAccount("aaa@qq.com"));
    }
}
