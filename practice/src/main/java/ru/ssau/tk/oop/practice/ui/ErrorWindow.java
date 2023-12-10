package ru.ssau.tk.oop.practice.ui;

import javax.swing.*;

import java.awt.*;

public class ErrorWindow extends JDialog {
    public ErrorWindow(JDialog owner) {
        super(owner, "", true);
        setSize(200, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel noticePanel = new JPanel();
        noticePanel.setLayout(new FlowLayout());

        JLabel noticeWindow = new JLabel("Error!");
        noticePanel.add(noticeWindow);

        JPanel okPanel = new JPanel();
        okPanel.setLayout(new FlowLayout());

        JButton okButton = new JButton("OK");
        okPanel.add(okButton);

        okButton.addActionListener(event -> setVisible(false));

        add(noticePanel, BorderLayout.NORTH);
        add(okPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}