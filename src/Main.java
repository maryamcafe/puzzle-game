import board.Location;
import board.PuzzlePiece;
import display.MyKeyListener;
import display.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();


        MyPanel panel = MyPanel.getInstance();

        int screenWidth, screenHeight;
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int maxSize = Math.max(screenWidth, screenHeight) / 3;
        panel.setSize(maxSize, maxSize);
        panel.setLocation(screenWidth / 2 - maxSize / 2, screenHeight / 2 - maxSize / 2);
        frame.setSize(panel.getSize());
        frame.setLocation(panel.getLocation());
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();

        ArrayList<Integer> piecesRandomOrder = new ArrayList<>(Arrays.asList(0, 5, 6, 7, 4, 3, 2, 8, 1));
        panel.setMissingPiece(7);
        boolean gameFinished = false;
        if (!solvable(panel.getMissingPiece(), piecesRandomOrder)) {
            JOptionPane.showMessageDialog(frame, "this puzzle is not solvable, change your config and try again", "Puzzle not solvable", JOptionPane.WARNING_MESSAGE);
            gameFinished = true;
        }

        for (int i = 0; i < 9; i++) {
            if (panel.getMissingPiece() != i) {
                puzzlePieces.add(new PuzzlePiece(piecesRandomOrder.get(i) + 1 + ".png", new Location(panel.getHeight() / 3 * (i % 3), panel.getWidth() / 3 * (i / 3))));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg", new Location(panel.getHeight() / 3 * (i % 3), panel.getWidth() / 3 * (i / 3))));
            }
        }
        panel.setPuzzlePieces(puzzlePieces);

        frame.addKeyListener(new MyKeyListener());

        frame.setVisible(true);


        while (true) {
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            panel.repaint();
            frame.repaint();

            if (gameFinished) {
                break;
            }
            if (panel.getGameState().equals("finished")) {
                JOptionPane.showMessageDialog(frame, "You finished the game, congratulation", "game finished", JOptionPane.INFORMATION_MESSAGE);
                gameFinished = true;
            }

        }


    }

    private static boolean solvable(int missingPiece, ArrayList<Integer> piecesOrder) {
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
        if (parity == 0) {
            return true;
        }
        return false;
    }
}
