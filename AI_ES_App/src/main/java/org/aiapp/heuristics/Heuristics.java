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
            } else {
                throw new IllegalArgumentException("Invalid heuristic distance.");
            }
        } else if (algorithm.equals("astar")) {
            if (heuristic == 0) { //for Euclidian distance
                dEstimate1 = s1.getDistAstEuc(); //get distance from start node to parent
                dEstimate2 = s2.getDistAstEuc(); //get distance from start node to parent

                //calculate euclidian distance from parent to current
                for (int stY = 0; stY < endState.getWidth(); stY++) {
                    for (int stX = 0; stX < endState.getHeight(); stX++) {
                        int value1 = s1.getGrid()[stY][stX], value2 = s2.getGrid()[stY][stX];
                        for (int enY = 0; enY < endState.getWidth(); enY++) {
                            for (int enX = 0; enX < endState.getHeight(); enX++) {
                                if (value1 != 0 && value1 == s1.getParent().getGrid()[enY][enX]) {
                                    dEstimate1 += Math.sqrt(Math.pow(stX - enX, 2) + Math.pow(stY - enY, 2));
                                }
                                if (value2 !=0 && value2 == s2.getParent().getGrid()[enY][enX]) {
                                    dEstimate2 += Math.sqrt(Math.pow(stX - enX, 2) + Math.pow(stY - enY, 2));
                                }
                            }
                        }
                    }
                }
            }
            else if (heuristic == 1) { //for Manhattan distance
                dEstimate1 = s1.getDistAstMan(); //get distance from start node to parent
                dEstimate2 = s2.getDistAstMan(); //get distance from start node to parent

                //calculate manhattan distance from parent to current
                for (int stY = 0; stY < endState.getWidth(); stY++) {
                    for (int stX = 0; stX < endState.getHeight(); stX++) {
                        int value1 = s1.getGrid()[stY][stX], value2 = s2.getGrid()[stY][stX];
                        for (int enY = 0; enY < endState.getWidth(); enY++) {
                            for (int enX = 0; enX < endState.getHeight(); enX++) {
                                if (value1 != 0 && value1 == s1.getParent().getGrid()[enY][enX]) {
                                    dEstimate1 += Math.abs(stX - enX) + Math.abs(stY - enY);
                                }
                                if (value2 !=0 && value2 == s2.getParent().getGrid()[enY][enX]) {
                                    dEstimate2 += Math.abs(stX - enX) + Math.abs(stY - enY);
                                }
                            }
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("Invalid heuristic distance.");
            }

        } else {
            throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }

        return Double.compare(dEstimate1, dEstimate2);
    }
}
