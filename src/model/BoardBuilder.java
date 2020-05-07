package model;

import util.ConfigLoader;
import util.PanelConfigs;

import java.util.List;
import java.util.Random;

public class BoardBuilder {

    private Random random;
    private int piecesNumber;
    //create a random list of numbers from 0 to 8 and check if it's solvable;

    public BoardBuilder() {
        random = new Random(System.nanoTime());
        piecesNumber = new PanelConfigs().getTotalPieces();
    }

    public Board createBoard() {
        return new Board(createInitialOrder());
    }

    private List<Integer> createInitialOrder() {
        //get a random configuration and if it was not solvable,
        // leave it and get another one
        List<Board> boards;
        boards = ConfigLoader.getInstance().getBoardConfigs();
        int i = random.nextInt(boards.size());
        List<Integer> result = boards.get(i).getInitialOrder();
        if (solvable(result)) {
            return result;
        } else {
            return createInitialOrder();
        }
    }

    private boolean solvable(List<Integer> piecesOrder) {
        int inversionCount = 0;
        for (int i = 0; i < piecesOrder.size(); i++) {
            for (int j = i + 1; j < piecesOrder.size(); j++) {
                if (piecesOrder.get(i) > piecesOrder.get(j)) {
                    inversionCount += 1;
                }
            }
        }
        int missingPiece = piecesOrder.indexOf(8);
        int parity = inversionCount % 2;
        int distanceOfMissingPiece = (2 - (missingPiece % 3)) + (2 - (missingPiece / 3));
        parity ^= (distanceOfMissingPiece % 2);
        return parity == 0;
    }
}
