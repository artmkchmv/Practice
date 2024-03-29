package ru.ssau.tk.oop.practice.ui;

import javax.swing.*;

import javax.swing.table.*;

import java.awt.*;

import java.awt.event.*;

import java.io.*;

import java.util.*;

import com.sun.scenario.effect.impl.state.LinearConvolveKernel;
import ru.ssau.tk.oop.practice.exceptions.*;

import ru.ssau.tk.oop.practice.functions.*;

import ru.ssau.tk.oop.practice.functions.factory.*;

import ru.ssau.tk.oop.practice.operations.*;

import static ru.ssau.tk.oop.practice.io.FunctionsIO.writeTabulatedFunction;

public class DifOperationWindow extends JDialog {
    private TabulatedFunction selectedFunction;
    private TabulatedFunction derivative;
    private LinkedList<TabulatedFunction> list_of_derivative;
    private boolean factory_type;
    private TabulatedFunctionFactory factory;
    private TabulatedDifferentialOperator differentialOperator;
    private HashMap<String, TabulatedFunction> map_Of_Functions = new HashMap<>();
    private DefaultTableModel resultTableModel;
    private JTable resultTable;

    private class NonEditableTableModel extends DefaultTableModel {

        public NonEditableTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    public DifOperationWindow(JFrame owner, LinkedList<TabulatedFunction> lst, boolean fct_type) {
        super(owner, "Differentiation", true);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        LinkedList<TabulatedFunction> listOfFunctions = lst;

        factory_type = fct_type;

        JPanel functionsPanel = new JPanel();
        functionsPanel.setLayout(new FlowLayout());

        JLabel functionsLabel = new JLabel("Select Function:");

        JComboBox<String> functionsComboBox;
        String[] functionNames = new String[listOfFunctions.size()];
        for (int i = 0; i < listOfFunctions.size(); i++) {
            functionNames[i] = listOfFunctions.get(i).toString();
        }
        for (int i = 0; i < listOfFunctions.size(); i++) {
            map_Of_Functions.put(functionNames[i], listOfFunctions.get(i));
        }

        functionsComboBox = new JComboBox<>(functionNames);
        functionsComboBox.setPreferredSize(new Dimension(300, 26));
        functionsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFunction = map_Of_Functions.get(functionsComboBox.getSelectedItem());
            }
        });

        JButton difOperationButton = new JButton("Differentiate");

        JButton saveDerivativeButton = new JButton("Save");
        saveDerivativeButton.setPreferredSize(new Dimension(67, 26));

        functionsPanel.add(functionsLabel);
        functionsPanel.add(functionsComboBox);
        functionsPanel.add(difOperationButton);
        functionsPanel.add(saveDerivativeButton);

        JPanel resultPanel = new JPanel(new BorderLayout());

        resultTableModel = new NonEditableTableModel(new String[]{"Function Name", "Type Of Fabric"}, 0);
        resultTable = new JTable(resultTableModel);

        resultPanel.add(resultTable);

        JScrollPane resultScrollPane = new JScrollPane(resultTable);

        resultPanel.add(resultScrollPane);

        JPanel derivativeDescriptionPanel = new JPanel(new BorderLayout());
        derivativeDescriptionPanel.setPreferredSize(new Dimension(WIDTH, 100));

        JTextArea derivativeDescriptionTextArea = new JTextArea();
        derivativeDescriptionTextArea.setEditable(false);
        derivativeDescriptionTextArea.setLineWrap(true);
        derivativeDescriptionTextArea.setWrapStyleWord(true);
        derivativeDescriptionPanel.add(derivativeDescriptionTextArea, BorderLayout.CENTER);

        JScrollPane derivativeDescriptionScrollPane = new JScrollPane(derivativeDescriptionTextArea);
        derivativeDescriptionPanel.add(derivativeDescriptionScrollPane, BorderLayout.CENTER);
        derivativeDescriptionTextArea.setRows(derivativeDescriptionTextArea.getLineCount());

        list_of_derivative = new LinkedList<TabulatedFunction>();

        difOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!factory_type) {
                        factory = new ArrayTabulatedFunctionFactory();
                    } else {
                        factory = new LinkedListTabulatedFunctionFactory();
                    }
                    differentialOperator = new TabulatedDifferentialOperator();
                    differentialOperator.setFactory(factory);
                    derivative = differentialOperator.derive2(selectedFunction);
                    list_of_derivative.add(derivative);
                    updateTable(derivative, "Derivative Tabulated Function");
                } catch (NullPointerException er) {
                    NoticeJDialogWindow noticeJDialogWindow = new NoticeJDialogWindow(DifOperationWindow.this, 1);
                } catch (ArrayIsNotSortedException er) {
                    NoticeJDialogWindow noticeJDialogWindow = new NoticeJDialogWindow(DifOperationWindow.this, 7);
                }
            }
        });

        saveDerivativeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                if (selectedRow != -1) {
                    JFileChooser fileChooser = new JFileChooser();
                    int returnVal = fileChooser.showSaveDialog(DifOperationWindow.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        try {
                            writeTabulatedFunction(new BufferedWriter(new FileWriter(file.getAbsolutePath())), derivative);
                            NoticeJDialogWindow noticeJDialogWindow = new NoticeJDialogWindow(DifOperationWindow.this, 0);
                        } catch (IOException ex) {
                            NoticeJDialogWindow noticeJDialogWindow = new NoticeJDialogWindow(DifOperationWindow.this, 4);
                        }
                    }
                } else {
                    NoticeJDialogWindow noticeJDialogWindow = new NoticeJDialogWindow(DifOperationWindow.this, 1);
                }
            }
        });

        final int[] selectedRow = {-1};

        resultTable.getSelectionModel().addListSelectionListener(e -> {
            int newRow = resultTable.getSelectedRow();
            if (newRow != -1 && newRow != selectedRow[0]) {
                selectedRow[0] = newRow;
                TabulatedFunction selectedFunction = list_of_derivative.get(selectedRow[0]);
                StringBuilder functionString = new StringBuilder();
                functionString.append(selectedFunction.getClass().getSimpleName());
                functionString.append(" size = ");
                functionString.append(selectedFunction.getCount());
                functionString.append("\n");
                for (int i = 0; i < selectedFunction.getCount(); i++) {
                    functionString.append("[");
                    functionString.append(selectedFunction.getX(i));
                    functionString.append("; ");
                    functionString.append(selectedFunction.getY(i));
                    functionString.append("]\n");
                }
                derivativeDescriptionTextArea.setText(functionString.toString());
            }
        });

        derivativeDescriptionPanel.setPreferredSize(new Dimension(WIDTH, 100));

        add(functionsPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
        add(derivativeDescriptionPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void updateTable(TabulatedFunction function, String name) {
        resultTableModel.addRow(new String[]{name, function.getClass().getSimpleName()});
    }
}