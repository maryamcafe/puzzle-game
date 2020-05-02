package display;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Dimension2D;

public class Display {

    private static Display instance;
    private JFrame frame;
    private MyPanel panel;
    private int screenWidth, screenHeight, maxSize;

    public Display(){
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        maxSize = Math.max(screenWidth, screenHeight) / 3;
        initDisplay();
    }

    public static Display getInstance(){
        if (instance == null){
            instance = new Display();
        }
        return instance;
    }

    private void initDisplay() {
        frame = new JFrame();
        panel = MyPanel.getInstance();

        panel.setSize(maxSize, maxSize);
        panel.setLocation(screenWidth / 2 - maxSize / 2, screenHeight / 2 - maxSize / 2);
        frame.setSize(panel.getSize());
        frame.setLocation(panel.getLocation());
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addKeyListener(new MyKeyListener());

        frame.setVisible(true);
    }


    public JFrame getFrame() {
        return frame;
    }

    public MyPanel getPanel() {
        return panel;
    }
}
