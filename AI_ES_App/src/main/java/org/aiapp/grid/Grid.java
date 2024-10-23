package org.aiapp.grid;

import org.aiapp.direction.Direction;

import java.util.*;

public class Grid {

    private int width, height;
    private int posX, posY;
    private long depth;
    private double distance;
    private int[][] grid;
    private Direction move;
    private Grid parent;
    private ArrayDeque<Direction> permutations;

    public Grid(int[][] grid, int width, int height, ArrayDeque<Direction> permutations) {
        this.grid = grid;
        this.width = width;
        this.height = height;
        this.permutations = permutations;
        this.move = null;
        getPosXAndPosY();
        this.depth = 0;
        this.distance = 0;
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
        this.permutations = parent.permutations;
        this.depth = parent.depth;
        this.parent = parent;
        if (moveSpace(direction)) {
            this.depth++;
            this.move = direction;
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

    public ArrayList<Direction> visualizeMoves() {
        ArrayList<Direction> moves = new ArrayList<>();
        moves.add(move);
        Grid gridParent = getParent();

        for (int k = 0; k < depth - 1; k++) {
            moves.add(gridParent.getMoves());
            gridParent = gridParent.getParent();
        }

        Collections.reverse(moves);

        return moves;
    }

    public void visualize() {

        ArrayList<int[][]> grids = new ArrayList<>();
        grids.add(grid);
        Grid gridParent = getParent();
        grids.add(gridParent.getGrid());
        for (int k = 0; k < depth - 1; k++) {
            gridParent = gridParent.getParent();
            grids.add(gridParent.getGrid());
        }
        Collections.reverse(grids);
        grids.forEach(grid -> {
            for (int y = 0; y < height; y++) {
                System.out.print("[");
                for (int x = 0; x < width; x++) {
                    if (grid[y][x] == 0) {
                        System.out.print("  ");
                    } else {
                        System.out.print(" " + grid[y][x]);
                    }
                }
                System.out.println(" ]");
            }
            System.out.println();
        });

    }

    public int[][] getGrid() {
        return grid;
    }

    public long getDepth() {
        return depth;
    }

    public void setDistance(double distance) {
        this.distance += distance;
    }

    public double getDistAstEuc() {
        Grid start = getParent();
        for (int i = 0; i < depth - 1; i++) {
            start = start.getParent();
        }
        double distance = 0;
        for (int stY = 0; stY < getWidth(); stY++) {
            for (int stX = 0; stX < getHeight(); stX++) {
                int value1 = start.getGrid()[stY][stX];
                for (int enY = 0; enY < getWidth(); enY++) {
                    for (int enX = 0; enX < getHeight(); enX++) {
                        if (value1 != 0 && value1 == getParent().getGrid()[enY][enX]) {
                            distance += Math.sqrt(Math.pow(stX - enX, 2) + Math.pow(stY - enY, 2));
                        }
                    }
                }
            }
        }
        return distance;
    }

    public double getDistAstMan() {
        Grid start = getParent();
        for (int i = 0; i < depth - 1; i++) {
            start = start.getParent();
        }
        double distance = 0;
        for (int stY = 0; stY < getWidth(); stY++) {
            for (int stX = 0; stX < getHeight(); stX++) {
                int value1 = start.getGrid()[stY][stX];
                for (int enY = 0; enY < getWidth(); enY++) {
                    for (int enX = 0; enX < getHeight(); enX++) {
                        if (value1 != 0 && value1 == getParent().getGrid()[enY][enX]) {
                            distance += Math.abs(stX - enX) + Math.abs(stY - enY);
                        }
                    }
                }
            }
        }
        return distance;
    }

    public Direction getMoves() {
        return move;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Grid getParent() {
        return parent;
    }

    public ArrayDeque<Direction> getPermutationsList() {
        return permutations;
    }

    public Direction getNextPermutation() {
        return permutations.poll();
    }

    public void getPosXAndPosY() {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == 0) {
                    posX = x;
                    posY = y;
                }
            }
        }
    }

    public Direction[] getMoveableDirections() {
        ArrayList<Direction> moves = new ArrayList<>();
        if (posX > 0) {
            moves.add(Direction.L);
        }
        if (posX < this.width - 1) {
            moves.add(Direction.R);
        }
        if (posY > 0) {
            moves.add(Direction.U);
        }
        if (posY < this.height - 1) {
            moves.add(Direction.D);
        }
        return moves.toArray(new Direction[0]);
    }

    private boolean moveSpace(Direction d) {
        switch (d){
            case U:
                if (posY > 0){
                    grid[posY][posX] = grid[posY - 1][posX];
                    posY--;
                    grid[posY][posX] = 0;
                    return true;
                } else {
                    return false;
                }
            case D:
                if (posY < height - 1) {
                    grid[posY][posX] = grid[posY + 1][posX];
                    posY++;
                    grid[posY][posX] = 0;
                    return true;
                } else {
                    return false;
                }
            case L:
                if (posX > 0){
                    grid[posY][posX] = grid[posY][posX - 1];
                    posX--;
                    grid[posY][posX] = 0;
                    return true;
                } else {
                    return false;
                }
            case R:
                if (posX < width - 1) {
                    grid[posY][posX] = grid[posY][posX+1];
                    posX++;
                    grid[posY][posX] = 0;
                    return true;
                } else {
                    return false;
                }
        }
        return false;
    }


}
