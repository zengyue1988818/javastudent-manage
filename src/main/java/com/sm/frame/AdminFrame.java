package com.sm.frame;

import javax.swing.*;

public class AdminFrame extends JFrame {
    private JPanel rootPanel;

    public AdminFrame() {
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}