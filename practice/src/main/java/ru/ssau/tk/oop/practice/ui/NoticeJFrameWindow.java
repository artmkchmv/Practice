package ru.ssau.tk.oop.practice.ui;

import javax.swing.*;

import java.awt.*;

public class NoticeJFrameWindow extends JDialog {

    public NoticeJFrameWindow(JFrame owner, int ex) {
        super(owner, "", true);
        setSize(200, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        String[] errorMessages = {
                "Successfully!", //0
                "Function not found!", //1
                "Division by zero!", //2
                "Invalid value!", //3
                "Input/Output error!", //4
                "Different lengths!", //5
                "Invalid character!", //6
                "Table not sorted!" //7
        };

        JPanel noticePanel = new JPanel();
        noticePanel.setLayout(new FlowLayout());

        JLabel noticeWindow = new JLabel(errorMessages[ex]);
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