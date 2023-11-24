import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DrawingProgramApp {
    private static enum ShapeType { LINE, RECTANGLE, ELLIPSE }

    private static ShapeType currentShape = ShapeType.LINE;
    private static Point startPoint = null;
    private static Point endPoint = null;

    private static JPanel canvas;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simple Drawing Program");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            canvas = new JPanel() {
                private java.util.List<Shape> shapes = new java.util.ArrayList<>();
                private java.util.List<Color> shapeColors = new java.util.ArrayList<>();

                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;

                    for (int i = 0; i < shapes.size(); i++) {
                        g2d.setColor(shapeColors.get(i));
                        g2d.draw(shapes.get(i));
                    }
                }
            };
            canvas.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint();
                    endPoint = startPoint;
                    canvas.repaint();
                }

                public void mouseReleased(MouseEvent e) {
                    if (startPoint != null) {
                        endPoint = e.getPoint();
                        canvas.repaint();
                    }
                }
            });
            canvas.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    if (startPoint != null) {
                        endPoint = e.getPoint();
                        canvas.repaint();
                    }
                }
            });
            frame.add(canvas, BorderLayout.CENTER);

            JPanel tools = new JPanel();
            frame.add(tools, BorderLayout.NORTH);

            ButtonGroup shapeGroup = new ButtonGroup();
            JRadioButton lineButton = new JRadioButton("Line");
            JRadioButton rectangleButton = new JRadioButton("Rectangle");
            JRadioButton ellipseButton = new JRadioButton("Ellipse");
            shapeGroup.add(lineButton);
            shapeGroup.add(rectangleButton);
            shapeGroup.add(ellipseButton);
            tools.add(lineButton);
            tools.add(rectangleButton);
            tools.add(ellipseButton);

            lineButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    currentShape = ShapeType.LINE;
                }
            });

            rectangleButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    currentShape = ShapeType.RECTANGLE;
                }
            });

            ellipseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    currentShape = ShapeType.ELLIPSE;
                }
            });

            frame.setVisible(true);
        });
    }
}
