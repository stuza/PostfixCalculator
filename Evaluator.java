package calculator;

import java.text.ParseException;

/**
 * Interface to be implemented by classes that can evaluate queues of Token
 * @author p0073862
 */
public interface Evaluator {

    /**
     * Evaluate a queue of Tokens. The order of the Tokens is to be specified
     * by implementing classes. Some classes might evaluate a queue in postfix
     * order, some in infix order, and so on 
     *
     * @param q queue of Tokens to be evaluated
     * @return result of evaluating the queue
     * @throws java.text.ParseException if the syntax of the queue is invalid
     */
    int evaluate(TokenQueue q) throws ParseException;
    
    
}
