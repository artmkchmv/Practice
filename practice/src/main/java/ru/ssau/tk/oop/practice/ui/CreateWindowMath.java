package ru.ssau.tk.oop.practice.ui;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

import java.util.*;

import ru.ssau.tk.oop.practice.functions.*;
import ru.ssau.tk.oop.practice.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.oop.practice.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.oop.practice.functions.factory.TabulatedFunctionFactory;

public class CreateWindowMath extends JDialog {

    private static TabulatedFunction function; //
    private boolean factory_type;
    private static TabulatedFunctionFactory factory;
    private static String functionName;
    private static boolean status;
    private HashMap<String, MathFunction> mapOfFunctions; //

    public static TabulatedFunction getMathFunction() { //
        return function;
    };

    public static String getFunctionName() {
        return functionName;
    }

    public static boolean getMathStatus() {
        return status;
    }

    public void setMathStatus(boolean status) {
        this.status = status;
    }

    public CreateWindowMath(JFrame owner, boolean fct_type) {
        super(owner, "Create Tabulated Function", true);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        setMathStatus(false);

        factory_type = fct_type;

        JPanel functions = new JPanel();
        functions.setLayout(new FlowLayout());

        JLabel typeOfFunctionsLabel = new JLabel("Select Type of Function:");

        String[] functions_arr = {
                "Unit Function (f(x) = 1)",
                "Zero Function (f(x) = 0)",
                "Identify Function (f(x) = x)",
                "Quadratic Function (f(x) = x²)",
                "Cosine Function (f(x) = cos(x))",
                "Tangent Function (f(x) = tan(x))"
        };

        mapOfFunctions = new HashMap<>();
        mapOfFunctions.put("Unit Function (f(x) = 1)", new UnitFunction());
        mapOfFunctions.put("Zero Function (f(x) = 0)", new ZeroFunction());
        mapOfFunctions.put("Identify Function (f(x) = x)", new IdentifyFunction());
        mapOfFunctions.put("Quadratic Function (f(x) = x²)", new SqrFunction());
        mapOfFunctions.put("Cosine Function (f(x) = cos(x))", new CosFunction());
        mapOfFunctions.put("Tangent Function (f(x) = tan(x))", new TgFunction());

        JComboBox arrayOfFunctions = new JComboBox(functions_arr);

        functions.add(typeOfFunctionsLabel);
        functions.add(arrayOfFunctions);

        JPanel values = new JPanel();
        values.setLayout(new FlowLayout());

        JLabel xFromLabel = new JLabel("Enter left bound of x:");
        values.add(xFromLabel);

        JTextField xFromTextField = new JTextField(5);
        values.add(xFromTextField);

        JLabel xToLabel = new JLabel("Enter right bound of x:");
        values.add(xToLabel);

        JTextField xToTextField = new JTextField(5);
        values.add(xToTextField);

        JLabel countLabel = new JLabel("Enter number of points:");
        values.add(countLabel);

        JTextField countTextField = new JTextField(5);
        values.add(countTextField);

        JPanel createPanel = new JPanel();

        JButton createButton = new JButton("Create Function");
        createPanel.add(createButton);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMathStatus(true);
                functionName = arrayOfFunctions.getSelectedItem().toString();
                MathFunction selectedFunction = mapOfFunctions.get(arrayOfFunctions.getSelectedItem());
                double xFrom = Double.parseDouble(xFromTextField.getText());
                double xTo = Double.parseDouble(xToTextField.getText());
                int count = Integer.parseInt(countTextField.getText());
                if (!factory_type) {
                    factory = new ArrayTabulatedFunctionFactory();
                }
                else {
                    factory = new LinkedListTabulatedFunctionFactory();
                }
                function = factory.create(selectedFunction, xFrom, xTo, count);
                setVisible(false);
                NoticeWindow notice_window = new NoticeWindow(CreateWindowMath.this);
            }
        });

        add(functions, BorderLayout.NORTH);
        add(values, BorderLayout.CENTER);
        add(createPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}