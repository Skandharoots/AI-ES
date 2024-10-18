package org.aiapp;

import org.aiapp.algorithms.AStar;
import org.aiapp.algorithms.Bfs;
import org.aiapp.algorithms.Dfs;
import org.aiapp.grid.Grid;
import org.aiapp.heuristics.Heuristics;
import org.aiapp.input.InputHandler;
import org.aiapp.input.InputResult;

import java.util.Comparator;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        InputHandler inputHandler = new InputHandler();
        InputResult inputResult = inputHandler.loadData();
        Grid startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight());
        Grid endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight());
        switch(args[0]) {

            case "--bfs":
                Bfs bfs = new Bfs(startGrid, endGrid);
                bfs.solve();
                break;

            case "--dfs":
                Dfs dfs = new Dfs(startGrid, endGrid);
                dfs.solve();
                break;

            case "--idfs":

                break;

            case "--bf":

                break;

            case "--astar":
                Comparator<Grid> heuristics;
                if (args[1].equals("0")) {
                    heuristics = new Heuristics(0, endGrid);
                    AStar astar = new AStar(startGrid, endGrid, heuristics);
                    astar.solve();
                } else if (Objects.equals(args[1], "1")) {
                    heuristics = new Heuristics(1, endGrid);
                    AStar astar = new AStar(startGrid, endGrid, heuristics);
                    astar.solve();
                } else {
                    System.out.println("Unknown heuristic!");
                }
                break;

            case "--sma":

                break;

        }

    }
}