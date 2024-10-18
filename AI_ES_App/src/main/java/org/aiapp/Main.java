package org.aiapp;

import org.aiapp.algorithms.Bfs;
import org.aiapp.algorithms.Dfs;
import org.aiapp.grid.Grid;
import org.aiapp.input.InputHandler;
import org.aiapp.input.InputResult;

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

                break;

            case "--sma":

                break;

        }

    }
}