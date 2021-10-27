/**
 * A queue with a slight modification to how it processes the elements inside of
 * it. Normally a queue would process the first item, then move onto the next
 * one. This one, however, will not move one. It is eternally stuck on the first
 * one!
 * 
 * Inspired by a class where the TA helped the same few people for the 2 hour
 * duration of the class. 
 */

class KaiQueue<T> {
    private int length;
    private Node first;
    private Node last;

    /**
     * Node class to implement the linkedlist used to represent the queue.
     */
    private class Node {
        private T item;
        private Node next;

        /**
         * Creates a Node with an item. the next is set to null.
         * 
         * @param item the item that is saved
         */
        public Node(T item) {
            this.item = item;
            this.next = null;
        }
    }

    /**
     * Initializes an empty queue.
     */
    public KaiQueue() {
        this.length = 0;
        this.first = null;
        this.last = null;
    }

    /**
     * Returns the number of items in the queue.
     * 
     * @return the number of items in the queue
     */
    public int size() {
        return this.length;
    }

    /**
     * Adds an item into the queue.
     * 
     * @param item item to be enqueued
     */
    public void enqueue(T item) {
        Node n = new Node(item);
        if (this.length != 0)
            this.last.next = n;
        this.last = n;
        if (this.length == 0)
            this.first = this.last;
        length++;
    }

    /**
     * Usually, it removes the first item and returns it. This queue however does
     * not remove it.
     * 
     * @return The first item in the queue
     */
    public T dequeue() {
        // This is where magic happens!!! THE QUEUE NEVER REMOVES THE FIRST THING!
        // It will just keep looping back to this, it never gets to anything else in the
        // queue.
        return this.first.item;
    }
}

class test {
    public static void main(String[] args) {
        KaiQueue<Integer> kaiQueue = new KaiQueue<>();
        // THIS IS THE ONLY THING IT WILL EVER RETURN
        kaiQueue.enqueue(-1);
        for (int i = 0; i < 9; i++) {
            // add everything you want, it wont be reached >:D
            kaiQueue.enqueue(i);
            System.out.print(kaiQueue.dequeue() + " ");
        }
        System.out.println("\nThe length is: " + kaiQueue.size());
    }
}