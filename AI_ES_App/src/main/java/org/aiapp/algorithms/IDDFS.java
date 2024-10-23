package org.aiapp.algorithms;

import org.aiapp.direction.Direction;
import org.aiapp.grid.Grid;

import java.util.*;

public class IDDFS {

    private Grid startGrid, endGrid;

    public IDDFS(Grid startGrid, Grid endGrid) {
        System.out.println("Starting Iterative Deepening Depth First Search Algorithm...");
        this.startGrid = startGrid;
        this.endGrid = endGrid;

    }

    public boolean dls(Grid startGrid, Grid endGrid, int limit, int permCnt, LinkedList<Grid> visited, ArrayList<Direction> permutations) {

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

        while (permCnt < permutations.size()) {
            Direction d = permutations.get(permCnt);
            Grid nextGrid = new Grid(startGrid, d);
            startGrid = nextGrid;
            permCnt++;
            limit--;

            if (limit <= 0) {
                System.out.println("-1");
                return false;
            }

            if (startGrid.equals(endGrid)) {
                startGrid.visualize();
                System.out.println(startGrid.visualizeMoves());
                System.out.println("Moves: " + startGrid.getDepth());
                return true;
            }
        }
            var moves = startGrid.getMoveableDirections();
            for (Direction d: moves) {
                Grid nextGrid = new Grid(startGrid, d);
                if (!visited.contains(nextGrid)) {
                    if (dls(nextGrid, endGrid, limit - 1, permCnt, visited, permutations)) {
                        return true;
                    }
                }
            }



        return false;
    }

    public boolean solve(int limit) {

        ArrayList<Direction> permutations = new ArrayList<>(startGrid.getPermutationsList());
        for (int lim = 1; lim <= limit; lim++) {
            LinkedList<Grid> visited = new LinkedList<>();
            if (dls(startGrid, endGrid, lim, 0, visited, permutations)) {
                return true;
            }
        }
        return false;
    }



}
