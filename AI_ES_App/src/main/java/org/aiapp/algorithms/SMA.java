package org.aiapp.algorithms;

import org.aiapp.direction.Direction;
import org.aiapp.grid.Grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SMA {

    private Grid startGrid, endGrid;
    private long maxLimit;
    private ArrayList<Grid> visitedList;
    private PriorityQueue<Grid> queue;
    Comparator<Grid> heuristics;

    public SMA(Grid startGrid, Grid endGrid, long maxLimit, Comparator<Grid> heuristics) {
        this.startGrid = startGrid;
        this.endGrid = endGrid;
        this.maxLimit = maxLimit;
        this.heuristics = heuristics;
    }

    public Integer getMemory(ArrayList<Grid> visitedList, PriorityQueue<Grid> queue) {
        return visitedList.size() + queue.size();
    }

    public void pruneMemory() {
        AtomicInteger i = new AtomicInteger();
        int max = queue.size() / 2;
        ArrayList<Grid> toRemove = new ArrayList<>();
        queue.forEach(grid -> {
            if (i.get() <= max) {
                toRemove.add(grid);
            }
            i.set(i.get() + 1);
        });
        toRemove.forEach(node -> {
            queue.remove(node);
        });
    }

    public void solve() {

        Grid cState = startGrid;
        visitedList = new ArrayList<>();
        queue = new PriorityQueue<>(100, heuristics);
        long startTime, endTime, totalTime;
        startTime = System.nanoTime();

        System.out.println("Starting SMA* algorithm...");

        while (cState != null && !cState.equals(endGrid)) {
            if (getMemory(visitedList, queue) > maxLimit) {
                pruneMemory();
            }
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
