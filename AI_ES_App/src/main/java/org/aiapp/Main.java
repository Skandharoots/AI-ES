package org.aiapp;

import org.aiapp.algorithms.*;
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
        Comparator<Grid> heuristics;
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
                IDDFS iddfs = new IDDFS(startGrid, endGrid);
                iddfs.solve(1000000000);
                break;

            case "--bf":
                    heuristics = new Heuristics(0, endGrid);
                    BestFirst bf = new BestFirst(startGrid, endGrid, heuristics);
                    bf.solve();
                break;

            case "--astar":
                    heuristics = new Heuristics(1, endGrid);
                    AStar astar = new AStar(startGrid, endGrid, heuristics);
                    astar.solve();
                break;

            case "--sma":

                break;

        }

    }
}