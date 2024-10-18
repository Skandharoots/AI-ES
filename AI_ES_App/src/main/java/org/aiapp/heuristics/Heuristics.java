package org.aiapp.heuristics;

import org.aiapp.grid.Grid;

import java.util.Comparator;

public class Heuristics implements Comparator<Grid> {

    private Grid endState;
    private int heuristic;

    /** Constructor
     * @param h the chosen heuristic
     * @param eState the end state used to calculate heuristics.**/
    public Heuristics(int h, Grid eState) {
        endState = eState;
        heuristic = h;
    }

    @Override
    public int compare(Grid s1, Grid s2) {
        long dEstimate1 = s1.getDepth(), dEstimate2 = s2.getDepth();
        if (heuristic == 0) {
            for(int y = 0; y < endState.getWidth(); y++) {
                for(int x = 0; x < endState.getHeight(); x++) {
                    if (endState.getGrid()[x][y] != s1.getGrid()[x][y] && s1.getGrid()[x][y] != 0) {
                        dEstimate1++;
                    }
                    if (endState.getGrid()[x][y] != s2.getGrid()[x][y] && s2.getGrid()[x][y] != 0) {
                        dEstimate2++;
                    }
                }
            }
        }
        else if (heuristic == 1){ //for mode 1
            for (int stY = 0; stY < endState.getWidth(); stY++) {
                for (int stX = 0; stX < endState.getHeight(); stX++) {
                    int value1 = s1.getGrid()[stX][stY], value2 = s2.getGrid()[stX][stY];
                    for (int enY = 0; enY < endState.getWidth(); enY++) {
                        for (int enX = 0; enX < endState.getHeight(); enX++) {
                            if (value1 != 0 && value1 == endState.getGrid()[enX][enY]) {
                                dEstimate1 = dEstimate1 + Math.abs(stX-enX) + Math.abs(stY-enY);
                            }
                            if (value2 !=0 && value2 == endState.getGrid()[enX][enY]) {
                                dEstimate2 = dEstimate2 + Math.abs(stX-enX) + Math.abs(stY-enY);
                            }
                        }
                    }
                }
            }

        }
        //do nothing if the mode is invalid
        return Long.compare(dEstimate1, dEstimate2);
    }
}
