package ru.ssau.tk.oop.practice.ui;

import javax.swing.*;

import java.awt.*;

public class ErrorLengthWindow extends JDialog {
    public ErrorLengthWindow(JDialog owner) {
        super(owner, "", true);
        setSize(200, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel errorPanel = new JPanel();
        errorPanel.setLayout(new FlowLayout());

        JLabel errorLabel = new JLabel("Different Lengths!");
        errorPanel.add(errorLabel);

        JPanel okPanel = new JPanel();
        okPanel.setLayout(new FlowLayout());

        JButton okButton = new JButton("OK");
        okPanel.add(okButton);

        okButton.addActionListener(event -> setVisible(false));

        add(errorPanel, BorderLayout.NORTH);
        add(okPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}


