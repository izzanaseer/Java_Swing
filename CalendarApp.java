import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CalendarApp {
    private static int selectedDay = 0;
    private static List<String> events = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Monthly Calendar");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            JPanel panel = new JPanel(new BorderLayout());
            frame.add(panel);

            JPanel calendarPanel = new JPanel(new GridLayout(7, 7));
            panel.add(calendarPanel, BorderLayout.CENTER);

            JButton[] dayButtons = new JButton[42];
            for (int i = 0; i < 42; i++) {
                dayButtons[i] = new JButton("");
                calendarPanel.add(dayButtons[i]);
                final int day = i;
                dayButtons[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (day > 0 && day <= 31) {
                            selectedDay = day;
                            showEventDialog();
                        }
                    }
                });
            }

            JButton prevButton = new JButton("Previous");
            JButton nextButton = new JButton("Next");
            JPanel navPanel = new JPanel(new FlowLayout());
            navPanel.add(prevButton);
            navPanel.add(nextButton);
            panel.add(navPanel, BorderLayout.NORTH);

            private static void navigateToPreviousMonth() {
                int currentMonth = LocalDate.now().getMonthValue();
                currentMonth--;
            }
            
            private static void navigateToNextMonth() {
                int currentMonth = LocalDate.now().getMonthValue();
                currentMonth++;
            }
            
            // Get the existing events for the selected day
            List<String> selectedDayEvents = new ArrayList<>();
            for (String event : events) {
                if (event.contains("Date: " + selectedDay)) {
                    selectedDayEvents.add(event);
                }
            }

            // Populate the text area with the existing events
            eventTextArea.setText("");
            for (String event : selectedDayEvents) {
                eventTextArea.append(event + "\n");
            }


            prevButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    navigateToPreviousMonth();
                }
            });

            nextButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    navigateToNextMonth();
                }
            });

            frame.setVisible(true);
        });
    }

    private static void showEventDialog() {
        JFrame eventFrame = new JFrame("Events for " + selectedDay);
        eventFrame.setSize(300, 200);

        JPanel eventPanel = new JPanel(new BorderLayout());
        eventFrame.add(eventPanel);

        JTextArea eventTextArea = new JTextArea();
        eventPanel.add(eventTextArea, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Event");
        eventPanel.add(saveButton, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String event = eventTextArea.getText();
                if (!event.isEmpty()) {
                    events.add("Date: " + selectedDay + ", Event: " + event);
                    eventFrame.dispose();
                }
            }
        });

        eventFrame.setVisible(true);
    }
}
