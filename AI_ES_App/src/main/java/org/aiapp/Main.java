package org.aiapp;

import org.aiapp.bfs.Bfs;
import org.aiapp.grid.Grid;
import org.aiapp.input.InputHandler;
import org.aiapp.input.InputResult;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        InputHandler inputHandler = new InputHandler();

        switch(args[0]) {

            case "--bfs":
                InputResult inputResult = inputHandler.loadData();
                Grid startGrid = new Grid(inputResult.getGrid(), inputResult.getWidth(), inputResult.getHeight(), inputResult.getPosX(), inputResult.getPosY());
                Grid endGrid = new Grid(inputResult.getEndGrid(), inputResult.getWidth(), inputResult.getHeight(), inputResult.getWidth() - 1, inputResult.getHeight() - 1);
                System.out.println(inputResult.getPosX() + " " + inputResult.getPosY());
                Bfs bfs = new Bfs(startGrid, endGrid);
                bfs.solve();
                break;

            case "--dfs":

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