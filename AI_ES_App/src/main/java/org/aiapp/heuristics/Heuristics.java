package org.aiapp.heuristics;

import org.aiapp.grid.Grid;

import java.util.Comparator;

public class Heuristics implements Comparator<Grid> {

    private Grid endState;
    private int heuristic;
    private String algorithm;

    /** Constructor
     * @param h the chosen heuristic
     * @param eState the end state used to calculate heuristics.**/
    public Heuristics(int h, String algorithm, Grid eState) {
        this.endState = eState;
        this.algorithm = algorithm;
        this.heuristic = h;
    }

    @Override
    public int compare(Grid s1, Grid s2) {
        double dEstimate1;
        double dEstimate2;

        if (algorithm.equals("bf")) {
            dEstimate1 = 0;
            dEstimate2 = 0;
        } else if (algorithm.equals("astar")) {
            dEstimate1 = s1.getDepth();
            dEstimate2 = s2.getDepth();
        } else if (algorithm.equals("sma")) {
            dEstimate1 = s1.getDepth();
            dEstimate2 = s2.getDepth();
        } else {
            throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }

        if (heuristic == 0) { //for Euclidian distance
            for (int stY = 0; stY < endState.getWidth(); stY++) {
                for (int stX = 0; stX < endState.getHeight(); stX++) {
                    int value1 = s1.getGrid()[stY][stX], value2 = s2.getGrid()[stY][stX];
                    for (int enY = 0; enY < endState.getWidth(); enY++) {
                        for (int enX = 0; enX < endState.getHeight(); enX++) {
                            if (value1 != 0 && value1 == endState.getGrid()[enY][enX]) {
                                dEstimate1 = dEstimate1 + Math.sqrt(Math.pow(stX - enX, 2) + Math.pow(stY - enY, 2));
                            }
                            if (value2 !=0 && value2 == endState.getGrid()[enY][enX]) {
                                dEstimate2 = dEstimate2 + Math.sqrt(Math.pow(stX - enX, 2) + Math.pow(stY - enY, 2));
                            }
                        }
                    }
                }
            }
        }
        else if (heuristic == 1) { //for Manhattan distance
            for (int stY = 0; stY < endState.getWidth(); stY++) {
                for (int stX = 0; stX < endState.getHeight(); stX++) {
                    int value1 = s1.getGrid()[stY][stX], value2 = s2.getGrid()[stY][stX];
                    for (int enY = 0; enY < endState.getWidth(); enY++) {
                        for (int enX = 0; enX < endState.getHeight(); enX++) {
                            if (value1 != 0 && value1 == endState.getGrid()[enY][enX]) {
                                dEstimate1 = dEstimate1 + Math.abs(stX - enX) + Math.abs(stY - enY);
                            }
                            if (value2 !=0 && value2 == endState.getGrid()[enY][enX]) {
                                dEstimate2 = dEstimate2 + Math.abs(stX - enX) + Math.abs(stY - enY);
                            }
                        }
                    }
                }
            }
        }

        return Double.compare(dEstimate1, dEstimate2);
    }
}
