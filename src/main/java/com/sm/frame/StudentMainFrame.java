package com.sm.frame;

import com.sm.entity.*;
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
import java.util.concurrent.ConcurrentHashMap;

public class StudentMainFrame extends JFrame{

    private  Thread threaddA;
    private ImgPanel rootPanel;
    private JPanel mainPanel;
    private JButton 院系公告Button;
    private JButton 我的奖励和惩罚Button;
    private JButton 我的信息Button;
    private JPanel mePanel;
    private JPanel repuPanel;
    private JPanel departmentPanel;
    private JScrollPane departmentScroll;
    private JPanel dePanel;
    private JButton 奖励Button;
    private JButton 惩罚Button;
    private JPanel rapPanel;
    private JPanel rewardPanel;
    private JPanel punishPanel;
    private JPanel tablePanel;
    private JButton 查看我的成绩Button;
    private JLabel studentLabel;
    private JButton 老师建议Button;
    private JButton 老师教育Button;
    private JButton 查看院系Button;
    private JButton 个人成绩Button;
    private JPanel centerPanel;
    private Font font;
    private Stu stu;

    public StudentMainFrame(Stu stu) {
        font = new Font("微软雅黑", Font.BOLD, 20);
        //设置需要的背景图片
        rootPanel.setFileName("allbg.jpg");
        //组件重绘
        rootPanel.repaint();
        this.stu = stu;
        studentLabel.setText("欢迎您："+ stu.getAdminName());
        //窗体基本属性
        setTitle("学生界面");
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //获取centerPanel的布局,获取的是LayoutManager，向下转型为cardLayout
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        CardLayout cardLayout1 = (CardLayout) rapPanel.getLayout();


        院系公告Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Card3");
                showDepartments();
            }
        });
        //学业预警
        int res = JOptionPane.showConfirmDialog(null,
                "是否愿意参加补考", "英语成绩未及格", JOptionPane.YES_NO_OPTION);




        我的奖励和惩罚Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel,"Card2");
            }
        });



        我的信息Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel,"Card1");
                //调用·显示·学生成绩数据的方法
                List<ScoreVO> scoreList = ServiceFactory.getScoreServiceInstance().selectAllScore();
                showStudentTable(scoreList);
            }
        });


        奖励Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout1.show(rapPanel,"Card5");
                 List<RewardVO> rewardVOList = ServiceFactory.getStudentServiceInstance().selectAllReward();
                 showReward(rewardVOList);
            }
        });


        惩罚Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout1.show(rapPanel,"Card6");
                List<PunishVO> punishVOList = ServiceFactory.getStudentServiceInstance().selectAllPunish();
                showPunish(punishVOList);
            }
        });


        查看我的成绩Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScoreFrame(StudentMainFrame.this);
            }
        });
        老师建议Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdviceFrame(StudentMainFrame.this);
            }
        });
    }


    private void showReward(List<RewardVO> rewardVOList) {
        GridLayout gridLayout = new GridLayout(1,6,20,20);
        rewardPanel.setBorder(new EmptyBorder(15,10,15,10));
        rewardPanel.setLayout(gridLayout);
        rewardPanel.setBackground(Color.PINK);
        rewardPanel.removeAll();
        for (RewardVO rewardVO : rewardVOList ){
            //给每个院系创建一个面板
            JPanel jPanel = new JPanel();
            jPanel.setLayout(new GridLayout(10,1));
            JLabel deLabel = new JLabel("院系："+rewardVO.getDepartmentName());
            deLabel.setFont(font);
            jPanel.add(deLabel);
            JLabel claLabel = new JLabel("班级："+rewardVO.getClassName());
            claLabel.setFont(font);
            jPanel.add(claLabel);
            JLabel xhLabel = new JLabel("学号：");
            xhLabel.setFont(font);
            jPanel.add(xhLabel);
            JLabel idLabel1 = new JLabel(rewardVO.getStudentId());
            idLabel1.setFont(font);
            jPanel.add(idLabel1);
            JLabel naLabel = new JLabel("姓名："+rewardVO.getStudentName());
            naLabel.setFont(font);
            jPanel.add(naLabel);
            JLabel reDateLabel = new JLabel("日期：" + rewardVO.getRewardDate());
            reDateLabel.setFont(font);
            jPanel.add(reDateLabel);
            JTextArea reArea = new JTextArea(rewardVO.getReward());
            reArea.setBackground(new Color(236,217,200));
            reArea.setFont(font);
            reArea.setLineWrap(true);
            reArea.setWrapStyleWord(true);
            reArea.setEditable(false);
            reArea.setEnabled(false);
            jPanel.add(reArea);
            rewardPanel.add(jPanel);
        }
    }


    private void showPunish(List<PunishVO> punishVOList) {
        GridLayout gridLayout = new GridLayout(1,6,20,20);
        punishPanel.setBorder(new EmptyBorder(15,10,15,10));
        punishPanel.setLayout(gridLayout);
        punishPanel.setBackground(Color.PINK);
        punishPanel.removeAll();
        for (PunishVO punishVO : punishVOList ){
            //给每个院系创建一个面板
            JPanel jPanel1 = new JPanel();
            jPanel1.setLayout(new GridLayout(10,1));
            JLabel deLabel = new JLabel("院系："+punishVO.getDepartmentName());
            deLabel.setFont(font);
            jPanel1.add(deLabel);
            JLabel claLabel = new JLabel("班级："+punishVO.getClassName());
            claLabel.setFont(font);
            jPanel1.add(claLabel);
            JLabel xhLabel = new JLabel("学号：");
            xhLabel.setFont(font);
            jPanel1.add(xhLabel);
            JLabel idLabel1 = new JLabel(punishVO.getStudentId());
            idLabel1.setFont(font);
            jPanel1.add(idLabel1);
            JLabel naLabel = new JLabel("姓名："+punishVO.getStudentName());
            naLabel.setFont(font);
            jPanel1.add(naLabel);
            JLabel reDateLabel = new JLabel("日期：" + punishVO.getPunishDate());
            reDateLabel.setFont(font);
            jPanel1.add(reDateLabel);
            JTextArea puArea = new JTextArea(punishVO.getPunish());
            puArea.setBackground(new Color(236,217,200));
            puArea.setFont(font);
            puArea.setLineWrap(true);
            puArea.setWrapStyleWord(true);
            puArea.setEditable(false);
            puArea.setEnabled(false);
            jPanel1.add(puArea);
            punishPanel.add(jPanel1);
        }
    }


    private void showDepartments() {
        dePanel.removeAll();
        //从service层获取到所有院系列表
        List <Map> departmentList = ServiceFactory.getDepartmentServiceInstance().selectDepartmentInfo();
        GridLayout gridLayout = new GridLayout(5,2,10,10);
        dePanel.setLayout(gridLayout);
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
            dePanel.add(depPanel);
            dePanel.revalidate();
        }
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

    public static void main(String[] args) throws Exception{
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new StudentMainFrame(DAOFactory.getStuDAOInstance().getStuByAccount("aaa@qq.com"));
    }
}
