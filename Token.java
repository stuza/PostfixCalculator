package calculator;
/**
 * Class representing a Token. A Token may be either a number, or an arithmetic
 * operator, or a piece of punctuation such as a right or left parenthesis.
 *
 * A token is defined by the following three attributes category, value, 
 * and precedence.
 * <p>
 * Its category can be either NUMBER, OPERATOR, or PUNCTUATION.
 * <p>
 * Its value can be either a numeric value(in the case where the category 
 * is NUMBER), one of the constants PLUS,MINUS, TIMES, or DIVIDE (in the case 
 * where the category is OPERATOR), or one of the constants LPAREN or RPAREN 
 * (in the case where the category is PUNCTUATION)
 * <p>
 * The precedence attribute is only relevant when the category is operator.
 *
 * @author David Sutton
 */
public class Token {
    //define some constants. Note that we could do this more neatly using an
    //enum, but we haven't covered enum in class yet.

    //constants representing categories
    public static final int NUMBER = 0;
    public static final int OPERATOR = 1;
    public static final int PUNCTUATION = 2;

    //constants representing the possible values for Tokens whose
    //category is OPERATOR
    public static final int PLUS = 0;
    public static final int MINUS = 1;
    public static final int TIMES = 2;
    public static final int DIVIDE = 3;

    //constants representing the possible values for Tokens whose
    //category is PUNCTUATION
    public static final int LPAREN = 5;
    public static final int RPAREN = 6;

    private final int category;
    private int value;
    private int precedence;

    /**
     * Creates a Token
     * @param category category of the Token
     * @param value value of the Token
     * @param precedence precedence of the Token
     */
    public Token(int category, int value, int precedence) {
        this.category = category;
        this.value = value;
        this.precedence = precedence;
    }

    /**
     * Create a Token with precedence 0
     * @param category category of the Token
     * @param value value of the Token
     */
    Token(int category, int value) {
        this(category, value, 0);
    }

    /**
     * Returns the category of this Token
     * @return category of this Token
     */
    public int getCategory() {
        return category;
    }

    /**
     * Tests whether this Token represents a number
     * @return true if the category of this Token is NUMBER, false otherwise
     */
    public boolean isNumber() {
        return getCategory() == NUMBER;
    }

    /**
     * Tests whether this Token represents an operator
     * @return true if the category of this token is OPERATOR, false otherwise
     */
    public boolean isOperator() {
        return getCategory() == OPERATOR;
    }

    /**
     * Tests whether this Token represents a piece of punctuation
     * @return 
     */
    public boolean isPunctuation() {
        return getCategory() == PUNCTUATION;
    }

    /**
     * Returns the value of this Token
     * @return the value of this Token
     */
    public int getValue() {
        return value;
    }

    /**
     * @pre category==operator
     */
    /**
     * Returns the precedence of this token
     * @return precedence of this token
     */
    public int getPrecedence() {
        return precedence;
    }

    /**
     * Returns a String representation of this Token
     * @return a String representation of this Token
     */
    @Override
    public String toString() {
        if (getCategory() == NUMBER) {
            return " " + value + " ";
        } else {
            switch (value) {
                case PLUS:
                    return " + ";
                case MINUS:
                    return " - ";
                case TIMES:
                    return " * ";
                case DIVIDE:
                    return " / ";
                case LPAREN:
                    return "(";
                case RPAREN:
                    return ")";
                default:
                    return " ? ";
            }

        }
    }
}
