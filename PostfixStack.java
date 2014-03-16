package calculator;

import java.util.NoSuchElementException;
import java.util.ArrayList;
/**
 *
 * @author Stuart McKenzie 10077518
 */

public class PostfixStack implements TokenStack {
    
    PostfixStackList<Token> stack = new PostfixStackList<>();
    
    public class PostfixStackList<Token> extends ArrayList<Token> {

        public Token pop() throws NoSuchElementException {
          return remove(size() - 1);
        }
        
        public Token peek() throws NoSuchElementException {
            return get(size() -1);
        }

        public void push(Token t) {
          add(t);
        }

    }
  
    
    /**
     * Returns the number of Tokens in this stack
     *
     * @return the number of Tokens in this stack
     */
    @Override
    public int size(){
        return stack.size();
    }
    
    
    /**
     * Returns, but does not remove, the Token at the top of this stack
     *
     * @return the Token at the top of the stack
     * @throws NoSuchElementException if this stack is empty
     */
    @Override
    public Token peek() throws NoSuchElementException{
        return stack.peek();
    }
    
    
    /**
     * Returns and removes the Token at the top of the stack
     *
     * @return the Token at the top of the stack
     * @throws NoSuchElementException if this Queue is empty
     */
    @Override
    public Token pop() throws NoSuchElementException{
        return stack.pop();
    }
   
    
    /**
     * Adds a Token to the top of this stack
     *
     * @param t Token to be added to the top of the stack
     */
    @Override
    public void push(Token t){
        stack.push(t);
    }
   
}
