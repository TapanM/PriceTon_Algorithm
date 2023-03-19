/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = StdIn.readString();
        String challenger = "";
        int count = 1;
        while (!StdIn.isEmpty()) {
            count++;
            challenger = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / count)) {
                champion = challenger;
            }
        }
        StdOut.println(champion);
    }
}
