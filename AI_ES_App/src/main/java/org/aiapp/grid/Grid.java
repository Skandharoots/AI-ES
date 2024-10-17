package org.aiapp.grid;

import org.aiapp.direction.Direction;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {

    private int width, height;
    private int posX, posY;
    private long depth;
    private int[][] grid;
    private ArrayList<Direction> moves;
    private Grid parent;

    public Grid(int[][] grid, int width, int height, int posX, int posY) {
        this.grid = grid;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.moves = new ArrayList<>();
        this.depth = 0;
        this.parent = null;
    }

    public Grid(Grid parent, Direction direction) {
        this.grid = parent.grid.clone();
        for (int y = 0; y < this.grid.length; y++) {
            this.grid[y] = parent.grid[y].clone();
        }
        this.posX = parent.posX;
        this.posY = parent.posY;
        this.width = parent.width;
        this.height = parent.height;
        this.depth = parent.depth;
        this.moves = parent.moves;
        if (moveSpace(direction)) {
            this.depth++;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(grid);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Grid other = (Grid) obj;
        for (int y = 0; y < this.width; y++) {
            for (int x = 0; x < this.height; x++) {
                if (this.grid[y][x] != other.grid[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getGrid() {
        return grid;
    }

    public long getDepth() {
        return depth;
    }

    public ArrayList<Direction> getMoves() {
        return moves;
    }

    public Grid getParent() {
        return parent;
    }

    private boolean moveSpace(Direction d) {
        switch (d){
            case U:
                if (posY > 0){
                    grid[posX][posY] = grid[posX][posY-1];
                    posY--;
                    grid[posX][posY] = 0;
                    moves.add(d);
                    return true;
                } else {
                    return false;
                }
            case D:
                if (posY < height - 1) {
                    grid[posX][posY] = grid[posX][posY+1];
                    posY++;
                    grid[posX][posY] = 0;
                    moves.add(d);
                    return true;
                } else {
                    return false;
                }
            case L:
                if (posX > 0){
                    grid[posX][posY] = grid[posX-1][posY];
                    posX--;
                    grid[posX][posY] = 0;
                    moves.add(d);
                    return true;
                } else {
                    return false;
                }
            case R:
                if (posX < width - 1) {
                    grid[posX][posY] = grid[posX+1][posY];
                    posX++;
                    grid[posX][posY] = 0;
                    moves.add(d);
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }


}
