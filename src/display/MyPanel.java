package display;

import board.PuzzlePiece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    private static MyPanel panelInstance;
    private ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    private int missingPiece = 0;
    private String gameState = "#";

    private MyPanel() {

    }

    public static MyPanel getInstance() {
        if (panelInstance == null) {
            panelInstance = new MyPanel();
            return panelInstance;
        }
        return panelInstance;
    }

    public void setPuzzlePieces(ArrayList<PuzzlePiece> puzzlePieces) {
        this.puzzlePieces = puzzlePieces;
    }

    public int getMissingPiece() {
        return missingPiece;
    }

    public void setMissingPiece(int missingPiece) {
        this.missingPiece = missingPiece;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }


    public void swapPieces(int i, int j) {
        PuzzlePiece copy = this.puzzlePieces.get(i).getClone();
        puzzlePieces.get(i).setImage(puzzlePieces.get(j).getImage());
        puzzlePieces.get(i).setPieceNumber(puzzlePieces.get(j).getPieceNumber());
        puzzlePieces.get(j).setImage(copy.getImage());
        puzzlePieces.get(j).setPieceNumber(copy.getPieceNumber());


        if (gameFinished()) {
            gameState = "finished";
        }
    }

    private boolean gameFinished() {
        for (int i = 0; i < 9; i++) {
            int pieceIdentifier = puzzlePieces.get(i).getPieceNumber();
            if (pieceIdentifier == 8) {
                continue;
            }

            if (pieceIdentifier != i) {
                return false;
            }
        }
        return true;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (PuzzlePiece piece : puzzlePieces) {
            g.drawImage(piece.getImage(), piece.getLocation().getX(), piece.getLocation().getY(), (int) this.getSize().getWidth() / 3, (int) this.getSize().getHeight() / 3, null);
        }
    }
}
