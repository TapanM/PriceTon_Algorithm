import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int c = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String temp = StdIn.readString();
            rq.enqueue(temp);
        }
        for (int i = 0; i < c; i++) {
            StdOut.println(rq.dequeue());
        }

    }
}
