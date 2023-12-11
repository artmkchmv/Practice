package ru.ssau.tk.oop.practice.ui;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class SettingsWindow extends JDialog {
    private static boolean type_of_fabric;

    public static boolean getTypeOfFabric() {
        return type_of_fabric;
    }

    public void setTypeOfFabric(boolean type_of_fabric) {
        SettingsWindow.type_of_fabric = type_of_fabric;
    }
    public SettingsWindow(JFrame owner) {
        super(owner, "Settings", true);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Choose Type of Factory:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JRadioButton arrayButton = new JRadioButton("Array", false);
        arrayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JRadioButton linkedListButton = new JRadioButton("Linked List", true);
        linkedListButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonGroup group = new ButtonGroup();
        group.add(arrayButton);
        group.add(linkedListButton);

        arrayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(arrayButton.isSelected()) {
                    type_of_fabric = false;
                }
                NoticeWindow notice_window = new NoticeWindow(SettingsWindow.this);
            }
        });

        linkedListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(linkedListButton.isSelected()) {
                    type_of_fabric = true;
                }
                NoticeWindow notice_window = new NoticeWindow(SettingsWindow.this);
            }
        });

        panel.add(label);
        panel.add(arrayButton);
        panel.add(linkedListButton);

        add(panel, BorderLayout.CENTER);

        arrayButton.setSelected(!type_of_fabric);
        linkedListButton.setSelected(type_of_fabric);

        setVisible(true);
    }
}