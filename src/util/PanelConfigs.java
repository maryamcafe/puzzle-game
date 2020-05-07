package util;

import java.awt.*;

public class PanelConfigs {

    private int width, height, totalPieces;
    private Configs configs;
    private int panelSize;

    public PanelConfigs(){
        ConfigLoader loader = ConfigLoader.getInstance();
        configs = loader.getPanelConfigs();
        setConfigs();
    }

    private void setConfigs() {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        panelSize = Math.max(screenWidth, screenHeight) / 3 - 10;
        totalPieces = configs.readInt("totalPieces");
    }


    public int getPanelSize() {
        return panelSize;
    }

    public int getTotalPieces() {
        return totalPieces;
    }

}
