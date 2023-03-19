import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 2;
    private Item[] rq;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        rq = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = rq[i];
        }
        rq = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == rq.length) {
            resize(2 * rq.length);
        }
        rq[n++] = item;

    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int removeIdx = StdRandom.uniformInt(n);
        Item item = rq[removeIdx];
        rq[removeIdx] = null;
        n--;
        // shrink size of array
        if (n > 0 && n == rq.length / 4) resize(rq.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue is already empty");
        int getIdx = StdRandom.uniformInt(n);
        return rq[getIdx];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] copy;
        private int sizeC;

        public RandomizedQueueIterator() {
            sizeC = n;
            copy = (Item[]) new Object[sizeC];
            for (int i = 0; i < sizeC; i++) {
                copy[i] = rq[i];
            }
        }

        public boolean hasNext() {
            return sizeC > 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int idx = StdRandom.uniformInt(sizeC);
            Item temp = copy[idx];

            copy[idx] = copy[sizeC - 1];
            copy[sizeC - 1] = null;
            sizeC--;

            return temp;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        String s = "A";
        rq.enqueue(s);
        StdOut.println("Enqueue : " + s);

        s = "B";
        rq.enqueue(s);
        StdOut.println("Enqueue : " + s);

        StdOut.println("Dequeue : " + rq.dequeue());

        StdOut.println("Sample: " + rq.sample());

        s = "C";
        rq.enqueue(s);
        StdOut.println("Enqueue : " + s);

        StdOut.println("Iterating Randomized Queue.....");
        for (String item : rq) {
            StdOut.print(item + " ");
        }
    }

}
