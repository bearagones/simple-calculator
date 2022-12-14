package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.exceptions.InvalidTokenException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField expressionTextField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] buttonText = {
        "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
        "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[buttonText.length];

    public static void main(String argv[]) {
        new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.expressionTextField.setPreferredSize(new Dimension(600, 50));
        this.expressionTextField.setFont(new Font("Courier", Font.BOLD, 28));

        add(expressionTextField, BorderLayout.NORTH);
        expressionTextField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button tempButtonReference;
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            tempButtonReference = new Button(buttonText[i]);
            tempButtonReference.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = tempButtonReference;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This function is triggered anytime a button is pressed
     * on our Calculator GUI.
     * @param actionEventObject Event object generated when a
     *                    button is pressed.
     */
    public void actionPerformed(ActionEvent actionEventObject) {
        String buttonPressed = actionEventObject.getActionCommand();
        Object buttonSource = actionEventObject.getSource();
        String oldText = this.expressionTextField.getText();

        // for-loop to traverse through the buttons array
        for(int i = 0; i < buttons.length; i++) {

            // if button pressed is an integer or operator, show button on screen
            if (buttonSource == buttons[i] && i!=14 && i!=18 && i!=19) {
                expressionTextField.setText(oldText + buttonPressed);

            // if button pressed is "=", evaluate the expression
            } else if(buttonSource == buttons[14]) {
                Evaluator evaluator = new Evaluator();
                try {
                    expressionTextField.setText(String.valueOf(evaluator.evaluateExpression(oldText)));
                } catch (InvalidTokenException e) {
                    e.printStackTrace();
                }

            // if button pressed is "C", remove the last integer or operator
            } else if (buttonSource == buttons[18]) {
                if (!oldText.isEmpty()) {
                    expressionTextField.setText(oldText.substring(0, oldText.length()-1));
                }

            // if button pressed is "CE", clear the text entirely
            } else if (buttonSource == buttons[19]) {
                expressionTextField.setText("");
            }
        }
    }
}

