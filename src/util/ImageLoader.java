package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    private String imagesPath;
    private Integer missingPiece;

    public ImageLoader(){
        imagesPath = ConfigLoader.getInstance().getAssetsPath();
        missingPiece = PanelConfigs.getInstance().getMissingPiece();
    }

    public Image load(Integer pieceNumber) {
        String imageName = getFullImageName(pieceNumber);
        return read(imageName);
    }

    private String getFullImageName(Integer pieceNumber) {
        if (pieceNumber.equals(missingPiece)) {
            return "missing.jpg";
        } else {
            return pieceNumber + 1 + ".png";
        }
    }

    //OVERLOAD
    public Image load(String imageName){
        String fullImageName = getFullImageName(imageName);
        return read(fullImageName);
    }
    //OVERLOAD
    private String getFullImageName(String imageName) {
        if (imageName.equals("missing")) {
            return "missing.jpg";
        } else {
            return imageName + ".png";
        }
    }

    private Image read(String fullImageName) {
        try {
            return ImageIO.read(new File(imagesPath + fullImageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
