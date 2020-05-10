package model;

import util.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PuzzlePiece {
    private Location location;
    private Image img;
    private int pieceNumber;
    private String imageName;


    private PuzzlePiece(Image img, Location location, int pieceIdentifier) {
        this.img = img;
        this.location = location;
        this.pieceNumber = pieceIdentifier;
    }

    public PuzzlePiece(Integer pieceNumber, Location location) {
        this.pieceNumber = pieceNumber;
        this.location = location;
        img = new ImageLoader().load(pieceNumber);
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
