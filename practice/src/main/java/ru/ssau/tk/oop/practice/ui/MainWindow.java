package ru.ssau.tk.oop.practice.ui;

import javax.swing.*;

import javax.swing.table.*;

import java.awt.*;

import java.awt.event.*;

import java.io.*;

import java.util.*;

import static ru.ssau.tk.oop.practice.io.FunctionsIO.readTabulatedFunction;

import static ru.ssau.tk.oop.practice.io.FunctionsIO.writeTabulatedFunction;

import ru.ssau.tk.oop.practice.exceptions.*;

import ru.ssau.tk.oop.practice.functions.*;

import ru.ssau.tk.oop.practice.functions.factory.*;

public class MainWindow extends JFrame {
    private JFrame mainFrame;
    private static LinkedList<TabulatedFunction> list_of_functions = new LinkedList<TabulatedFunction>(); //
    private DefaultTableModel functionsTableModel;
    private JTable functionsTable;
    private TabulatedFunctionFactory factory;

    private class NonEditableTableModel extends DefaultTableModel {

        public NonEditableTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    public static LinkedList<TabulatedFunction> getList_of_functions() { //
        return list_of_functions;
    }

    public MainWindow() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Operations with Functions");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());

        JMenuBar mainMenu = new JMenuBar();
        JMenu createButton = new JMenu("Function");
        JMenu operationsButton = new JMenu("Operations");
        JMenu helpButton = new JMenu("Help");

        mainMenu.add(createButton);
        mainMenu.add(operationsButton);
        mainMenu.add(helpButton);

        JMenuItem createTabulatedFunctionXY = new JMenuItem("Create Tabulated Function (xValues, yValues)");
        JMenuItem createTabulatedFunctionMath = new JMenuItem("Create Tabulated Function (MathFunction)");
        JMenuItem loadTabulatedFunction = new JMenuItem("Load Tabulated Function");
        JMenuItem saveTabulatedFunction = new JMenuItem("Save Tabulated Function");
        JMenuItem settings = new JMenuItem("Settings");

        createButton.add(createTabulatedFunctionXY);
        createButton.add(createTabulatedFunctionMath);
        createButton.add(loadTabulatedFunction);
        createButton.add(saveTabulatedFunction);
        helpButton.add(settings);

        JMenuItem mathOperation = new JMenuItem("Math Operations");
        JMenuItem differentiationOperation = new JMenuItem("Differentiation");

        operationsButton.add(mathOperation);
        operationsButton.add(differentiationOperation);

        functionsTableModel = new NonEditableTableModel(new String[]{"Function Name", "Type Of Fabric"}, 0);
        functionsTable = new JTable(functionsTableModel);

        JScrollPane scrollPane = new JScrollPane(functionsTable);

        createTabulatedFunctionXY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateWindowXY create_window_xy = new CreateWindowXY(mainFrame, SettingsWindow.getTypeOfFabric());
                if (create_window_xy.getXYStatus()) {
                    list_of_functions.add(create_window_xy.getTabulatedFunction());
                    updateTable(create_window_xy.getTabulatedFunction(), "Tabulated Function");
                }
            }
        });

        createTabulatedFunctionMath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateWindowMath create_window_math = new CreateWindowMath(mainFrame, SettingsWindow.getTypeOfFabric());
                if (create_window_math.getMathStatus()) {
                    list_of_functions.add(create_window_math.getMathFunction());
                    updateTable(create_window_math.getMathFunction(), create_window_math.getFunctionName());
                }
            }
        });

        loadTabulatedFunction.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(mainFrame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!SettingsWindow.getTypeOfFabric()) {
                    factory = new ArrayTabulatedFunctionFactory();
                } else {
                    factory = new LinkedListTabulatedFunctionFactory();
                }
                try {
                    list_of_functions.add(readTabulatedFunction(new BufferedReader(new FileReader(file.getAbsolutePath())), factory));
                    updateTable(readTabulatedFunction(new BufferedReader(new FileReader(file.getAbsolutePath())), factory), "Tabulated Function");
                    NoticeJFrameWindow noticeJFrameWindow = new NoticeJFrameWindow(mainFrame, 0);
                } catch (IOException ex) {
                    NoticeJFrameWindow noticeJFrameWindow = new NoticeJFrameWindow(mainFrame, 4);
                } catch (ArrayIsNotSortedException ex) {
                    NoticeJFrameWindow noticeJFrameWindow = new NoticeJFrameWindow(mainFrame, 7);
                }
            }
        });

        saveTabulatedFunction.addActionListener(e -> {
            int selectedRow = functionsTable.getSelectedRow();
            if (selectedRow != -1) {
                TabulatedFunction selectedFunction = list_of_functions.get(selectedRow);
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showSaveDialog(mainFrame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        writeTabulatedFunction(new BufferedWriter(new FileWriter(file.getAbsolutePath())), selectedFunction);
                        NoticeJFrameWindow noticeJFrameWindow = new NoticeJFrameWindow(mainFrame, 0);
                    } catch (IOException ex) {
                        NoticeJFrameWindow noticeJFrameWindow = new NoticeJFrameWindow(mainFrame, 4);
                    }
                }
            } else {
                NoticeJFrameWindow noticeJFrameWindow = new NoticeJFrameWindow(mainFrame, 1);
            }
        });

        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsWindow(mainFrame);
            }
        });


        differentiationOperation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DifOperationWindow(mainFrame, MainWindow.getList_of_functions(), SettingsWindow.getTypeOfFabric());
            }
        });

        mathOperation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MathOperationsWindow(mainFrame, MainWindow.getList_of_functions(), SettingsWindow.getTypeOfFabric());
            }
        });

        JPanel functionDescriptionPanel = new JPanel(new BorderLayout());
        JTextArea functionDescriptionTextArea = new JTextArea();
        functionDescriptionTextArea.setEditable(false);
        functionDescriptionTextArea.setLineWrap(true);
        functionDescriptionTextArea.setWrapStyleWord(true);

        functionDescriptionPanel.add(functionDescriptionTextArea, BorderLayout.CENTER);

        JScrollPane functionDescriptionScrollPane = new JScrollPane(functionDescriptionTextArea);
        functionDescriptionPanel.add(functionDescriptionScrollPane, BorderLayout.CENTER);
        functionDescriptionTextArea.setRows(functionDescriptionTextArea.getLineCount());

        final int[] selectedRow = {-1};

        functionsTable.getSelectionModel().addListSelectionListener(e -> {
            int newRow = functionsTable.getSelectedRow();
            if (newRow != -1 && newRow != selectedRow[0]) {
                selectedRow[0] = newRow;
                TabulatedFunction selectedFunction = (TabulatedFunction) list_of_functions.get(selectedRow[0]);
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
                functionDescriptionTextArea.setText(functionString.toString());
            }
        });

        functionDescriptionPanel.setPreferredSize(new Dimension(WIDTH, 100));

        mainFrame.getContentPane().add(BorderLayout.NORTH, mainMenu);
        mainFrame.add(scrollPane, BorderLayout.CENTER);
        mainFrame.add(functionDescriptionPanel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }

    private void updateTable(TabulatedFunction function, String name) {
        functionsTableModel.addRow(new String[]{name, function.getClass().getSimpleName()});
    }

    public static void main(String[] args) {
        MainWindow main_window = new MainWindow();
    }
}