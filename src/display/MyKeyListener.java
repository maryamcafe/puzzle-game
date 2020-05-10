package display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

    private MyPanel panel;

    public MyKeyListener(MyPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            swapRight(panel.getMissingPiece());
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            swapLeft(panel.getMissingPiece());
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            swapUp(panel.getMissingPiece());
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            swapDown(panel.getMissingPiece());
        }
    }

    private void swapRight(int missingPieceIndex) {
        if (missingPieceIndex % 3 == 2) {
            return;
        }
        panel.swapPieces(missingPieceIndex, missingPieceIndex + 1);
        panel.setMissingPiece(missingPieceIndex + 1);
    }

    private void swapLeft(int missingPieceIndex) {
        if (missingPieceIndex % 3 == 0) {
            return;
        }
        panel.swapPieces(missingPieceIndex, missingPieceIndex - 1);
        panel.setMissingPiece(missingPieceIndex - 1);
    }

    private void swapUp(int missingPieceIndex) {
        if (missingPieceIndex <= 2) {
            return;
        }
        panel.swapPieces(missingPieceIndex, missingPieceIndex - 3);
        panel.setMissingPiece(missingPieceIndex - 3);
    }

    private void swapDown(int missingPieceIndex) {
        if (missingPieceIndex >= 6) {
            return;
        }
        panel.swapPieces(missingPieceIndex, missingPieceIndex + 3);
        panel.setMissingPiece(missingPieceIndex + 3);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
