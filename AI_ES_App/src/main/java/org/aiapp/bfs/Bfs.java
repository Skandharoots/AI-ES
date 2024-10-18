package org.aiapp.bfs;

import org.aiapp.direction.Direction;
import org.aiapp.grid.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

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

    public void visualize(int[][] grid) {
        int width = startGrid.getWidth();
        int height = startGrid.getHeight();

        for (int x = 0; x < width; x++) {
            System.out.print("[");
            for (int y = 0; y < height; y++) {
                System.out.print(" " + grid[x][y]);
            }
            System.out.println(" ]");
        }
        System.out.println();
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
            double seconds = totalTime / 1_000_000_000.0;
            cState.getHistory().forEach(this::visualize);
            System.out.println(cState.getMoves());
            System.out.println("Moves: " + cState.getDepth());
            System.out.println("Total time: " + seconds + "s");

        }
    }
}
