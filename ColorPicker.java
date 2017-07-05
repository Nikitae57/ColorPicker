import javax.swing.*;
import java.awt.*;

public class ColorPicker {
    public static void main(String[] args) {
        ColorPicker CP = new ColorPicker();
        CP.start();
    }

    Point coords;
    Robot r;
    Color c;
    String red, green, blue;

    private void start() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/icon/icon.jpg"));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 10, 70);
        panel.setLayout(new GridLayout(1, 1));
        
        JButton button = new JButton();
        button.setFont(new Font("Serif", Font.PLAIN, 16));
        button.setEnabled(false);

        panel.add(button);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setResizable(false);

        frame.setIconImage(icon.getImage());
        frame.setVisible(true);

        try {
            r = new Robot();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        while (true) {
            coords = MouseInfo.getPointerInfo().getLocation();
            int x = (int) coords.getX();
            int y = (int) coords.getY();
            c = r.getPixelColor(x, y);
            red = Integer.toHexString(c.getRed());
            green = Integer.toHexString(c.getGreen());
            blue = Integer.toHexString(c.getBlue());

            if (c.getRed() < 10)
                red = "0" + red;
            if (c.getGreen() < 10)
                green = "0" + green;
            if (c.getBlue() < 10)
                blue = "0" + blue;

            button.setText("#" + red + green + blue);
            panel.setBorder(BorderFactory.createLineBorder(new Color(c.getRGB()), 10));
        }
    }
}
