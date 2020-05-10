package model;

import util.PanelConfigs;

import java.util.List;

public class SolvablePuzzleChecker {

    private int rows;
    private int inversionCount, XRow, row;

    public SolvablePuzzleChecker() {
        rows = PanelConfigs.getInstance().getRows();

    }

    public boolean isSolvable(List<Integer> pieces) {
        inversionCount = 0;
        XRow = 0;
        row = 0;
        for (int i = 0; i < pieces.size(); i++) {
            if (i % rows == 0) { // advance to next row
                row++;
            }
            if (pieces.get(i) == 8) { // the blank tile
                XRow = row; //save the row on which encountered
                continue;
            }
            for (int j = i + 1; j < pieces.size(); j++) {
                if (pieces.get(i) > pieces.get(j)) {
                    inversionCount += 1;
                }
            }
        }

        if (rows % 2 == 0) { // even grid
            if (XRow % 2 == 0) { // blank on odd row; counting from bottom
                return inversionCount % 2 == 0;
            } else { // blank on even row; counting from bottom
                return inversionCount % 2 != 0;
            }
        } else { // odd grid
            return inversionCount % 2 == 0;
        }
    }

}
