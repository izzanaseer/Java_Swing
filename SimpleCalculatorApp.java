import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculatorApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simple Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 400);

            JPanel panel = new JPanel();
            frame.add(panel);
            panel.setLayout(new GridLayout(5, 4));

            JTextField textField = new JTextField();
            panel.add(textField);

            String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
            };

            for (String label : buttonLabels) {
                JButton button = new JButton(label);
                panel.add(button);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String command = e.getActionCommand();
                        if (command.equals("C")) {
                            textField.setText("");
                        } else if (command.equals("=")) {
                            try {
                                String expression = textField.getText();
                                double result = evaluateExpression(expression);
                                textField.setText(String.valueOf(result));
                            } catch (Exception ex) {
                                textField.setText("Error");
                            }
                        } else {
                            textField.setText(textField.getText() + command);
                        }
                    }
                });
            }

            frame.setVisible(true);
        });
    }

    private static double evaluateExpression(String expression) {
        return 0;
    }
}
