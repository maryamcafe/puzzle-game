package model;

import util.PanelConfigs;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Integer> initialOrder;
    //we could also create an array instead of arrayList,
    // but it will prevent us from developing the game in other ways.
    private transient List<PuzzlePiece> puzzlePieces;
    private transient int panelWidth, panelHeight, missingPiece,
            missingPieceIndex, totalPieces, rows;


    public Board(List<Integer> initialOrder){
        this.initialOrder = initialOrder;

        PanelConfigs configs = PanelConfigs.getInstance();
        panelHeight = configs.getPanelSize();
        panelWidth = configs.getPanelSize();

        totalPieces = configs.getTotalPieces();
        missingPiece = configs.getMissingPiece();
        rows = configs.getRows();

        puzzlePieces = new ArrayList<>();
        initPuzzlePieces();
    }


    private void initPuzzlePieces() {
        missingPieceIndex = initialOrder.indexOf(missingPiece);
        for (int i = 0; i < totalPieces; i++) {
            Location location = new Location(panelHeight / rows * (i % rows), panelWidth / rows * (i / rows));
            puzzlePieces.add(new PuzzlePiece(initialOrder.get(i), location));
        }
    }

    public List<Integer> getInitialOrder() {
        return initialOrder;
    }

    public List<PuzzlePiece> getPuzzlePieces() {
        return puzzlePieces;
    }

    public int getMissingPieceIndex() {
        return missingPieceIndex;
    }

    @Override
    public String toString() {
        return "Board{" +
                "initialOrder=" + initialOrder +
                '}';
    }
}
