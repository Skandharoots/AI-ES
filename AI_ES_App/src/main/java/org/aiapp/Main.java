package org.aiapp;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        InputHandler inputHandler = new InputHandler();

        switch(args[0]) {

            case "--bfs":
                InputResult inputResult = inputHandler.loadData();
                System.out.println(inputResult.getWidth());
                System.out.println(inputResult.getHeight());
                System.out.println(Arrays.deepToString(inputResult.getGrid()));
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