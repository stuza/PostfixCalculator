/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.NoSuchElementException;

/**
 * Interface representing a queue of Tokens
 */
public interface TokenQueue {

    /**
     * Adds the specified Token to this queue
     *
     * @param tok the Token to add
     */
    void add(Token tok);

    /**
     * Returns the number of Tokens in this queue
     *
     * @return the number of Tokens in this queue
     */
    int size();

    /**
     * Retrieves, but does not remove the head of this queue
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this Queue is empty
     */
    Token peek() throws NoSuchElementException;

    /**
     * Retrieves and removes the head of this queue
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this Queue is empty
     */
    Token remove() throws NoSuchElementException;

}
