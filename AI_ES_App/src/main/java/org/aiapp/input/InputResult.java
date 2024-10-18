package org.aiapp.input;

import org.aiapp.grid.Grid;

import java.util.ArrayList;
import java.util.Comparator;

public class InputResult {

    private int width;
    private int height;
    private int[][] grid;
    private int[][] endGrid;

    public InputResult(int width, int height, int[][] grid) {
        this.width = width;
        this.height = height;
        this.grid = grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getGrid() {
        return grid;
    }

    public int[][] getEndGrid() {
        endGrid = solveEndGrid();
        return endGrid;
    }

    public int[][] solveEndGrid() {

        ArrayList<Integer> grid1d = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid1d.add(grid[i][j]);
            }
        }
        grid1d.sort(Comparator.naturalOrder());

        int first = grid1d.get(0);

        for (int i = 0; i < grid1d.size() - 1; i++) {
            grid1d.set(i, grid1d.get(i + 1));
        }

        grid1d.set(grid1d.size() - 1, first);

        int[][] endGridState = new int[width][height];

        int k = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                endGridState[i][j] = grid1d.get(k);
                k++;
            }
        }

        return endGridState;

    }
}
