package util;

import java.awt.*;

public class PanelConfigs {

    private int panelSize, totalPieces, missingPiece, rows;
    private Configs configs;
    private static PanelConfigs instance;

    public static PanelConfigs getInstance(){
        if (instance == null){
            instance = new PanelConfigs();
        }
        return instance;
    }

    private PanelConfigs(){
        ConfigLoader loader = ConfigLoader.getInstance();
        configs = loader.getPanelConfigs();
        setConfigs();
    }

    private void setConfigs() {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        panelSize = Math.max(screenWidth, screenHeight) / 3 - 10;
        totalPieces = configs.readInt("totalPieces");
        missingPiece = totalPieces - 1;
        setRows();
    }

    private void setRows() {
        if(totalPieces==9){
            rows = 3;
        }
    }

    public int getPanelSize() {
        return panelSize;
    }

    public int getTotalPieces() {
        return totalPieces;
    }

    public int getMissingPiece() {
        return missingPiece;
    }

    public int getRows() {
        return rows;
    }
}
