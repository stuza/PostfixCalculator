package calculator;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TokenQueue {
    LinkedList<Token> queue = new LinkedList<Token>();
    
    
    /**
     * Adds the specified Token to this queue
     *
     * @param tok the Token to add
     */
    public void add(Token tok) {
    	queue.addLast(tok);
    }

    
    /**
     * Returns the number of Tokens in this queue
     *
     * @return the number of Tokens in this queue
     */
    public int size() {
    	return queue.size();
    }

    
    /**
     * Retrieves, but does not remove the head of this queue
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this Queue is empty
     */
    public Token peek() throws NoSuchElementException {
    	if(queue.size()==0) {
    		throw new NoSuchElementException();
    	}
    	return queue.peek();
    }
    

    /**
     * Retrieves and removes the head of this queue
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this Queue is empty
     */
    public Token remove() throws NoSuchElementException {
    	if(queue.size()==0) {
    		throw new NoSuchElementException();
    	}
    	return queue.removeFirst();
    }
    
    @Override
    public String toString() {
        String output = "";
        while (queue.size() != 0) {
            output+= queue.removeFirst().toString();
        }
        return output;
    }
}
