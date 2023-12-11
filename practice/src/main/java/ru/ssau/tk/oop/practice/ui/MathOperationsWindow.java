package ru.ssau.tk.oop.practice.ui;

import javafx.scene.control.Tab;
import ru.ssau.tk.oop.practice.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.oop.practice.functions.TabulatedFunction;
import ru.ssau.tk.oop.practice.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.oop.practice.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.oop.practice.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.oop.practice.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

import static ru.ssau.tk.oop.practice.io.FunctionsIO.writeTabulatedFunction;

public class MathOperationsWindow extends JDialog {
    private TabulatedFunction selectedFunction1;
    private TabulatedFunction selectedFunction2;
    private TabulatedFunction tempFunction;
    private TabulatedFunction resultFunction;
    private LinkedList<TabulatedFunction> list_of_results;
    private boolean factory_type;
    private TabulatedFunctionFactory factory;
    private String operationName;
    private TabulatedFunctionOperationService operation;
    private HashMap<String, TabulatedFunction> map_Of_Functions1 = new HashMap<>();
    private HashMap<String, TabulatedFunction> map_Of_Functions2 = new HashMap<>();
    private DefaultTableModel operationResultTableModel;
    private JTable operationResultTable;

    private class NonEditableTableModel extends DefaultTableModel {

        public NonEditableTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    public MathOperationsWindow(JFrame owner, LinkedList<TabulatedFunction> lst, boolean fct_type) {
        super(owner, "Operations", true);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        LinkedList<TabulatedFunction> listOfFunctions = lst;

        factory_type = fct_type;

        JPanel setPanel = new JPanel();
        setPanel.setLayout(new BorderLayout());

        JPanel function1Panel = new JPanel();
        function1Panel.setLayout(new FlowLayout());

        JLabel selectedFunction1Label = new JLabel("Select 1st Function and press OK:");
        function1Panel.add(selectedFunction1Label);

        JComboBox<String> selectedFunction1ComboBox;

        String[] selectedFunction1Names = new String[listOfFunctions.size()];

        for (int i = 0; i < listOfFunctions.size(); i++) {
            selectedFunction1Names[i] = listOfFunctions.get(i).toString();
        }
        for (int i = 0; i < listOfFunctions.size(); i++) {
            map_Of_Functions1.put(selectedFunction1Names[i], listOfFunctions.get(i));
        }

        selectedFunction1ComboBox = new JComboBox<>(selectedFunction1Names);
        selectedFunction1ComboBox.setPreferredSize(new Dimension(300, 26));

        selectedFunction1ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tempFunction = map_Of_Functions1.get(selectedFunction1ComboBox.getSelectedItem());
            }
        });

        function1Panel.add(selectedFunction1ComboBox);

        JButton ok1Button = new JButton("OK");

        function1Panel.add(ok1Button);

        ok1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFunction1 = tempFunction;
            }
        });

        setPanel.add(function1Panel, BorderLayout.NORTH);

        JPanel function2Panel = new JPanel();
        function2Panel.setLayout(new FlowLayout());

        JLabel selectedFunction2Label = new JLabel("Select 2nd Function and press OK:");
        function2Panel.add(selectedFunction2Label);

        JComboBox<String> selectedFunction2ComboBox;

        String[] selectedFunction2Names = new String[listOfFunctions.size()];

        for (int i = 0; i < listOfFunctions.size(); i++) {
            selectedFunction2Names[i] = listOfFunctions.get(i).toString();
        }
        for (int i = 0; i < listOfFunctions.size(); i++) {
            map_Of_Functions2.put(selectedFunction2Names[i], listOfFunctions.get(i));
        }

        selectedFunction2ComboBox = new JComboBox<>(selectedFunction2Names);
        selectedFunction2ComboBox.setPreferredSize(new Dimension(300, 26));

        selectedFunction2ComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tempFunction = map_Of_Functions2.get(selectedFunction2ComboBox.getSelectedItem());
            }
        });

        function2Panel.add(selectedFunction2ComboBox);

        JButton ok2Button = new JButton("OK");

        function2Panel.add(ok2Button);

        ok2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFunction2 = tempFunction;
            }
        });

        setPanel.add(function2Panel, BorderLayout.CENTER);

        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(new FlowLayout());

        JLabel operationLabel = new JLabel("Select Operation and press Apply:");
        operationPanel.add(operationLabel);

        JComboBox<String> operationComboBox;

        String[] operationNames = {"Addition", "Subtraction", "Multiplication", "Division"};

        operationComboBox = new JComboBox<>(operationNames);
        operationComboBox.setPreferredSize(new Dimension(120, 26));
        operationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operationName = operationComboBox.getSelectedItem().toString();
            }
        });

        operationPanel.add(operationComboBox);

        JButton applyButton = new JButton("Apply");
        operationPanel.add(applyButton);

        JButton saveButton = new JButton("Save");
        operationPanel.add(saveButton);

        setPanel.add(operationPanel, BorderLayout.SOUTH);

        JPanel operationResultPanel = new JPanel();
        operationResultPanel.setLayout(new BorderLayout());

        operationResultTableModel = new NonEditableTableModel(new String[]{"Function Name", "Type Of Fabric"}, 0);
        operationResultTable = new JTable(operationResultTableModel);
        operationResultPanel.add(operationResultTable);

        JScrollPane operationScrollPane = new JScrollPane(operationResultTable);
        operationResultPanel.add(operationScrollPane);

        JPanel operationDescriptionPanel = new JPanel();
        operationDescriptionPanel.setLayout(new BorderLayout());

        JTextArea operationDescriptionTextArea = new JTextArea();
        operationDescriptionTextArea.setEditable(false);
        operationDescriptionTextArea.setLineWrap(true);
        operationDescriptionTextArea.setWrapStyleWord(true);
        operationDescriptionPanel.add(operationDescriptionTextArea, BorderLayout.CENTER);

        JScrollPane operationDescriptionScrollPane = new JScrollPane(operationDescriptionTextArea);
        operationDescriptionPanel.add(operationDescriptionScrollPane, BorderLayout.CENTER);
        operationDescriptionTextArea.setRows(operationDescriptionTextArea.getLineCount());

        list_of_results = new LinkedList<TabulatedFunction>();

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!factory_type) {
                    factory = new ArrayTabulatedFunctionFactory();
                } else {
                    factory = new LinkedListTabulatedFunctionFactory();
                }
                try {
                    operation = new TabulatedFunctionOperationService(factory);
                    if (Objects.equals(operationName, operationNames[0])) {
                        resultFunction = operation.Addition(selectedFunction1, selectedFunction2);
                    }
                    if (Objects.equals(operationName, operationNames[1])) {
                        resultFunction = operation.Subtraction(selectedFunction1, selectedFunction2);
                    }
                    if (Objects.equals(operationName, operationNames[2])) {
                        resultFunction = operation.Multiplication(selectedFunction1, selectedFunction2);
                    }
                    if (Objects.equals(operationName, operationNames[3])) {
                        resultFunction = operation.Division(selectedFunction1, selectedFunction2);
                    }
                    list_of_results.add(resultFunction);
                    updateTable(resultFunction, "Tabulated Function (Result of " + operationName + ")");
                } catch (ArithmeticException ex) {
                    ErrorDivZeroWindow errorDivZeroWindow = new ErrorDivZeroWindow(MathOperationsWindow.this);
                } catch (NullPointerException ex) {
                    ErrorNullPointerWindow nullPointerWindow = new ErrorNullPointerWindow(MathOperationsWindow.this);
                } catch (InconsistentFunctionsException ex) {
                    ErrorLengthWindow errorLengthWindow = new ErrorLengthWindow(MathOperationsWindow.this);
                }
            }
        });

        final int[] selectedRow = {-1};

        operationResultTable.getSelectionModel().addListSelectionListener(e -> {
            int newRow = operationResultTable.getSelectedRow();
            if (newRow != -1 && newRow != selectedRow[0]) {
                selectedRow[0] = newRow;
                TabulatedFunction selectedFunction = list_of_results.get(selectedRow[0]);
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
                operationDescriptionTextArea.setText(functionString.toString());
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = operationResultTable.getSelectedRow();
                if (selectedRow != -1) {
                    JFileChooser fileChooser = new JFileChooser();
                    int returnVal = fileChooser.showSaveDialog(MathOperationsWindow.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        try {
                            writeTabulatedFunction(new BufferedWriter(new FileWriter(file.getAbsolutePath())), resultFunction);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    ErrorCreateJDialogWindow errorWindow = new ErrorCreateJDialogWindow(MathOperationsWindow.this);
                }
            }
        });

        operationDescriptionPanel.setPreferredSize(new Dimension(WIDTH, 100));

        add(setPanel, BorderLayout.NORTH);
        add(operationResultPanel, BorderLayout.CENTER);
        add(operationDescriptionPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void updateTable(TabulatedFunction function, String name) {
        operationResultTableModel.addRow(new String[]{name, function.getClass().getSimpleName()});
    }
}
