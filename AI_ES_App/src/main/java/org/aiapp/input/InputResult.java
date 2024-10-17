package org.aiapp.input;

public class InputResult {

    private int width;
    private int height;
    private int[][] grid;

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
}
