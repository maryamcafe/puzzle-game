package model;

import util.PanelConfigs;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Integer> initialOrder;
    //we could also create an array instead of arrayList,
    // but it will prevent us from developing the game in other ways.
    private transient List<PuzzlePiece> puzzlePieces;
    private transient int panelWidth, panelHeight, missingPiece;


    public Board(List<Integer> initialOrder){
        this.initialOrder = initialOrder;

        PanelConfigs configs = new PanelConfigs();
        panelHeight = configs.getPanelSize();
        panelWidth = configs.getPanelSize();
        puzzlePieces = new ArrayList<>();
        initPuzzlePieces();
    }


    private void initPuzzlePieces() {
        missingPiece = initialOrder.indexOf(8);
        for (int i = 0; i < 9; i++) {
            Location location = new Location(panelHeight / 3 * (i % 3), panelWidth / 3 * (i / 3));
            if (missingPiece != i) {
                puzzlePieces.add(new PuzzlePiece(initialOrder.get(i) + 1 + ".png",location));
            } else {
                puzzlePieces.add(new PuzzlePiece("missing.jpg", location));
            }
        }
    }

    public List<Integer> getInitialOrder() {
        return initialOrder;
    }

    public List<PuzzlePiece> getPuzzlePieces() {
        return puzzlePieces;
    }

    public int getMissingPiece() {
        return missingPiece;
    }
}
