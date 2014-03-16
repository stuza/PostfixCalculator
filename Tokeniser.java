package calculator;

import java.text.ParseException;
import java.util.Iterator;


/**
 * Class that can be used to divide a String into a sequence of Tokens
 * @author p0073862
 */
public class Tokeniser implements Iterator<Token> {

    private String expr;//changed from final
    private int index;

    /**
     * Create a Tokeniser that can divide an expression into a sequence of
     * Tokens
     * @param expr the expression to be converted into Tokens 
     */
    public Tokeniser(String expr) {
        this.expr = expr;
        index = 0;
    }

    /**
     * Determine whether we have read the last token in the expression
     * @return true if there is another token to be read from the expression
     * false otherwise.
     */
    @Override
    public boolean hasNext() {
        return hasCurrentChar();
     }

    
    /**
     * Get the next Token
     * @pre hasNext()
     * @return the next token in the expression
     */
    @Override
    public Token next() {
        Token tok;
        consumeWhiteSpace();
        if (Character.isDigit(currentChar())) {
            StringBuilder builder = new StringBuilder();
            while(hasCurrentChar() && Character.isDigit(currentChar())){
                builder.append(currentChar());
                consumeChar();
            }
            int num = Integer.valueOf(builder.toString());
            tok =  new Token(Token.NUMBER, num);
        } else {
            int precedence = 0;
            int type;
            int value;
            switch (currentChar()) {
                case '+':
                    value = Token.PLUS;
                    precedence = 2;
                    type = Token.OPERATOR;
                    break;
                case '-':
                    value = Token.MINUS;
                    precedence = 2;
                    type = Token.OPERATOR;
                    break;
                case '*':
                    value = Token.TIMES;
                    precedence = 3;
                    type = Token.OPERATOR;
                    break;
                case '/':
                    value = Token.DIVIDE;
                    precedence = 3;
                    type = Token.OPERATOR;
                    break;
                case '(':
                    type = Token.PUNCTUATION;
                    value = Token.LPAREN;
                    break;
                case ')':
                    type = Token.PUNCTUATION;
                    value = Token.RPAREN;
                    break;
                default:
                    System.out.println("Error! Unrecognised token");
                    type = -1;
                    value = 0;
                    expr = "";
                    break;
            }
            index++;
            tok = new Token(type, value, precedence);
        }
        consumeWhiteSpace();
        return tok;
    }

    private char currentChar() {
        return expr.charAt(index);
    }
    
    private boolean hasCurrentChar() {
        return index<expr.length();
    }
    
    private void consumeChar() {
        index++;
    }
    
    private void consumeWhiteSpace() {
        while(hasCurrentChar() &&  Character.isSpaceChar(currentChar())) {
            consumeChar();
        }
    }


    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
