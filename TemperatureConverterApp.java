import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Temperature Converter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);

            JPanel panel = new JPanel();
            frame.add(panel);
            panel.setLayout(new GridLayout(4, 2));

            JLabel inputLabel = new JLabel("Enter temperature:");
            JTextField inputField = new JTextField(10);

            JLabel resultLabel = new JLabel("Result:");
            JTextField resultField = new JTextField(10);
            resultField.setEditable(false);

            JLabel sourceUnitLabel = new JLabel("Source unit:");
            JComboBox<String> sourceUnitCombo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});

            JLabel targetUnitLabel = new JLabel("Target unit:");
            JComboBox<String> targetUnitCombo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit"});

            JButton convertButton = new JButton("Convert");

            panel.add(inputLabel);
            panel.add(inputField);
            panel.add(sourceUnitLabel);
            panel.add(sourceUnitCombo);
            panel.add(targetUnitLabel);
            panel.add(targetUnitCombo);
            panel.add(resultLabel);
            panel.add(resultField);
            panel.add(convertButton);

            convertButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        double temperature = Double.parseDouble(inputField.getText());
                        String sourceUnit = sourceUnitCombo.getSelectedItem().toString();
                        String targetUnit = targetUnitCombo.getSelectedItem().toString();

                        double result;
                        if (sourceUnit.equals("Celsius") && targetUnit.equals("Fahrenheit")) {
                            result = (temperature * 9 / 5) + 32;
                        } else if (sourceUnit.equals("Fahrenheit") && targetUnit.equals("Celsius")) {
                            result = (temperature - 32) * 5 / 9;
                        } else {
                            result = temperature; // No conversion needed
                        }

                        resultField.setText(String.format("%.2f", result));
                    } catch (NumberFormatException ex) {
                        resultField.setText("Invalid input");
                    }
                }
            });

            frame.setVisible(true);
        });
    }
}
