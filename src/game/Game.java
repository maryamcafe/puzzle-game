package game;

import board.Location;
import board.PuzzlePiece;
import display.Display;
import display.MyPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Game implements Runnable {

    boolean gameFinished = false;
    private Display display;
    private MyPanel panel;
    private JFrame frame;

    public Game() {
        display = Display.getInstance();
        panel = display.getPanel();
        frame = display.getFrame();

        initGame();
    }

    private void initGame() {

        ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
        ArrayList<Integer> piecesRandomOrder = new ArrayList<>(Arrays.asList(0, 5, 6, 7, 4, 3, 2, 8, 1));
        panel.setMissingPiece(7);

        for (int i = 0; i < 9; i++) {
            if (panel.getMissingPiece() != i) {
                puzzlePieces.add(new PuzzlePiece(piecesRandomOrder.get(i) + 1 + ".png",
                        new Location(panel.getHeight() / 3 * (i % 3), panel.getWidth() / 3 * (i / 3))));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg",
                        new Location(panel.getHeight() / 3 * (i % 3), panel.getWidth() / 3 * (i / 3))));
            }

            panel.setPuzzlePieces(puzzlePieces);

            if (!solvable(panel.getMissingPiece(), piecesRandomOrder)) {
                JOptionPane.showMessageDialog(display.getPanel(), "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
                gameFinished = true;
            }
        }
    }

    @Override
    public void run() {
        while (!gameFinished) {
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            panel.repaint();
            frame.repaint();

            if (panel.getGameState().equals("finished")) {
                JOptionPane.showMessageDialog(frame, "You finished the game, congratulation", "game finished", JOptionPane.INFORMATION_MESSAGE);
                gameFinished = true;
            }
        }
    }


    private boolean solvable(int missingPiece, ArrayList<Integer> piecesOrder) {
        int inversionCount = 0;
        for (int i = 0; i < piecesOrder.size(); i++) {
            for (int j = i + 1; j < piecesOrder.size(); j++) {
                if (piecesOrder.get(i) > piecesOrder.get(j)) {
                    inversionCount += 1;
                }
            }
        }

        int parity = inversionCount % 2;

        int distanceOfMissingPiece = (2 - (missingPiece % 3)) + (2 - (missingPiece / 3));

        parity ^= (distanceOfMissingPiece % 2);
        return parity == 0;
    }
}
