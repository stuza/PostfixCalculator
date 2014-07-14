package calculator;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TokenTreeStack {
    private ArrayList<TokenTree> stack = new ArrayList<TokenTree>();
    
    /**
     * Returns the number of Tokens in this stack
     *
     * @return the number of Tokens in this stack
     */
    public int size() {
        return stack.size();
    }

    /**
     * Returns, but does not remove, the TokenTree at the top of this stack
     *
     * @return the TokenTree at the top of the stack
     * @throws NoSuchElementException if this stack is empty
     */
    public TokenTree peek() throws NoSuchElementException {
        if (stack.size() != 0) {
            return stack.get(stack.size() -1);
        }
        throw new NoSuchElementException();
    }

    /**
     * Returns and removes the TokenTree at the top of the stack
     *
     * @return the TokenTree at the top of the stack
     * @throws NoSuchElementException if this Queue is empty
     */
    public TokenTree pop() throws NoSuchElementException {
        if (stack.size() != 0) {
            return stack.remove(stack.size() - 1);
        }
        throw new NoSuchElementException();
    }

    /**
     * Adds a TokenTree to the top of this stack
     *
     * @param t Token to be added to the top of the stack
     */
    public void push(TokenTree t) {
        stack.add(t);
    }
    
    
    Boolean isEmpty() {
    	return stack.size()==0;
    }
}
