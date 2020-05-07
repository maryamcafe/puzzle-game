package game;

import display.Display;
import display.MyPanel;

import javax.swing.*;
import java.util.List;

public class Game implements Runnable {

    boolean gameFinished = false;
    private MyPanel panel;
    private JFrame frame;

    public Game() {
        panel = Display.getInstance().getPanel();
        frame = Display.getInstance().getFrame();
        warnIfNotSolvable();
    }

    private void warnIfNotSolvable() {
        if (!solvable(panel.getMissingPiece(),panel.getRandomOrder())) {
            JOptionPane.showMessageDialog(panel, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
            gameFinished = true;
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


    private boolean solvable(int missingPiece, List<Integer> piecesOrder) {
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
