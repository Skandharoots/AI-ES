package org.aiapp;

import org.aiapp.algorithms.*;
import org.aiapp.direction.Direction;
import org.aiapp.grid.Grid;
import org.aiapp.heuristics.Heuristics;
import org.aiapp.input.InputHandler;
import org.aiapp.input.InputResult;

import java.util.ArrayDeque;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        InputHandler inputHandler = new InputHandler();
        InputResult inputResult = inputHandler.loadData();
        Grid startGrid;
        Grid endGrid;
        Comparator<Grid> heuristics;
        ArrayDeque<Direction> permutations;
        switch(args[0]) {

            case "--bfs":
                if ((args.length == 2)) {
                    permutations = inputHandler.createPermutations(args[1]);
                    startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), permutations);
                    endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                } else {
                    permutations = new ArrayDeque<>();
                    startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), permutations);
                    endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                }
                Bfs bfs = new Bfs(startGrid, endGrid);
                bfs.solve();
                break;

            case "--dfs":
                if ((args.length == 2)) {
                    permutations = inputHandler.createPermutations(args[1]);
                    startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), permutations);
                    endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                } else {
                    permutations = new ArrayDeque<>();
                    startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), permutations);
                    endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                }
                Dfs dfs = new Dfs(startGrid, endGrid);
                dfs.solve();
                break;

            case "--idfs":
                if ((args.length == 2)) {
                    permutations = inputHandler.createPermutations(args[1]);
                    startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), permutations);
                    endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                } else {
                    permutations = new ArrayDeque<>();
                    startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), permutations);
                    endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                }
                IDDFS iddfs = new IDDFS(startGrid, endGrid);
                iddfs.solve(1000000000);
                break;

            case "--bf":
                if (args[1].equals("0")) {
                    startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                    endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                    heuristics = new Heuristics(0, "bf" ,endGrid);
                    BestFirst bf = new BestFirst(startGrid, endGrid, heuristics);
                    bf.solve();
                } else if (args[1].equals("1")) {
                    startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                    endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                    heuristics = new Heuristics(1, "bf" ,endGrid);
                    BestFirst bf = new BestFirst(startGrid, endGrid, heuristics);
                    bf.solve();
                } else {
                    System.out.println("Invalid heuristic. Available values are 0 for Euclidian and 1 for Manhattan distances.");
                }
                break;

            case "--astar":
                if (args[1].equals("0")) {
                    startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                    endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                    heuristics = new Heuristics(0, "astar" ,endGrid);
                    AStar astar = new AStar(startGrid, endGrid, heuristics);
                    astar.solve();
                } else if (args[1].equals("1")) {
                    startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                    endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                    heuristics = new Heuristics(1, "astar" ,endGrid);
                    AStar astar = new AStar(startGrid, endGrid, heuristics);
                    astar.solve();
                } else {
                    System.out.println("Invalid heuristic. Available values are 0 for Euclidian and 1 for Manhattan distances.");
                }
                break;

            case "--sma":
                if (args[1].equals("0")) {
                    startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                    endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                    heuristics = new Heuristics(0, "sma" ,endGrid);
                    SMA sma;
                    if (args.length == 3) {
                        sma = new SMA(startGrid, endGrid, Long.parseLong(args[2]), heuristics);
                    } else {
                        sma = new SMA(startGrid, endGrid, Long.parseLong("100"), heuristics);
                    }
                    sma.solve();
                } else if (args[1].equals("1")) {
                    startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                    endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), new ArrayDeque<>());
                    heuristics = new Heuristics(1, "sma" ,endGrid);
                    SMA sma;
                    if (args.length == 3) {
                        sma = new SMA(startGrid, endGrid, Long.parseLong(args[2]), heuristics);
                    } else {
                        sma = new SMA(startGrid, endGrid, Long.parseLong("100"), heuristics);
                    }
                    sma.solve();
                } else {
                    System.out.println("Invalid heuristic. Available values are 0 for Euclidian and 1 for Manhattan distances.");
                }
                break;

        }

    }
}