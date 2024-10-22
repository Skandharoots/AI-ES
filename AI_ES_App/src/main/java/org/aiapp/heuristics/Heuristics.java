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
        long dEstimate1 = 0;
        long dEstimate2 = 0;

        if (heuristic == 0) { //for Best First Algorithm
            for (int stY = 0; stY < endState.getWidth(); stY++) {
                for (int stX = 0; stX < endState.getHeight(); stX++) {
                    int value1 = s1.getGrid()[stY][stX], value2 = s2.getGrid()[stY][stX];
                    for (int enY = 0; enY < endState.getWidth(); enY++) {
                        for (int enX = 0; enX < endState.getHeight(); enX++) {
                            if (value1 != 0 && value1 == endState.getGrid()[enY][enX]) {
                                dEstimate1 = dEstimate1 + Math.abs(stX-enX) + Math.abs(stY-enY);
                            }
                            if (value2 !=0 && value2 == endState.getGrid()[enY][enX]) {
                                dEstimate2 = dEstimate2 + Math.abs(stX-enX) + Math.abs(stY-enY);
                            }
                        }
                    }
                }
            }
        }
        else if (heuristic == 1) { //for A* algorithm
            dEstimate1 = s1.getDepth();
            dEstimate2 = s2.getDepth();
            for (int stY = 0; stY < endState.getWidth(); stY++) {
                for (int stX = 0; stX < endState.getHeight(); stX++) {
                    int value1 = s1.getGrid()[stY][stX], value2 = s2.getGrid()[stY][stX];
                    for (int enY = 0; enY < endState.getWidth(); enY++) {
                        for (int enX = 0; enX < endState.getHeight(); enX++) {
                            if (value1 != 0 && value1 == endState.getGrid()[enY][enX]) {
                                dEstimate1 = dEstimate1 + Math.abs(stX-enX) + Math.abs(stY-enY);
                            }
                            if (value2 !=0 && value2 == endState.getGrid()[enY][enX]) {
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
