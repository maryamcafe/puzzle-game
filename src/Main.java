import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


class Location {
    private int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

class PuzzlePiece {
    private Image img;
    Location location;
    private int pieceNumber;

    public int getPieceNumber() {
        return pieceNumber;
    }

    public void setPieceNumber(int pieceNumber) {
        this.pieceNumber = pieceNumber;
    }

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


    public Image getImage() {
        return img;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public PuzzlePiece getClone() {
        PuzzlePiece clone = new PuzzlePiece(img, location, pieceNumber);

        return clone;
    }
}

    class MyPanel extends JPanel {
    private ArrayList<PuzzlePiece> puzzlePieces = new ArrayList<>();
    private int missingPiece = 0;

    private MyPanel() {

    }

    private String gameState = "#";

    private static MyPanel panelInstance;

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

    public void setMissingPiece(int missingPiece) {
        this.missingPiece = missingPiece;
    }

    public int getMissingPiece() {
        return missingPiece;
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
            g.drawImage(piece.getImage(), piece.location.getX(), piece.location.getY(), (int) this.getSize().getWidth() / 3, (int) this.getSize().getHeight() / 3, null);
        }
    }
}


class MyKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        int missingPieceIndex = MyPanel.getInstance().getMissingPiece();
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {

            if (missingPieceIndex % 3 == 2) {
                return;
            }
            MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + 1);
            MyPanel.getInstance().setMissingPiece(missingPieceIndex + 1);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            if (missingPieceIndex % 3 == 0) {
                return;
            }
            MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - 1);
            MyPanel.getInstance().setMissingPiece(missingPieceIndex - 1);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            if (missingPieceIndex <= 2) {
                return;
            }
            MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex - 3);
            MyPanel.getInstance().setMissingPiece(missingPieceIndex - 3);
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            if (missingPieceIndex >= 6) {
                return;
            }
            MyPanel.getInstance().swapPieces(missingPieceIndex, missingPieceIndex + 3);
            MyPanel.getInstance().setMissingPiece(missingPieceIndex + 3);
        }

        if (MyPanel.getInstance().getGameState().equals("finished")) {
            return;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}


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
        if (!solvable(panel.getMissingPiece(), piecesRandomOrder)){
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

        int  distanceOfMissingPiece = (2 - (missingPiece % 3)) + (2 - (missingPiece / 3));

        parity ^= (distanceOfMissingPiece % 2);
        if (parity == 0) {
            return true;
        }
        return false;
    }
}
