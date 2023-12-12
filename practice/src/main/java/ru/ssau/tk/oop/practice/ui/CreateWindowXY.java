package ru.ssau.tk.oop.practice.ui;

import javax.swing.*;

import javax.swing.table.*;

import java.awt.*;

import java.awt.event.*;

import ru.ssau.tk.oop.practice.exceptions.*;

import ru.ssau.tk.oop.practice.functions.*;

import ru.ssau.tk.oop.practice.functions.factory.*;

public class CreateWindowXY extends JDialog {

    private static TabulatedFunction function;
    private boolean factory_type;
    private static TabulatedFunctionFactory factory;
    private static boolean status;
    private JTable pointsTable;
    private JTextField numPointsField;

    public static TabulatedFunction getTabulatedFunction() {
      return function;
    };

    public static boolean getXYStatus() {
        return status;
    }

    public void setXYStatus(boolean status) {
        this.status = status;
    }

    public CreateWindowXY(JFrame owner, boolean fct_type) {
        super(owner, "Create Tabulated Function", true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        setXYStatus(false);

        factory_type = fct_type;

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel numPointsLabel = new JLabel("Number of function points:");
        inputPanel.add(numPointsLabel);

        numPointsField = new JTextField(6);
        inputPanel.add(numPointsField);

        JButton createTableButton = new JButton("Create Table");
        inputPanel.add(createTableButton);

        createTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) pointsTable.getModel();
                model.setRowCount(0);
                model.setColumnIdentifiers(new String[]{"X", "Y"});
                try {
                    int numPoints = Integer.parseInt(numPointsField.getText());
                    if (numPoints > 2) {
                        for (int i = 0; i < numPoints; i++) {
                            model.addRow(new Object[]{"", ""});
                        }
                        pointsTable.setModel(model);
                    } else {
                        NoticeJDialogWindow noticeJDialogWindow = new NoticeJDialogWindow(CreateWindowXY.this, 3);
                    }
                } catch (NumberFormatException er) {
                    NoticeJDialogWindow noticeJDialogWindow = new NoticeJDialogWindow(CreateWindowXY.this, 6);
                }
            }
        });

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        pointsTable = new JTable(0, 2);

        JScrollPane scrollPane = new JScrollPane(pointsTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JButton createFunctionButton = new JButton("Create Function");

        createFunctionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setXYStatus(true);
                DefaultTableModel model = (DefaultTableModel) pointsTable.getModel();
                double[] xValues = new double[pointsTable.getRowCount()];
                double[] yValues = new double[pointsTable.getRowCount()];
                try {
                    for (int i = 0; i < model.getRowCount(); i++) {
                        xValues[i] = Double.parseDouble(model.getValueAt(i, 0).toString());
                        yValues[i] = Double.parseDouble(model.getValueAt(i, 1).toString());
                    }
                    if (!factory_type) {
                        factory = new ArrayTabulatedFunctionFactory();
                    }
                    else {
                        factory = new LinkedListTabulatedFunctionFactory();
                    }
                    function = factory.create(xValues, yValues);
                    setVisible(false);
                    NoticeJDialogWindow noticeJDialogWindow = new NoticeJDialogWindow(CreateWindowXY.this, 0);
                } catch (NumberFormatException er) {
                    setXYStatus(false);
                    NoticeJDialogWindow noticeJDialogWindow = new NoticeJDialogWindow(CreateWindowXY.this, 6);
                } catch (ArrayIsNotSortedException er) {
                    setXYStatus(false);
                    NoticeJDialogWindow noticeJDialogWindow = new NoticeJDialogWindow(CreateWindowXY.this, 7);
                }
            }
        });

        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(createFunctionButton, BorderLayout.SOUTH);

        renameColumns(pointsTable, new String[]{"X", "Y"});

        add(inputPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void renameColumns(JTable table, String[] newColumnNames) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < newColumnNames.length; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setHeaderValue(newColumnNames[i]);
        }
        table.getTableHeader().repaint();
    }
}