package fundamentals;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Author Pierre Schaus
 *
 * We are interested in the implementation of a circular simply linked list,
 * i.e. a list for which the last position of the list refers, as the next position,
 * to the first position of the list.
 *
 * The addition of a new element (enqueue method) is done at the end of the list and
 * the removal (remove method) is done at a particular index of the list.
 *
 * A (single) reference to the end of the list (last) is necessary to perform all operations on this queue.
 *
 * You are therefore asked to implement this circular simply linked list by completing the class see (TODO's)
 * Most important methods are:
 *
 * - the enqueue to add an element;
 * - the remove method [The exception IndexOutOfBoundsException is thrown when the index value is not between 0 and size()-1];
 * - the iterator (ListIterator) used to browse the list in FIFO.
 *
 * @param <Item>
 */
public class CircularLinkedList<Item> implements Iterable<Item> {

    private long nOp = 0; // count the number of operations
    private int n;          // size of the stack
    private Node  last;   // trailer of the list

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    public CircularLinkedList() {
        // TODO initialize instance variables
        n = 0;
        last = null;
    }

    public boolean isEmpty() {
        // TODO
        if(n==0){
            return true;
        }
        return false;
    }

    public int size() {
        // TODO
        return n;
    }

    private long nOp() {
        return nOp;
    }



    /**
     * Append an item at the end of the list
     * @param item the item to append
     */
    public void enqueue(Item item) {
        // TODO
        if(n==0){
            last = new Node();
            last.item = item;
            last.next = last;
        }
        else{
            Node node = new Node();
            node.item = item;
            node.next = last.next;
            last.next = node;
            last = node;
        }
        n++;
        nOp++;

    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     */
    public Item remove(int index) {
        // BEGIN STUDENT return null;
        if(index<0 || index>n-1){
            throw new IndexOutOfBoundsException();
        }
        Node node = last;
        Node previous;
        Item element;
        if(n==1){
            element = last.item;
            last = null;
        }
        else{
            previous = node;
            for(int i = 0; i<=index; i++){
                previous = node;
                node = node.next;
            }
            element = node.item;
            previous.next = node.next;
            if(index == n-1){
                last = previous;
            }
        }
        n--;
        nOp++;
        return element;
    }


    /**
     * Returns an iterator that iterates through the items in FIFO order.
     * @return an iterator that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * Implementation of an iterator that iterates through the items in FIFO order.
     * The iterator should implement a fail-fast strategy, that is ConcurrentModificationException
     * is thrown whenever the list is modified while iterating on it.
     * This can be achieved by counting the number of operations (nOp) in the list and
     * updating it everytime a method modifying the list is called.
     * Whenever it gets the next value (i.e. using next() method), and if it finds that the
     * nOp has been modified after this iterator has been created, it throws ConcurrentModificationException.
     */
    private class ListIterator implements Iterator<Item> {

        // TODO You probably need a constructor here and some instance variables
        Node current;
        final long i = nOp;
        public ListIterator(){
            if(n==0){
                current = null;
            } else{
                current = last.next;
            }
        }


        @Override
        public boolean hasNext() {
            // BEGIN STUDENT return false;
            return current != null;
        }

        @Override
        public Item next() {
            // BEGIN STUDENT return null;
            if(i != nOp){
                throw new ConcurrentModificationException();
            }
            Item item = current.item;
            if(current == last){
                current = null;
            } else{
                current = current.next;
            }
            return item;
        }

    }

}