package com.sm.frame;

import com.sm.entity.*;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFactory;
import com.sm.ui.ImgPanel;
import com.sm.utils.AliOSSUtil;
import net.coobird.thumbnailator.Thumbnails;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AdminMainFrame extends JFrame{
    private ImgPanel rootPanel;
    private JButton 院系管理Button;
    private JButton 班级管理Button;
    private JButton 学生管理Button;
    private JButton 奖惩管理Button;
    private JPanel centerPanel;
    private JPanel departmentPanel;
    private JPanel classPanel;
    private JPanel studentPanel;
    private JPanel rewardPunishPanel;
    private JPanel topPanel;
    private JButton 新增院系Button;
    private JButton 刷新数据Button;
    private JPanel leftPanel;
    private JTextField deNameField;
    private JButton 选择Button;
    private JButton 新增Button;
    private JPanel contentPanel;
    private JLabel adminNameLabel;
    private JButton 删除院系Button;
    private JLabel logoLabel;
    private JComboBox<Department> depcomboBox;
    private JTextField CClassField;
    private JButton 新增班级Button;
    private JPanel treePanel;
    private JPanel classContentPanel;
    private JPanel stuTopPanel;
    private ImgPanel infoPanel;
    private JComboBox<Department> comboBox1;
    private JComboBox<CClass> comboBox2;
    private JTextField searchField;
    private JButton 搜索Button;
    private JButton 新增学生Button;
    private JButton 批量导入Button;
    private JPanel tablePanel;
    private JLabel stuDepLabel;
    private JLabel stuClassLabel;
    private JLabel stuNameLabel;
    private JLabel stuGenderLabel;
    private JLabel stuBirthdayLabel;
    private JLabel stuIdLabel;
    private JTextField stuAddressTield;
    private JTextField stuPhoneField;
    private JButton 编辑Button;
    private JLabel stuAvatarLael;
    private JButton 初始界面Button;
    private JPanel newPanel;
    private JPanel rewardPanel;
    private JPanel punishPanel;
    private JButton 惩罚Button;
    private JButton 奖励Button;
    private JButton 新增奖励Button;
    private JButton 新增惩罚Button;
    private Admin admin;
    private String uploadFileUrl;
    private File file,toPic;
    private int departmentId = 0;
    private int row;
    private Font font;

    public AdminMainFrame(Admin admin) {
        font = new Font("微软雅黑",Font.BOLD,20);
        //设置需要的背景图片
        rootPanel.setFileName("2.jpg");
        //组件重绘
        rootPanel.repaint();
        this.admin = admin;
        adminNameLabel.setText("管理员:" + admin.getAdminName());
        showDepartments();
//        Date date = new Date();
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        timeLabel.setText(f.format(date));
//        timeLabel.setFont(font);


        //窗体基本属性
        setTitle("管理员界面");
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        //获取centerPanel的布局,获取的是LayoutManager，向下转型为cardLayout
        CardLayout cardLayout = (CardLayout) centerPanel.getLayout();
        CardLayout cardLayout1 = (CardLayout) newPanel.getLayout();



        院系管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card1");
            }
        });

        班级管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card2");
                try {
                    showClassPanel();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        奖惩管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card4");
            }
        });

        学生管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel, "Card3");
                //右侧个人信息面板加入背景图
                infoPanel.setFileName("bg4.jpg");
                infoPanel.repaint();
                //调用显示学生数据的办法
                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectAll();
                showStudentTable(studentList);
                Department tip1 = new Department();
                tip1.setDepartmentName("请选择院系");
                comboBox1.addItem(tip1);
                CClass tip2 = new CClass();
                tip2.setClassName("请选择班级");
                comboBox2.addItem(tip2);
                List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
                for (Department department : departmentList) {
                    comboBox1.addItem(department);
                }
                List<CClass> classList = ServiceFactory.getCClassServiceInstance().selectAll();
                for (CClass cClass : classList) {
                    comboBox2.addItem(cClass);
                }
                comboBox1.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            int index = comboBox1.getSelectedIndex();
                            //排除第一项提示信息
                            if (index != 0) {
                                departmentId = comboBox1.getItemAt(index).getId();
                                //获取院系的学生，显示在表格中
                                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByDepartmentId(departmentId);
                                showStudentTable(studentList);
                                //二级联动
                                List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectByDepartmentId(departmentId);
                                comboBox2.removeAllItems();
                                CClass tip = new CClass();
                                tip.setClassName("请选择班级");
                                comboBox2.addItem(tip);
                                for (CClass cClass : cClassList) {
                                    comboBox2.addItem(cClass);
                                }

                            }
                        }
                    }
                });
                comboBox2.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            int index = comboBox2.getSelectedIndex();
                            if (index != 0) {
                                int classId = comboBox2.getItemAt(index).getId();
                                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByClassId(classId);
                                showStudentTable(studentList);
                            }
                        }
                    }
                });
            }
        });

        depcomboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //得到选中项的索引
                int index = depcomboBox.getSelectedIndex();
                //按照索引取出项，就是一个Department对象，然后取出其id备用
                departmentId = depcomboBox.getItemAt(index).getId();
            }
        });

        新增学生Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFrame(AdminMainFrame.this);
                AdminMainFrame.this.setEnabled(true);
            }
        });

        搜索Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keywords = searchField.getText();
                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectByKeywords(keywords);
                showStudentTable(studentList);
            }
        });

        初始界面Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<StudentVO> studentList = ServiceFactory.getStudentServiceInstance().selectAll();
                showStudentTable(studentList);

                comboBox1.setSelectedIndex(0);
                comboBox2.removeAllItems();
                CClass tip2 = new CClass();
                tip2.setClassName("请选择班级");
                comboBox2.addItem(tip2);
                List<CClass> cClassList = ServiceFactory.getCClassServiceInstance().selectAll();
                for (CClass cClass : cClassList) {
                    comboBox2.addItem(cClass);
                }
                stuAvatarLael.setText("<html><img src='https://zystudent-manage.oss-cn-beijing.aliyuncs.com/img0b209428-2254-4f43-8f26-e7e9f2465d40.jpg'></html>");
                stuNameLabel.setText("未选择");
                stuDepLabel.setText("未选择");
                stuClassLabel.setText("未选择");
                stuNameLabel.setText("未选择");
                stuGenderLabel.setText("未选择");
                stuBirthdayLabel.setText("未选择");
                stuAddressTield.setText("未选择");
                stuPhoneField.setText("未选择");
                searchField.setText("未选择");
            }
        });

        奖励Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout1.show(newPanel,"Card5");
                List<RewardVO> rewardVOList = ServiceFactory.getStudentServiceInstance().selectAllReward();
                showReward(rewardVOList);
            }
        });

        惩罚Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout1.show(newPanel,"Card6");
                List<PunishVO> punishVOList = ServiceFactory.getStudentServiceInstance().selectAllPunish();
                showPunish(punishVOList);
            }

        });

        新增奖励Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddRewardFrame(AdminMainFrame.this);
                AdminMainFrame.this.setEnabled(true);
            }
        });
    }


    public void showStudentTable(List<StudentVO> studentList) {
        tablePanel.removeAll();
        //获得所有学生视图数据
//     studentList = ServiceFactory.getStudentServiceInstance().selectAll();
        //创建表格
        JTable table = new JTable();
        //表格数据模型
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        //表头内容
        model.setColumnIdentifiers(new String[]{"学号", "院系", "班级", "姓名", "性别", "地址", "手机号", "出生日期", "头像"});
        //遍历List,转成Obiect数组
        for (StudentVO student : studentList) {
            Object[] object = new Object[]{student.getId(), student.getDepartmentName(), student.getClassName(),
                    student.getStudentName(), student.getGender(), student.getAddress(), student.getPhone(), student.getBirthday(), student.getAvatar()};
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
        //表格内容选择监听，点击一行，在右面显示这个学生信息

        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem item = new JMenuItem("删除");
        jPopupMenu.add(item);
        table.add(jPopupMenu);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.getSelectedRow();
                stuIdLabel.setText(table.getValueAt(row, 0).toString());
                stuDepLabel.setText(table.getValueAt(row, 1).toString());
                stuClassLabel.setText(table.getValueAt(row, 2).toString());
                stuNameLabel.setText(table.getValueAt(row, 3).toString());
                stuGenderLabel.setText(table.getValueAt(row, 4).toString());
                stuAddressTield.setText(table.getValueAt(row, 5).toString());
                stuPhoneField.setText(table.getValueAt(row, 6).toString());
                stuBirthdayLabel.setText(table.getValueAt(row, 7).toString());
                stuAvatarLael.setText("<html><img src='" + table.getValueAt(row, 8).toString() + "'/></html>");
                //显示编辑按钮
                编辑Button.setVisible(true);
                编辑Button.setText("编辑");
                编辑Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //如果按钮文字是编辑，就激活两个文本框，并将按钮文字设置为保存
                        if (e.getActionCommand().equals("编辑")) {
                            stuAddressTield.setEditable(true);
                            stuAddressTield.setEnabled(true);
                            stuPhoneField.setEditable(true);
                            stuPhoneField.setEnabled(true);
                            编辑Button.setText("保存");
                        }
                        //如果按钮文字为保存，，则通过输出内容创建修改的student对象，调用service进行信息更新
                        if (e.getActionCommand().equals("保存")) {
                            Student student = new Student();
                            student.setId(stuIdLabel.getText());
                            student.setAddress(stuAddressTield.getText());
                            student.setPhone(stuPhoneField.getText());
                            try {
                                int n = ServiceFactory.getStudentServiceInstance().updateStudent(student);
                                if (n == 1) {
                                    //更新表格中的对应行的地址，电话单元格的内容
                                    model.setValueAt(stuAddressTield.getText(), row, 5);
                                    model.setValueAt(stuPhoneField.getText(), row, 6);
                                    //文本框恢复成不可以状态
                                    stuAddressTield.setEditable(false);
                                    stuAddressTield.setEnabled(false);
                                    stuPhoneField.setEditable(false);
                                    stuPhoneField.setEnabled(false);
                                    //按钮文字恢复编辑
                                    编辑Button.setText("编辑");
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
                if (e.getButton() == 3) {
                    jPopupMenu.show(table, e.getX(), e.getY());
                }
            }
        });
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = (String) table.getValueAt(row, 0);
                int choice = JOptionPane.showConfirmDialog(tablePanel, "删除？？？");
                if (choice == 0) {
                    if (row != -1) {
                        model.removeRow(row);
                    }
                    try {
                        ServiceFactory.getStudentServiceInstance().deleteById(id);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        新增院系Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = leftPanel.isVisible();
                if (flag == true) {
                    leftPanel.setVisible(false);
                } else {
                    leftPanel.setVisible(true);
                }
            }
        });

        刷新数据Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDepartments();
            }
        });

        deNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deNameField.setText(" ");
            }
        });

        选择Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("G://image"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    //选中文件
                    file = fileChooser.getSelectedFile();
                    //通过文件创建icon对象
                    Icon icon = new ImageIcon(file.getAbsolutePath());
                    //通过标签显示图片
                    logoLabel.setIcon(icon);
                    //设置标签可见
                    logoLabel.setVisible(true);
                    //将按钮隐藏
                    选择Button.setVisible(false);
                }
            }
        });

        新增Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //上传文件到OSS并返回外链
                uploadFileUrl = AliOSSUtil.ossUpload(file);
                //创建Department对象，并设置相应属性
                Department department = new Department();
                department.setDepartmentName(deNameField.getText().trim());
                department.setLogo(uploadFileUrl);
                //调用service实现新增功能
                int n = ServiceFactory.getDepartmentServiceInstance().addDepartment(department);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系成功");
                    //新增成功后，将侧边栏隐藏
                    leftPanel.setVisible(false);
                    //刷新界面数据
                    showDepartments();
                    //将图片预览标签隐藏
                    logoLabel.setVisible(true);
                    //将选择图片的按钮可见
                    新增Button.setVisible(true);
                    //清空文本框
                    deNameField.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系失败");
                }
            }
        });

        leftPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logoLabel.removeAll();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("G:\\image"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    //选中文件
                    file = fileChooser.getSelectedFile();
                    //指定缩略图大小
                    toPic = fileChooser.getSelectedFile();
                    //此处把图片压成400×500的缩略图
                    try {
                        Thumbnails.of(file).size(230, 230).toFile(toPic);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    //通过文件创建icon对象
                    Icon icon = new ImageIcon(file.getAbsolutePath());
                    //通过标签显示图片
                    logoLabel.setIcon(icon);
                    //设置标签可见
                    logoLabel.setVisible(true);
                }
            }
        });

        新增班级Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CClass cClass = new CClass();
                //创建Department对象，并设置相应属性
                cClass.setClassName(CClassField.getText().trim());
                cClass.setDepartmentId(departmentId);
                //调用service实现新增功能
                int n = ServiceFactory.getCClassServiceInstance().addCClass(cClass);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel, "新增院系成功");
                    try {
                        showClassPanel();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    CClassField.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "新增班级失败");
                }
            }
        });
    }


    private void showClassPanel() throws SQLException{
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        showCombobox(departmentList);
        showTree(departmentList);
        showClasses(departmentList);
    }


    private void showCombobox(List<Department> departmentList) {
        for (Department department : departmentList){
            depcomboBox.addItem(department);
        }
    }


    private void showTree(List<Department>departmentList) throws SQLException{
        treePanel.removeAll();
        //左侧树形结构根节点
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("南工院");
        for (Department department : departmentList){
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(department.getDepartmentName());
            top.add(group);
            List<CClass> cClassList = ServiceFactory.
                    getCClassServiceInstance().selectByDepartmentId(department.getId());
            for (CClass cClass : cClassList){
                int num = ServiceFactory.getStudentServiceInstance().countStudentByClassId(cClass.getId());
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(cClass.getClassName() + "(" + num + "人)");
                group.add(node);
            }
        }
        final  JTree tree = new JTree(top);
        tree.setRowHeight(30);
        tree.setFont(new Font("微软雅黑",Font.PLAIN,20));
        treePanel.add(tree);
        treePanel.revalidate();
    }


    private void showClasses(List<Department> departmentList) {
        classContentPanel.removeAll();
        //右侧流式布局显示
        Font titleFont = new Font("微软雅黑",Font.PLAIN,22);
        for (Department department: departmentList){
            ImgPanel depPanel = new ImgPanel();
            depPanel.setFileName("bg5.jpg");
            depPanel.repaint();
            depPanel.setPreferredSize(new Dimension(350,500));
            depPanel.setLayout(null);
            JLabel depNameLabel = new JLabel(department.getDepartmentName());
            depNameLabel.setFont(titleFont);
            depNameLabel.setBounds(130,15,200,30);
            //获取这个院系的所有班级
            List<CClass> cClassList = null;
            cClassList = ServiceFactory.
                    getCClassServiceInstance().selectByDepartmentId(department.getId());

            //数据模型
            DefaultListModel listModel = new DefaultListModel();
            //遍历班级集合，依次加入数据模型
            for (CClass cClass: cClassList){
                listModel.addElement(cClass);
            }
            //使用数据模型创建一个JList组件
            JList<CClass> jList = new JList<>(listModel);
            //JList 加入滚动面板
            JScrollPane listScrollPane = new JScrollPane(jList);
            listScrollPane.setBounds(90,120,200,260);
            depPanel.add(depNameLabel);
            depPanel.add(listScrollPane);
            classContentPanel.add(depPanel);

            JPopupMenu jPopupMenu = new JPopupMenu();
            JMenuItem item1 = new JMenuItem("修改");
            JMenuItem item2 = new JMenuItem("删除");
            jPopupMenu.add(item1);
            jPopupMenu.add(item2);
            jList.add(jPopupMenu);

            jList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //选中项的下标
                    int index = jList.getSelectedIndex();
                    //点击鼠标右键
                    if (e.getButton()==3){
                        //在鼠标位置弹出菜单
                        jPopupMenu.show(jList,e.getX(),e.getY());
                        //取出选中数据
                        CClass cClass = jList.getModel().getElementAt(index);
                        //对删除菜单项添加监听
                        item2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int choice = JOptionPane.showConfirmDialog(depPanel,"删除？");
                                if (choice==0){
                                    ServiceFactory.getCClassServiceInstance().deleteCClassById(cClass.getId());
                                    listModel.remove(index);
                                    try {
                                        showTree(ServiceFactory.getDepartmentServiceInstance().selectAll());
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
    }


    private void showDepartments() {
        contentPanel.removeAll();
        //从service层获取到所有院系列表
        List <Map> departmentList = ServiceFactory.getDepartmentServiceInstance().selectDepartmentInfo();
        GridLayout gridLayout = new GridLayout(0,4,10,10);
        contentPanel.setLayout(gridLayout);
        for (Map map : departmentList) {
            //给每个院系对象创建一个面板
            JPanel depPanel = new JPanel();
            Department department = (Department) map.get("department");
            int classCount = (int) map.get("classCount");
            int studentCount = (int) map.get("studentCount");
            depPanel.setBackground(new Color(242, 233, 216));
            depPanel.setPreferredSize(new Dimension(300, 300));
            //将院系名称设置给面板标题
            depPanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));
            //新建一个Label用来放置院系logo，并指定大小
            JLabel logoLabel = new JLabel("<html><img src='" + department.getLogo() + "'/></html>");
            logoLabel.setPreferredSize(new Dimension(230,230));
            depPanel.add(logoLabel);
            JLabel infoLabel = new JLabel("班级"+classCount+"个，学生"+studentCount+"人");
            depPanel.add(infoLabel);
            contentPanel.add(depPanel);
            contentPanel.revalidate();
        }
        //从service层获取到所有院系列表
//        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
//        int len = departmentList.size();
//        int row = len % 4 == 0 ? len / 4 : len / 4 + 1;
//        GridLayout gridLayout = new GridLayout(row, 4, 15, 15);
//        contentPanel.setLayout(gridLayout);
//        for (Department department : departmentList) {
//            //给每个院系对象创建一个面板
//            JPanel depPanel = new JPanel();
//            depPanel.setPreferredSize(new Dimension(200, 200));
//            //将院系名称设置给面板标题
//            depPanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));
//            //新建一个Label用来放置院系logo，并指定大小
//            JLabel logoLabel = new JLabel("<html><img src='" + department.getLogo() + "' width=200 height=200/></html>");
//            JButton delBtn = new JButton("删除");
//            delBtn.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    int n = JOptionPane.showConfirmDialog(null, "确定要删除这行数据吗？", "删除警告", JOptionPane.YES_OPTION);
//                    if (n == JOptionPane.YES_OPTION) {
//                        contentPanel.remove(depPanel);
//                        contentPanel.repaint();
//                        ServiceFactory.getDepartmentServiceInstance().deleteDepartment(department.getId());
//                    }
//                }
//            });
//            delBtn.setPreferredSize(new Dimension(100, 50));
//            //图标标签加入院系面板
//            depPanel.add(logoLabel);
//            depPanel.add(delBtn);
//            //院系面板加入主体内容面板
//            contentPanel.add(depPanel);
//            //刷新主体内容面板
//            contentPanel.revalidate();
    }


    private void showReward(List<RewardVO> rewardVOList) {
        GridLayout gridLayout = new GridLayout(1,20,20,20);
        rewardPanel.setBorder(new EmptyBorder(15,10,15,10));
        rewardPanel.setLayout(gridLayout);
        rewardPanel.setBackground(Color.PINK);

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
            //删除学生信息
            JButton dereButton = new JButton("删除");
            dereButton.setBounds(330,5,100,30);
            dereButton.setFont(font);
            jPanel.add(dereButton);
            JButton upreOKButton = new JButton("确定");
            upreOKButton.setBounds(330,130,100,30);
            upreOKButton.setFont(font);
            upreOKButton.setVisible(false);
            jPanel.add(upreOKButton);
            dereButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(null,"是否确认删除？");
                    //判断用户是否点击
                    if (result == JOptionPane.OK_OPTION){
                        //从流式面板移除当前1这个人的布局
                        rewardPanel.remove(jPanel);
                        //删除这行记录
                        try {
                            ServiceFactory.getStudentServiceInstance().deleteById(String.valueOf(rewardVO.getId()));
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        jPanel.revalidate();
                    }
                }
            });
            reArea.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2){
                        reArea.setEditable(true);
                        reArea.setEnabled(true);
                        upreOKButton.setVisible(true);
                        upreOKButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                rewardVO.setId(Integer.valueOf(idLabel1.getText()));
                                rewardVO.setReward(reArea.getText());
                                ServiceFactory.getStudentServiceInstance().upRew(rewardVO);
                                reArea.setEditable(false);
                                reArea.setEnabled(false);
                                upreOKButton.setVisible(false);
                                jPanel.revalidate();
                            }
                        });
                    }
                }
            });
            rewardPanel.add(jPanel);

        }
    }

    private void showPunish(List<PunishVO> punishVOList) {
        GridLayout gridLayout = new GridLayout(1,6,20,20);
        punishPanel.setBorder(new EmptyBorder(15,10,15,10));
        punishPanel.setLayout(gridLayout);
        punishPanel.setBackground(Color.PINK);
        for (PunishVO punishVO : punishVOList){
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
            //删除学生信息
            JButton dereButton = new JButton("删除");
            dereButton.setBounds(330,5,100,30);
            dereButton.setFont(font);
            jPanel1.add(dereButton);
            JButton uppuOKButton = new JButton("确定");
            uppuOKButton.setBounds(330,130,100,30);
            uppuOKButton.setFont(font);
            uppuOKButton.setVisible(false);
            jPanel1.add(uppuOKButton);
            dereButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(null,"是否确认删除？");
                    //判断用户是否点击
                    if (result == JOptionPane.OK_OPTION){
                        //从流式面板移除当前1这个人的布局
                        rewardPanel.remove(jPanel1);
                        //删除这行记录
                        try {
                            ServiceFactory.getStudentServiceInstance().deleteById(String.valueOf(punishVO.getId()));
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        jPanel1.revalidate();
                    }
                }
            });
            puArea.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2){
                        puArea.setEditable(true);
                        puArea.setEnabled(true);
                        uppuOKButton.setVisible(true);
                        uppuOKButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                punishVO.setId(Integer.valueOf(idLabel1.getText()));
                                punishVO.setPunish(puArea.getText());
                                ServiceFactory.getStudentServiceInstance().upPun(punishVO);
                                puArea.setEditable(false);
                                puArea.setEnabled(false);
                                uppuOKButton.setVisible(false);
                                jPanel1.revalidate();
                            }
                        });
                    }
                }
            });
            punishPanel.add(jPanel1);
        }
    }

    public static void main(String[] args) throws Exception{
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
        new AdminMainFrame(DAOFactory.getAdminDAOInstance().getAdminByAccount("aaa@qq.com" ));
    }
}
















