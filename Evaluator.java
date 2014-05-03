package calculator;

import java.text.ParseException;
import java.util.NoSuchElementException;

public class Evaluator {
    //create our empty stack, s.
    TokenStack s = new TokenStack();
    
    
    /**
     * Evaluate a queue of Tokens in Postfix order. This function is recursive.
     *
     * @param q queue of Tokens to be evaluated
     * @return result of evaluating the queue
     * @throws java.text.ParseException if the syntax of the queue is invalid
     */
    public double evaluate(TokenQueue q) throws ParseException, NoSuchElementException {
            //if the q is empty, return 0
            if (q.size() == 0){
                return 0;
            }
            
            //otherwise, while there are still tokens in the queue,
            //Remove a token from the queue and if it is a number push it 
            //onto the stack.
            Token t = q.remove();
               
            if (t.getCategory() == Token.NUMBER) {
                s.push(t);
            }
            
            //else if the token is an operator, detect unary minus.
            //Otherwise there should be at least 2 tokens
            //on the stack, and these tokens should be numbers. If this is not
            //the case then report a syntax error.
            else if (t.getCategory() == Token.OPERATOR) {
            	      	
            	//Detect unary minus
                //if unary minus detected, pop a nummber off the stack and
                //multiply it by -1 then push it back onto the stack. 
                if(t.getValue() == Token.UMINUS) {
                	if (s.isEmpty()) {
                		throw new ParseException("Syntax Error",1);
                	}
                	Token temp = s.pop();
                	if(!temp.isNumber()) {
                		throw new ParseException("Syntax Error",2);
                	}
                	temp.setValue(temp.getValue() * -1);
                	s.push(temp);	
                }
            	
                else{ //Token is an operator, but not a unary minus         	
	                if(s.size() < 2){
	                    throw new ParseException("Syntax Error",3);
	                }
	                 
	                //Otherwise remove the top two tokens from  stack S.
	                Token u = s.pop();
	                Token v = s.pop();
	                              
	                //These tokens will be numbers (but we'll check)
	                if ( !u.isNumber() || !v.isNumber() ) {
	                    throw new ParseException("Syntax Error",4);
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
            }
            
            //if there are still tokens in the queue, continue to evaluate them.
            if (q.size() != 0) {
                return evaluate(q);
            }
            
            //There should now be exactly one token on the stack, and this token
            //should be a number. 
            //If this is not the case, then report a syntax error.
            if (s.size() != 1){
                throw new ParseException("Syntax Error",5);
            }
            Token finalresult = s.pop();
            if (!finalresult.isNumber()) {
                throw new ParseException("Syntax Error",6);
            }
            return finalresult.getValue();
    }
}
