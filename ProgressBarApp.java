import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressBarApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Progress Bar Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 150);

            JPanel panel = new JPanel();
            frame.add(panel);
            panel.setLayout(new FlowLayout());

            JProgressBar progressBar = new JProgressBar(0, 100);
            panel.add(progressBar);

            JButton startButton = new JButton("Start");
            panel.add(startButton);

            JButton resetButton = new JButton("Reset");
            panel.add(resetButton);

            Timer timer = new Timer(100, new ActionListener() {
                private int progress = 0;

                public void actionPerformed(ActionEvent e) {
                    if (progress < 100) {
                        progress++;
                        progressBar.setValue(progress);
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });

            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    progressBar.setValue(0);
                    timer.start();
                }
            });

            resetButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    progressBar.setValue(0);
                    timer.stop();
                }
            });

            frame.setVisible(true);
        });
    }
}
