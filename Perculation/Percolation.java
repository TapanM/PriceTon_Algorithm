import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final boolean[][] grid; // matrix
    private final int size;
    private final WeightedQuickUnionUF q;
    private final int top;
    private final int bottom;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        grid = new boolean[size][size];
        q = new WeightedQuickUnionUF(size * size + 2);
        top = 0;
        bottom = size * size + 1;
        openSites = 0;
    }

    private void checkException(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkException(row, col);
        grid[row - 1][col - 1] = true;
        openSites++;

        // if any first row opened
        if (row == 1) {
            q.union(getQuickFindIndex(row, col), top);
        }

        // if any last row opened
        if (row == size) {
            q.union(bottom, getQuickFindIndex(row, col));
        }

        // up
        if (row > 1 && isOpen(row - 1, col)) {
            q.union(getQuickFindIndex(row, col), getQuickFindIndex(row - 1, col));
        }

        // down
        if (row < size && isOpen(row + 1, col)) {
            q.union(getQuickFindIndex(row + 1, col), getQuickFindIndex(row, col));
        }

        // left
        if (col > 1 && isOpen(row, col - 1)) {
            q.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col - 1));
        }

        // right
        if (col < size && isOpen(row, col + 1)) {
            q.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col + 1));
        }

    }

    private int getQuickFindIndex(int row, int col) {
        return size * (row - 1) + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkException(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if ((row > 0 && row <= size) && (col > 0 && col <= size)) {
            return q.find(top) == q.find(getQuickFindIndex(row, col));
        }
        else throw new IllegalArgumentException();
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }


    // does the system percolate?
    public boolean percolates() {
        return q.find(top) == q.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {

    }

}
