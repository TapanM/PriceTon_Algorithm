/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private Percolation p;
    private double[] result;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        result = new double[trials];
        for (int i = 0; i < trials; i++) {
            p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                if (p.isOpen(row, col)) continue;
                p.open(row, col);
            }
            result[i] = (double) p.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(result);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(result);
    }

    private double parameter() {
        return (1.96 * stddev()) / Math.sqrt(result.length);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - parameter();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + parameter();
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),
                                                   Integer.parseInt(args[1]));
        StdOut.println("mean: " + ps.mean());
        StdOut.println("stddev: " + ps.stddev());
        StdOut.println("95% confidence interval: " + ps.confidenceLo() + ", " + ps.confidenceHi());

    }

}
