package org.aiapp.algorithms;

import org.aiapp.direction.Direction;
import org.aiapp.grid.Grid;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Bfs {

    private Grid startGrid;
    private Grid endGrid;
    private ArrayList<Grid> visitedGrids;
    private LinkedBlockingQueue<Grid> queue;
    private long numberOfNodes = 0;

    public Bfs(Grid startGrid, Grid endGrid) {
        this.startGrid = startGrid;
        this.endGrid = endGrid;
    }

    public void solve() {
        Grid cState = startGrid;
        visitedGrids = new ArrayList<>();
        queue = new LinkedBlockingQueue<>();
        long startTime, endTime, totalTime;

        startTime = System.nanoTime();

        System.out.println("Starting Breadth First Search Algorithm...");

        while (cState != null && !cState.equals(endGrid) && !cState.getPermutationsList().isEmpty()) {
            Direction d = cState.getNextPermutation();
            visitedGrids.add(cState);
            cState = new Grid(cState, d);
        }

        while (cState != null && !cState.equals(endGrid) && cState.getPermutationsList().isEmpty()) {
            if (!visitedGrids.contains(cState)) {
                var moves = cState.getMoveableDirections();
                for (Direction d: moves) {
                    queue.add(new Grid(cState, d));
                }
                visitedGrids.add(cState);
                numberOfNodes++;
            }
            cState = queue.poll();
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
