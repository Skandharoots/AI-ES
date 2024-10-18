package org.aiapp.algorithms;

import org.aiapp.direction.Direction;
import org.aiapp.grid.Grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar {

    private Grid startGrid, endGrid;
    private ArrayList<Grid> visitedList;
    private PriorityQueue<Grid> queue;
    Comparator<Grid> heuristics;

    public AStar(Grid startGrid, Grid endGrid, Comparator<Grid> heuristics) {
        this.startGrid = startGrid;
        this.endGrid = endGrid;
        this.heuristics = heuristics;
    }

    public void solve() {
        Grid cState = startGrid;
        visitedList = new ArrayList<>();
        queue = new PriorityQueue<>(100, heuristics);
        long startTime, endTime, totalTime;
        startTime = System.nanoTime();

        System.out.println("Starting A* Algorithm...");

        while (cState != null && !cState.equals(endGrid)) {
            if (!visitedList.contains(cState)) {
                var moves = cState.getMoveableDirections();
                for (Direction d : moves) {
                    queue.add(new Grid(cState, d));
                }
                visitedList.add(cState);
            }
            cState = queue.poll();
        }
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        if (cState == null) {
            System.out.println("-1");
        }
        else {
            double seconds = totalTime / 1_000_000_000.0;
            cState.visualize();
            System.out.println(cState.visualizeMoves());
            System.out.println("Moves: " + cState.getDepth());
            System.out.println("Total time: " + seconds + "s");
        }
    }
}
