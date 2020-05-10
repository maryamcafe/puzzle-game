package model;

import util.ConfigLoader;
import util.PanelConfigs;

import java.util.List;
import java.util.Random;

public class BoardBuilder {

    private Random random;
    private List<Integer> initialOrder;
    private SolvablePuzzleChecker checker;
    //create a random list of numbers from 0 to 8 and check if it's solvable;

    public BoardBuilder() {
        random = new Random(System.nanoTime());
        checker = new SolvablePuzzleChecker();
        initialOrder = createInitialOrder();
    }

    public Board createBoard() {
        return new Board(initialOrder);
    }

    //get a random configuration and if it was not solvable,
    // leave it and get another one
    private List<Integer> createInitialOrder() {
        List<Board> boards;
        boards = ConfigLoader.getInstance().getBoardConfigs();
        int i = random.nextInt(boards.size());
        List<Integer> result = boards.get(i).getInitialOrder();
        if (checker.isSolvable(result)) {
            return result;
        } else {
            return createInitialOrder();
        }
    }

}
