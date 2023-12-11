package ru.ssau.tk.oop.practice.ui;

import ru.ssau.tk.oop.practice.functions.*;

import ru.ssau.tk.oop.practice.functions.factory.*;

import ru.ssau.tk.oop.practice.operations.*;

import javax.swing.*;

import javax.swing.table.*;

import java.awt.*;

import java.awt.event.*;

import java.util.*;

public class DifOperationWindow extends JDialog {
    private TabulatedFunction selectedFunction;
    private TabulatedFunction derivative;
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

    public DifOperationWindow(JFrame owner, LinkedList<TabulatedFunction> lst, boolean fct_type) { //
        super(owner, "Differentiation", true);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        LinkedList<TabulatedFunction> listOfFunctions = lst; //

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
        functionsComboBox.setPreferredSize(new Dimension(350, 26));
        functionsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFunction = map_Of_Functions.get(functionsComboBox.getSelectedItem());
            }
        });

        JButton difOperationButton = new JButton("Differentiate");

        functionsPanel.add(functionsLabel);
        functionsPanel.add(functionsComboBox);
        functionsPanel.add(difOperationButton);

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

        final int[] selectedRow = {-1};

        resultTable.getSelectionModel().addListSelectionListener(e -> {
            int newRow = resultTable.getSelectedRow();
            if (newRow != -1 && newRow != selectedRow[0]) {
                selectedRow[0] = newRow;
                StringBuilder functionString = new StringBuilder();
                functionString.append(derivative.getClass().getSimpleName());
                functionString.append(" size = ");
                functionString.append(derivative.getCount());
                functionString.append("\n");
                for (int i = 0; i < derivative.getCount(); i++) {
                    functionString.append("[");
                    functionString.append(derivative.getX(i));
                    functionString.append("; ");
                    functionString.append(derivative.getY(i));
                    functionString.append("]\n");
                }
                derivativeDescriptionTextArea.setText(functionString.toString());
            }
        });

        difOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!factory_type) {
                    factory = new ArrayTabulatedFunctionFactory();
                }
                else {
                    factory = new LinkedListTabulatedFunctionFactory();
                }
                differentialOperator = new TabulatedDifferentialOperator();
                differentialOperator.setFactory(factory);
                derivative = differentialOperator.derive(selectedFunction);
                updateTable(derivative, "Derivative Tabulated Function");
            }
        });

        add(functionsPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.CENTER);
        add(derivativeDescriptionPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void updateTable(TabulatedFunction function, String name) {
        resultTableModel.addRow(new String[]{name, function.getClass().getSimpleName()});
    }
}
