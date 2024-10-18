package org.aiapp.algorithms;

import org.aiapp.direction.Direction;
import org.aiapp.grid.Grid;
import java.util.HashSet;

public class Dfs {

    private Grid startGrid, endGrid;
    private HashSet<Grid> visitedList;
    private HashSet<Grid> queue;

    public Dfs(Grid startGrid, Grid endGrid) {
        this.startGrid = startGrid;
        this.endGrid = endGrid;
    }

    public void solve() {

        Grid cState = startGrid;
        visitedList = new HashSet<>();
        queue = new HashSet<>();
        long startTime = System.nanoTime();
        long endTime, totalTime;

        System.out.println("Starting Depth First Search Algorithm...");

        while (cState != null && !cState.equals(endGrid)) {
            if (!visitedList.contains(cState)) {
                var moves = cState.getMoveableDirections();
                for (Direction d: moves) {
                    queue.add(new Grid(cState, d));
                }
                visitedList.add(cState);
            }
            cState = queue.stream().reduce((a, b) -> b).get();
            queue.remove(cState);
        }

        endTime = System.nanoTime();
        totalTime = endTime - startTime;

        if (cState == null) {
            System.out.println("-1");
        } else {
            double seconds = totalTime / 1_000_000_000.0;
            cState.visualize();
            System.out.println(cState.visualizeMoves());
            System.out.println("Moves: " + cState.getDepth());
            System.out.println("Total time: " + seconds + "s");
        }
    }
}
