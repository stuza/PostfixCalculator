/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author Stuart McKenzie, 10077518
 */
public class TokenTree {
    
    TokenTree left;
    TokenTree right;
    Token token;
    
    //constructors
    public TokenTree() {
        this.left = null;
        this.right = null;
        this.token = null;
    }
    
    
    public TokenTree(Token t) {
    this.left = null;
    this.right = null;
    this.token = t;
    }
    
    
    public TokenTree(TokenTree right, TokenTree left, Token t) { 
    this.right = right;
    this.left = left;
    this.token = t;
    }
    
    
    //methods
    /**
     * Get the left subtree of this node
     * @return the left subtree
     */
    public TokenTree getLeft() {
        return left;
    }

    /**
     * Get the right subtree of this node
     * @return the right subtree
     */
    public TokenTree getRight() {
        return right;
    }

    /**
     * Get the token at this node
     * @return the Token at this node.
     */
    public Token getToken() {
        return token;
    }

    /**
     * Set the left subtree of this node
     * @param left the left subtree
     */
    public void setLeft(TokenTree left) {
        this.left = left;
    }

    /**
     * Set the right subtree of this node
     * @param right the right to set
     */
    public void setRight(TokenTree right) {
        this.right = right;
    }

    /**
     * Set the token at this node
     * @param token the token to set
     */
    public void setToken(Token token) {
        this.token = token;
    }
    
    /**
     * @return true if has left node.
     */
    public boolean hasLeft() {
        return (this.left != null);
    }
    
    /**
     * @return true if has right node.
     */
    public boolean hasRight() {
        return (this.right != null);
    }
    
}
