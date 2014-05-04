package calculator;

import java.util.Iterator;


/**
 * Class that can be used to divide a String into a sequence of Tokens
 * @author p0073862 & 10077518 
 */
public class Tokeniser implements Iterator<Token> {
    private String expr;//changed from final
    private int index;
    
    /***The Tokeniser expects to be passed a string containing mathematical
     * operators and operands and then outputs them as a * TokenQueue.
     * Each symbol/number is checked to be valid but no attempt is made by 
     * the Tokeniser to validate the expression or to detect a unary minus
     * symbol - those functions are left to the parser. 
     * We limit the size of expr to between 0 and 1024 to stop overflow.
     */
    
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
     * false otherwise. Will return false if expr is longer than 1024 
     * characters to stop overflow. No reason whatsoever behind the choice of
     * 1024.
     */
    @Override
    public boolean hasNext() {
    	if (expr.length() > 0 && expr.length() < 1024) {
            return hasCurrentChar();
    	}
    	else{
            return false;
    	}
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
      
        if (Character.isDigit(currentChar()) || currentChar() == '.') {
            StringBuilder builder = new StringBuilder();
            while(hasCurrentChar() && (Character.isDigit(currentChar()) || currentChar() == '.')) {
                builder.append(currentChar());
                consumeChar();
            }
        try {
            double num = Double.valueOf(builder.toString());
            tok =  new Token(Token.NUMBER, num);
        }
        catch (NumberFormatException numFormExc) {
        	return new Token(-1,0);
        }
        
        } 
        else {
            int precedence = 0;
            int type;
            double value;
            switch (currentChar()) {
                case '-':
                    value = Token.MINUS;
                    precedence = 2;
                    type = Token.OPERATOR;
                    break;
                case '+':
                    value = Token.PLUS;
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
    	if (hasCurrentChar()) {
    		return expr.charAt(index);
    	}
    	return ' ';
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
        System.out.println("Unsupported Operation");
    }
}
