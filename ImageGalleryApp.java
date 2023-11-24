import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageGalleryApp {
    private static int currentIndex = 0;
    private static File[] imageFiles;
    private static JLabel imageLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Image Gallery");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JPanel panel = new JPanel(new BorderLayout());
            frame.add(panel);

            imageLabel = new JLabel();
            panel.add(imageLabel, BorderLayout.CENTER);

            JButton prevButton = new JButton("Previous");
            JButton nextButton = new JButton("Next");

            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(prevButton);
            buttonPanel.add(nextButton);
            panel.add(buttonPanel, BorderLayout.SOUTH);

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setMultiSelectionEnabled(true);

            prevButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (currentIndex > 0) {
                        currentIndex--;
                        loadImage(imageFiles[currentIndex]);
                    }
                }
            });

            nextButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (currentIndex < imageFiles.length - 1) {
                        currentIndex++;
                        loadImage(imageFiles[currentIndex]);
                    }
                }
            });

            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                imageFiles = fileChooser.getSelectedFiles();
                if (imageFiles.length > 0) {
                    loadImage(imageFiles[currentIndex]);
                }
            }

            frame.setVisible(true);
        });
    }

    private static void loadImage(File file) {
        try {
            BufferedImage image = ImageIO.read(file);
            if (image != null) {
                imageLabel.setIcon(new ImageIcon(image));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
