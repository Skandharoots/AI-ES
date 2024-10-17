package org.aiapp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {

    private int width;
    private int height;
    private int[][] grid;
    private InputResult inputResult;

    public InputHandler() {
    }

    public InputResult loadData() {

        String result = "";
        Pattern regex1 = Pattern.compile("^[1-9][0-9]* [1-9][0-9]*$");
        Scanner scanner = new Scanner(System.in);

        Boolean checkDimen = false;
        while(!checkDimen) {
            System.out.println("Please enter the width and height of the grid separated by a space:");
            result = scanner.nextLine();
            if ((regex1.matcher(result)).find()) {
                checkDimen = true;
                var separated = result.split(" ");
                width = Integer.parseInt(separated[0]);
                height = Integer.parseInt(separated[1]);
                grid = new int[width][height];
            } else {
                checkDimen = false;
                System.out.println("Wrong format!");
            }
        }
        String widthPat = Integer.toString(width - 1);

        Pattern regex2 = Pattern.compile("^([0-9][0-9]*[ ]){1," + widthPat + "}[0-9][0-9]*$");

        Boolean checkRow = false;
        int rowCount = 0;

        while (!(rowCount >= height)) {
            System.out.println("Please enter" + width + "row values separated by a space:");
            Scanner scanner2 = new Scanner(System.in);
            String result2 = scanner2.nextLine();
            if ((regex2.matcher(result2)).find()) {
                var separated = result2.split(" ");
                for (int i = 0; i < separated.length; i++) {
                    grid[rowCount][i] = Integer.parseInt(separated[i]);
                }
                rowCount++;
            } else {
                System.out.println("Wrong format!");
            }
        }

        inputResult = new InputResult(width, height, grid);

        return inputResult;
    }
}
