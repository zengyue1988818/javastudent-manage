package com.sm.frame;

import com.sm.entity.ScoreVO;
import com.sm.entity.Tea;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TeacherMainFrame extends JFrame {
    private ImgPanel rootPanel;
    private JButton 院系公报Button;
    private JButton 老师简介Button;
    private JButton 学生成绩Button;
    private JPanel centerPanel;
    private JPanel teacherPanel;
    private JPanel studentPanel;
    private JPanel ScorePanel;
    private JPanel tablePanel;
    private JPanel infoPanel;
    private JPanel tabelPanel;
    private Font font;
    public TeacherMainFrame(Tea data) {
        font = new Font("微软雅黑",Font.BOLD,20);
        //设置需要的背景图片
        rootPanel.setFileName("allbg.jpg");
        //组件重绘
        rootPanel.repaint();
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





    public static void main(String[] args) throws Exception {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new TeacherMainFrame(DAOFactory.getTeaDAOInstance().getTeaByAccount("aaa@qq.com"));
    }
}
