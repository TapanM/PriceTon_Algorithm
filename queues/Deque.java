import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> pre;
    }

    private Node<Item> head;
    private Node<Item> tail;
    private int n;

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Not a Valid Argument");
        Node<Item> newNode = new Node<>();
        newNode.item = item;
        newNode.next = head;
        newNode.pre = null;
        if (isEmpty()) {
            tail = newNode;
        } else {
            head.pre = newNode;
        }
        head = newNode;
        n++;
    }


    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Not a Valid Argument");
        Node<Item> newNode = new Node<>();
        newNode.item = item;
        newNode.pre = tail;
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is already empty");
        Item del = head.item;
        head = head.next;
        n--;
        return del;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is already empty");
        Item del = tail.item;
        tail = tail.pre;
        n--;
        return del;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator(head);
    }

    private class DequeIterator implements Iterator<Item> {
        Node<Item> temp;

        public DequeIterator(Node<Item> head) {
            temp = head;
        }

        public boolean hasNext() {
            return temp != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = temp.item;
            temp = temp.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Method Not Supported");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> dq = new Deque<>();

        String s = "Mandal";
        dq.addFirst(s);
        StdOut.println("Adding element in front of Deque: " + s);

        s = "Tapan";
        dq.addFirst(s);
        StdOut.println("Adding element in front of Deque: " + s);

        s = ",I am a Good Boy";
        dq.addLast(s);
        StdOut.println("Adding element in end of Deque: " + s);

        s = "Hi there,";
        dq.addFirst(s);
        StdOut.println("Adding element in front of Deque: " + s);

        StdOut.println("Deleting element in front of queue: " + dq.removeFirst());
        StdOut.println("Deleting element in end of queue: " + dq.removeLast());

        StdOut.println("Dequeue iterating....");
        Iterator<String> it = dq.iterator();
        while (it.hasNext()) {
            StdOut.print(it.next() + " ");
        }
    }

}
