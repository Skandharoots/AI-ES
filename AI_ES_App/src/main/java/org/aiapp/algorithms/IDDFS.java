package org.aiapp.algorithms;

import org.aiapp.direction.Direction;
import org.aiapp.grid.Grid;

import java.util.*;

public class IDDFS {

    private Grid startGrid, endGrid;
    private long startTime;
    private long endTime;

    public IDDFS(Grid startGrid, Grid endGrid) {
        System.out.println("Starting Iterative Deepening Depth First Search Algorithm...");
        this.startGrid = startGrid;
        this.endGrid = endGrid;

    }

    public boolean dls(Grid startGrid, Grid endGrid, int limit, LinkedList<Grid> visited) {

        if (startGrid.equals(endGrid)) {
            startGrid.visualize();
            System.out.println(startGrid.visualizeMoves());
            System.out.println("Moves: " + startGrid.getDepth());
            return true;
        }

        if (limit <= 0) {
            System.out.println("-1");
            return false;
        }

        visited.add(startGrid);

        var moves = startGrid.getMoveableDirections();
        for (Direction d: moves) {
            Grid nextGrid = new Grid(startGrid, d);
            if (!visited.contains(nextGrid)) {
                if (dls(nextGrid, endGrid, limit - 1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean solve(int limit) {

        startTime = System.nanoTime();
        long totalTime;

        for (int lim = 0; lim <= limit; ++lim) {
            LinkedList<Grid> visited = new LinkedList<>();
            if (dls(startGrid, endGrid, lim, visited)) {
                return true;
            }
        }
        return false;
    }



}
