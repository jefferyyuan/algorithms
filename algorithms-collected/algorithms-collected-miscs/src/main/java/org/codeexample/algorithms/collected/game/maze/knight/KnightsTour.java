package org.codeexample.algorithms.collected.game.maze.knight;

import java.util.ArrayList;
import java.util.List;

/**
 * Backtracking Knight's tour solver
 * @author Pavel Micka
 */
public class KnightsTour {

    /**
     * Indicator that the square was not visited yet
     */
    private static int NOT_VISITED = -1;
    /**
     * Width of the chessboard
     */
    private int xSize;
    /**
     * Height of the chessboard
     */
    private int ySize;
    /**
     * Numver of solutions
     */
    private int solutionsCount;
    /**
     * Solution board
     * 0 -> Initial position of the knight
     * 1 -> first move
     * 2 -> second move
     * .
     * .
     * .
     * n -> n-th move
     */
    private int[][] solutionBoard;

    public static void main(String[] args) {
    	int ySize = 9;
        for (int i = ySize; i < ySize+1; i++) {
            KnightsTour kt = new KnightsTour(3, i);
            kt.solve();
            System.out.println("<td>"+kt.solutionsCount+"</td>");
        }
    }

    /**
     * Constructor
     * @param xSize width of the chessboard
     * @param ySize height of the chessboard
     */
    public KnightsTour(int xSize, int ySize) {
        solutionsCount = 0;

        this.xSize = xSize;
        this.ySize = ySize;

        solutionBoard = new int[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                solutionBoard[i][j] = NOT_VISITED;
            }
        }
    }

    /**
     * Solve the knight's tour
     */
    public void solve() {
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                takeTurn(j, i, 0);
                solutionBoard[i][j] = NOT_VISITED; //reset the square
            }
        }
    }

    /**
     * Return possible destinations of the knight
     * @param x x coord of the knight
     * @param y y coord of the knight
     * @return possible destinations of the knight
     */
    private List<Coords> getFields(int x, int y) {
        List<Coords> l = new ArrayList<Coords>();
        if (x + 2 < xSize && y - 1 >= 0)
            l.add(new Coords(x + 2, y - 1)); //right and upward
        if (x + 1 < xSize && y - 2 >= 0)
            l.add(new Coords(x + 1, y - 2)); //upward and right
        if (x - 1 >= 0 && y - 2 >= 0)
            l.add(new Coords(x - 1, y - 2)); //upward and left
        if (x - 2 >= 0 && y - 1 >= 0)
            l.add(new Coords(x - 2, y - 1)); //left and upward
        if (x - 2 >= 0 && y + 1 < ySize)
            l.add(new Coords(x - 2, y + 1)); //left and downward
        if (x - 1 >= 0 && y + 2 < ySize)
            l.add(new Coords(x - 1, y + 2)); //downward and left
        if (x + 1 < xSize && y + 2 < ySize)
            l.add(new Coords(x + 1, y + 2)); //downward and right
        if (x + 2 < xSize && y + 1 < ySize)
            l.add(new Coords(x + 2, y + 1)); //right and downward
        return l;
    }

    /**
     * Perform the move
     * @param x destination x coord
     * @param y destination y coord
     * @param turnNr number of the move
     */
    private void takeTurn(int x, int y, int turnNr) {
        solutionBoard[y][x] = turnNr;
        if (turnNr == (xSize * ySize) - 1) {
            solutionsCount++;
            printSolution();
            return;
        } else {
            for (Coords c : getFields(x, y)) {
                if (solutionBoard[c.getY()][c.getX()] == NOT_VISITED) {
                    takeTurn(c.getX(), c.getY(), turnNr + 1);
                    solutionBoard[c.getY()][c.getX()] = NOT_VISITED; //reset the square
                }
            }
        }
    }

    /**
     * Print out the solution
     */
    private void printSolution() {
        System.out.println("Solution #" + solutionsCount);
        for (int i = 0; i < solutionBoard.length; i++) {
            for (int j = 0; j < solutionBoard[i].length; j++) {
                System.out.print(solutionBoard[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    /**
     * @return the solutionsCount
     */
    public int getSolutionsCount() {
        return solutionsCount;
    }    
    
    /**
     * Represents coordinates
     */
    private class Coords {
        private int x;
        private int y;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * @return the x
         */
        public int getX() {
            return x;
        }

        /**
         * @param x the x to set
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * @return the y
         */
        public int getY() {
            return y;
        }

        /**
         * @param y the y to set
         */
        public void setY(int y) {
            this.y = y;
        }
    }
}
