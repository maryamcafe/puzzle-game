package game;

import display.Display;
import display.MyPanel;

import javax.swing.*;
import java.util.List;

public class Game implements Runnable {

    private MyPanel panel;
    private JFrame frame;

    public Game() {
        panel = Display.getInstance().getPanel();
        frame = Display.getInstance().getFrame();
    }

    @Override
    public void run() {
        while (!gameFinished()) {
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            panel.repaint();
            frame.repaint();
        }
        //do something to stop keyListener.
        JOptionPane.showMessageDialog(frame, "You finished the game, congratulation", "game finished", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean gameFinished() {
        for (int i = 0; i < 9; i++) {
            int pieceIdentifier = panel.getPuzzlePieces().get(i).getPieceNumber();
            if (pieceIdentifier == 8) {
                continue;
            }
            if (pieceIdentifier != i) {
                return false;
            }
        }
        return true;
    }

}
