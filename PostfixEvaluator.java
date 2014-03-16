package calculator;

import java.text.ParseException;


/**
 * PostfixEvaluator. This is a class containing just one method,
 * int evaluate(PostfixQueue q) which will evaluate Postfix expressions and 
 * return the result as the nearest integer.
 *  *
 * @author Stuart McKenzie10077518
 */
public class PostfixEvaluator implements Evaluator {
    
    //create our empty stack, s.
    PostfixStack s = new PostfixStack();
    
    
    /**
     * Evaluate a queue of Tokens in Postfix order. This function is recursive.
     *
     * @param q queue of Tokens to be evaluated
     * @return result of evaluating the queue
     * @throws java.text.ParseException if the syntax of the queue is invalid
     */
    @Override
    public int evaluate(TokenQueue q) throws ParseException {
            //if the q is empty, raise an exception with no error message
            if (q.size() == 0){
                throw new ParseException("",0);
            }
            
            //otherwise, while there are still tokens in the queue,
            //Remove a token from the queue and if it is a number push it 
            //onto the stack.
            Token t = q.remove();
            if (t.getCategory() == Token.NUMBER) {
                s.push(t);
            }
            
            //else if the token is an operator there should be at least 2 tokens
            //on the stack, and these tokens should be numbers. If this is not
            //the case then report a syntax error.
            else if (t.getCategory() == Token.OPERATOR) {
                if(s.size() < 2){
                    throw new ParseException("Syntax Error",0);
                }
                 
                //Otherwise remove the top two tokens from  stack S.
                Token u = s.pop();
                Token v = s.pop();
                
                 
                //These tokens will be numbers (but we'll check)
                if ( u.isNumber() == false || v.isNumber() == false ) {
                    throw new ParseException("Syntax Error",1);
                }
                
                //Apply the operators to the numbers these tokens represent then
                //create a token that represents the result of this operation  
                //and push it on to stack s.
                if (t.getValue() == Token.PLUS) {
                    Token resultToken = new Token(Token.NUMBER,(v.getValue()+u.getValue()));
                    s.push(resultToken);
                }
                else if (t.getValue() == Token.MINUS) {
                    Token resultToken = new Token(Token.NUMBER,(v.getValue()-u.getValue()));
                    s.push(resultToken);       
                }
                else if (t.getValue() == Token.TIMES) {
                    Token resultToken = new Token(Token.NUMBER,(v.getValue()*u.getValue()));
                    s.push(resultToken);
                }
                else if (t.getValue() == Token.DIVIDE) {
                    Token resultToken = new Token(Token.NUMBER,(v.getValue()/u.getValue()));
                    s.push(resultToken);
                }
            }
            
            //if there are still tokens in the queue, continue to evaluate them.
            if (q.size() != 0) {
                return evaluate(q);
            }
            
            //There should now be exactly one token on the stack, and this token
            //should be a number. 
            //If this is not the case, then report a syntax error.
            if (s.size() != 1){
                throw new ParseException("Syntax Error",3);
            }
            Token finalresult = s.pop();
            if (!finalresult.isNumber()) {
                throw new ParseException("Syntax Error",4);
            }
            return finalresult.getValue();
    }        
        
    
}
