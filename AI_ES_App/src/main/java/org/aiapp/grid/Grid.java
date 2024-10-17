package org.aiapp.grid;

import org.aiapp.direction.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {

    private int width, height;
    private int posX, posY;
    private int[][] grid;
    private long operations;
    private ArrayList<Direction> moves;
    private Grid parent;

    public Grid(int[][] grid, int width, int height, int posX, int posY) {
        this.grid = grid;
        this.posX = posX;
        this.posY = posY;
        this.operations = 0;
        this.moves = new ArrayList<>();
        this.parent = null;
    }

    private Grid(Grid parent, Direction direction) {
        this.grid = parent.grid.clone();
        for (int y = 0; y < this.grid.length; y++) {
            this.grid[y] = parent.grid[y].clone();
        }
        this.posX = parent.posX;
        this.posY = parent.posY;
        this.width = parent.width;
        this.height = parent.height;
        this.operations = parent.operations;
        this.moves = parent.moves;
        this.moveSpace(direction);
        this.operations++;

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
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
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

    public long getOperations() {
        return operations;
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
                if (posY>0){
                    grid[posX][posY] = grid[posX][posY-1];
                    posY--;
                    grid[posX][posY] = 0;
                    moves.add(d);
                    return true;
                }
            case D:
                if (posY<(grid.length-1)) {
                    grid[posX][posY] = grid[posX][posY+1];
                    posY++;
                    grid[posX][posY] = 0;
                    moves.add(d);
                    return true;
                }
            case L:
                if (posX>0){
                    grid[posX][posY] = grid[posX-1][posY];
                    posX--;
                    grid[posX][posY] = 0;
                    moves.add(d);
                    return true;
                }
            case R:
                if (posX<(grid[0].length-1)) {
                    grid[posX][posY] = grid[posX+1][posY];
                    posX++;
                    grid[posX][posY] = 0;
                    moves.add(d);
                    return true;
                }
        }
        return false;
    }


}
