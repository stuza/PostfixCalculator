package calculator;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 *
 * @author Stuart McKenzie 10077518
 */


public class PostfixQueue implements TokenQueue {
    
    LinkedList<Token> queue = new LinkedList<>();
    
     
     /**
     * Adds the specified Token to this queue
     *
     * @param toAdd the Token to add
     */
    @Override
    public void add (Token toAdd){
        queue.addLast(toAdd);
    }
        
        
     /**
     * Retrieves, but does not remove the head of this queue
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this Queue is empty
     */
    @Override
    public Token peek() throws NoSuchElementException {
        return queue.peek();
    }
        
     /**
     * Retrieves and removes the head of this queue
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this Queue is empty
     */
    @Override
    public Token remove() throws NoSuchElementException {
        return queue.removeFirst();
    }
        
     /**
     * Returns the number of Tokens in this queue
     *
     * @return the number of Tokens in this queue
     */
    @Override  
    public int size() {
         return queue.size();
    }
    
    public void clear() {
        queue.clear();
    }
                     
}
