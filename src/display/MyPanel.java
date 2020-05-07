package display;

import model.Board;
import model.BoardBuilder;
import model.PuzzlePiece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel {
    private static MyPanel panelInstance;
//    private List<PuzzlePiece> puzzlePieces = new ArrayList<>();
    private transient List<PuzzlePiece> puzzlePieces;
    private List<Integer> randomOrder;
    private int missingPiece;
    private String gameState = "#";

    private MyPanel() {
        Board board = new BoardBuilder().createBoard();
        puzzlePieces = board.getPuzzlePieces();
        missingPiece = board.getMissingPiece();
        randomOrder = board.getInitialOrder();
    }

    public static MyPanel getInstance() {
        if (panelInstance == null) {
            panelInstance = new MyPanel();
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

    public List<Integer> getRandomOrder() {
        return randomOrder;
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
            g.drawImage(piece.getImage(),
                    piece.getLocation().getX(),
                    piece.getLocation().getY(),
                    (int) this.getSize().getWidth() / 3, //these are constants and therefore should be replaced.
                    (int) this.getSize().getHeight() / 3,
                    null);
        }
    }
}
