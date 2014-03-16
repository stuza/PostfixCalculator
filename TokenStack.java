/*
 *
 */
package calculator;

import java.util.NoSuchElementException;

/**
 * Interface representing a stack of Tokens
 *
 * @author p0073862
 */
public interface TokenStack {

    /**
     * Returns the number of Tokens in this stack
     *
     * @return the number of Tokens in this stack
     */
    int size();

    /**
     * Returns, but does not remove, the Token at the top of this stack
     *
     * @return the Token at the top of the stack
     * @throws NoSuchElementException if this stack is empty
     */
    Token peek() throws NoSuchElementException;

    /**
     * Returns and removes the Token at the top of the stack
     *
     * @return the Token at the top of the stack
     * @throws NoSuchElementException if this Queue is empty
     */
    Token pop() throws NoSuchElementException;

    /**
     * Adds a Token to the top of this stack
     *
     * @param t Token to be added to the top of the stack
     */
    void push(Token t);

}
