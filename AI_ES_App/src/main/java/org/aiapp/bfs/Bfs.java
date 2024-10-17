package org.aiapp.bfs;

import org.aiapp.direction.Direction;
import org.aiapp.grid.Grid;

import java.util.ArrayList;
import java.util.Arrays;
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

        System.out.println("Starting BSF...");

        while (cState != null && !cState.equals(endGrid)) {
            if (!visitedGrids.contains(cState)) {
                for (Direction d: Direction.values()) {
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
//            cState.getParent().forEach(parent -> {
//                System.out.println(Arrays.deepToString(parent.getGrid()));
//            });
            System.out.println(cState.getMoves());
            System.out.println("Total time: " + totalTime);
            System.out.println("Moves: " + cState.getDepth());
            System.out.println(Arrays.deepToString(cState.getGrid()));
        }
    }
}
