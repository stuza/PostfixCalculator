package calculator;

/**
 *
 * @author stuart
 */
import java.text.ParseException;
import java.util.NoSuchElementException;

public class Parser {
        
	TokenQueue output;//used for creating postfix output for evaluation
	
	//iterative function
	public TokenQueue infixToPostfix(TokenQueue queue) throws ParseException, NoSuchElementException {
            
            //if the q is empty, return an empty q 
            if (queue.size() == 0) {
		return new TokenQueue();
            }
               
            //initialise output, stack and last token variable
            output = new TokenQueue();
            TokenStack stack = new TokenStack();
            char lastTokenType = 'x';//used in detection of unary minus
            
            while (queue.size() != 0) {
		//While there are still tokens in the queue,
                //If the token is a number, append it to the postfix output.
                Token t = queue.remove();
		    
		if (t.isNumber()) {
               	    appendToOutput(t);
		    lastTokenType = 'n';       	
                }				  
		    
		// else if the token is an operator
		else if (t.isOperator()) { 		    	
                    //detect unary minus
		    if (t.getValue() == Token.MINUS && (lastTokenType == 'x' || lastTokenType == 'o' || lastTokenType == '(') && lastTokenType != ')') {
		    	t.setValue(Token.UMINUS);
		    	stack.push(t);
		    }
		    else {	
                        //While there is an operator of higher or equal 
		        //precedence than t at the top of the stack, 
		        //pop it off the stack and append it to the output
		   	//then push t onto the stack
                        while (!stack.isEmpty() && stack.peek().getPrecedence() >= t.getPrecedence()) {	    	    	
	    		    appendToOutput(stack.pop());   
                        }
                        stack.push(t);
                    }
		    lastTokenType = 'o';
		}
		   
                //else if the token is an opening bracket then push it onto the stack
		else if (t.isPunctuation() && t.getValue() == Token.LPAREN ) {
              	    stack.push(t);
		    lastTokenType = '(';
		}
		    
		//If the token is a closing bracket, pop operators off the stack 
		//and append them to the output, until the operator at the top
		//of the stack is a opening bracket.
		else if (t.isPunctuation() && t.getValue() == Token.RPAREN) {		    	
		    while (!stack.isEmpty() && stack.peek().isOperator()) {
			appendToOutput(stack.pop());
		    }
		    //Pop the opening bracket off the stack.
		    if(!stack.isEmpty() && stack.peek().isPunctuation() && stack.peek().getValue() == Token.LPAREN) {
		    	stack.pop();	
		    }
		    else {
		    	throw new ParseException("Syntax Error",6);
                    }
		    lastTokenType = ')';
		}		    
	    }
			
            //While there are still operator tokens in the stack
            //Pop the operator on the top of the stack, and append it to the output.
            while (!stack.isEmpty() && stack.peek().isOperator()) {
                    appendToOutput(stack.pop());
            }
          
            //the queue and stack should both be empty but we'll check
            if(queue.size() != 0) {
                    throw new ParseException("Parser Queue not empty!",0);
            }
            if(!stack.isEmpty()) {
                    throw new ParseException("Parser Stack not empty!",0);
            }
            //return our output queue
            return output;                   
        }	
      
        /***
         * 
         * @param queue TokenQueue
         * @return Infix expression as String
         * @throws ParseException
         * @throws NoSuchElementException 
         */
        public String postfixToInfix (TokenQueue queue) throws ParseException, NoSuchElementException {
            if(queue.size() == 0) {
                return "";
            }
            TreeMaker treemaker = new TreeMaker();
            TokenTree tt = treemaker.makeTree(queue);
            return traverse(tt);            
        }
            
        /*** 
         * @param tt TokenTree
         * @return Infix expression as string
         */
        public String traverse (TokenTree tt) {
            if (tt.getToken().isNumber()) {
                return tt.getToken().toString();
            }

            String left = traverse(tt.getLeft());
            if (needsParenOnLeft(tt)) {
                left = '(' + left + ')';
            }

            String right = traverse(tt.getRight());
            if (needsParenOnRight(tt)) {
                right = '(' + right + ')';
            }

            return left + tt.getToken().toString() + right;
        };
	
        
        /*** 
         * @param tt TokenTree
         * @return true if left node is an operator and of a lower precedence
         * than the current operator
         */
        private boolean needsParenOnLeft(TokenTree tt) {
            return ( (tt.getLeft().getToken().isOperator()) && (tt.getLeft().getToken().getPrecedence() < tt.getToken().getPrecedence()) );
        }
        
        
        /***
         * @param tt TokenTree
         * @return true if right node is + or * AND of a lower precedence than
         * the current operator. Otherwise it will return true if the right node
         * is of lower or equal precedence.
         */
        private boolean needsParenOnRight(TokenTree tt) {
            if (tt.getRight().getToken().isNumber()) {
                return false;
            }
            if ( (tt.getToken().isOperator() && tt.getToken().getValue() == Token.PLUS)  ||  (tt.getToken().isOperator() && tt.getToken().getValue() == Token.TIMES) ) {
                return (tt.getRight().getToken().getPrecedence() < tt.getToken().getPrecedence());
            }
            
            return (tt.getRight().getToken().getPrecedence() <= tt.getToken().getPrecedence());
        }
           
        
        private void appendToOutput (Token t) {
		output.add(t);
	}
}
