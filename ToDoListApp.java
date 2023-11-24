import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ToDoListApp {
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("To-Do List");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel panel = new JPanel(new BorderLayout());
            frame.add(panel);

            DefaultListModel<String> model = new DefaultListModel<>();
            JList<String> list = new JList<>(model);
            panel.add(new JScrollPane(list), BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            panel.add(buttonPanel, BorderLayout.SOUTH);

            JTextField taskField = new JTextField(20);
            buttonPanel.add(taskField);

            JButton addButton = new JButton("Add");
            buttonPanel.add(addButton);

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String task = taskField.getText();
                    if (!task.isEmpty()) {
                        tasks.add(task);
                        model.addElement(task);
                        taskField.setText("");
                    }
                }
            });

            JButton deleteButton = new JButton("Delete");
            buttonPanel.add(deleteButton);

            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = list.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        tasks.remove(selectedIndex);
                        model.remove(selectedIndex);
                    }
                }
            });

            JButton completeButton = new JButton("Complete");
            buttonPanel.add(completeButton);

            completeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = list.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        String task = tasks.get(selectedIndex);
                        tasks.set(selectedIndex, "[Completed] " + task);
                        model.set(selectedIndex, "[Completed] " + task);
                    }
                }
            });

            frame.setVisible(true);
        });
    }
}
