package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PuzzlePiece {
    private Location location;
    private Image img;
    private int pieceNumber;

    private PuzzlePiece(String imageName) {
        try {
            img = ImageIO.read(new File("src/assets/" + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!imageName.equals("missing.jpg")) {
            pieceNumber = Integer.parseInt(imageName.substring(0, 1)) - 1;
        } else {
            pieceNumber = 8;
        }
    }

    public PuzzlePiece(String ImagePath, Location location) {
        this(ImagePath);
        this.location = location;
    }

    private PuzzlePiece(Image img, Location location, int pieceIdentifier) {
        this.img = img;
        this.location = location;
        this.pieceNumber = pieceIdentifier;
    }

    public int getPieceNumber() {
        return pieceNumber;
    }

    public void setPieceNumber(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

    public Image getImage() {
        return img;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public Location getLocation() {
        return location;
    }

    public PuzzlePiece getClone() {
        return new PuzzlePiece(img, location, pieceNumber);

    }
}