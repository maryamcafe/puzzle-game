package display;

import util.PanelConfigs;

import javax.swing.*;
import java.awt.*;

public class Display {

    private static Display instance;
    private JFrame frame;
    private MyPanel panel;
    private int  panelSize;

    public Display(){
         panelSize = PanelConfigs.getInstance().getPanelSize();
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

        panel.setSize(panelSize, panelSize);
        frame.setSize(panel.getSize());
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addKeyListener(new MyKeyListener(panel));

        frame.setVisible(true);
    }


    public JFrame getFrame() {
        return frame;
    }

    public MyPanel getPanel() {
        return panel;
    }

}
