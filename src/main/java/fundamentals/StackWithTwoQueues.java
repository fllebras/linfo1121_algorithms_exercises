package fundamentals;

import java.util.ArrayDeque;
import java.util.EmptyStackException;
import java.util.Queue;

/**
 * Author: Pierre Schaus and Auguste Burlats
 * Implement the abstract data type stack using two queues
 * You are not allowed to modify or add the instance variables,
 * only the body of the methods
 */
public class StackWithTwoQueues<E> {

    Queue<E> queue1;
    Queue<E> queue2;

    public StackWithTwoQueues() {
        queue1 = new ArrayDeque();
        queue2 = new ArrayDeque();
    }

    /**
     * Looks at the object at the top of this stack
     * without removing it from the stack
     */
    public boolean empty() {
        return queue1.isEmpty();
    }

    /**
     * Returns the first element of the stack, without removing it from the stack
     *
     * @throws EmptyStackException if the stack is empty
     */
    public E peek() throws EmptyStackException {
        if(queue1.isEmpty()){
            throw new EmptyStackException();
        }
        int n = queue1.size();
        while(n>1){
            queue2.add(queue1.remove());
            n--;
        }
        E element = queue1.peek();
        queue2.add(queue1.remove());
        queue1.addAll(queue2);
        queue2.removeAll(queue1);
        return element;
    }

    /**
     * Remove the first element of the stack and returns it
     *
     * @throws EmptyStackException if the stack is empty
     */
    public E pop() throws EmptyStackException {
        if(queue1.isEmpty()){
            throw new EmptyStackException();
        }
        int n = queue1.size();
        while(n>1){
            queue2.add(queue1.remove());
            n--;
        }
        E element = queue1.remove();
        queue1.addAll(queue2);
        queue2.removeAll(queue1);
        return element;
    }

    /**
     * Adds an element to the stack
     *
     * @param item the item to add
     */
    public void push(E item) {
        queue1.add(item);
    }

}