import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickCounterApp {
    private JFrame frame;
    private JLabel label;
    private JButton button;
    private int clickCount = 0;

    public ClickCounterApp() {
        // Create the main frame
        frame = new JFrame("Click Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        // Create the label
        label = new JLabel("Click Count: 0");

        // Create the button
        button = new JButton("Click me!");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickCount++;
                label.setText("Click Count: " + clickCount);
            }
        });

        // Add components to the frame
        frame.add(label);
        frame.add(button);

        // Display the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClickCounterApp();
            }
        });
    }
}
