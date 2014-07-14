package calculator;
/**
 *
 * @author Stuart McKenzie, 10077518
 */
import java.text.ParseException;
import java.util.NoSuchElementException;

public class TreeMaker {
    /**
     * 
     * @param q A queue of tokens in postfix order
     * @return A TokenTree representing this expression. There is an example
     * of such a tree in the coursework documentation
     * 
     * @throws ParseException This exception should only be thrown if the
     * queue contains a syntactically invalid expression. However the method
     * is not required to throw an exception for every syntactically invalid
     * expression.
     */
    public TokenTree makeTree (TokenQueue q) throws ParseException, NoSuchElementException {
        TokenTreeStack s = new TokenTreeStack();
          
        if (q.size() == 0) {
            return new TokenTree();
        }
        
        //While there are still tokens in the queue
        while (q.size() != 0) {
            //Remove a token from the queue
            Token t = q.remove();

            //If this token is a number then create a TokenTree whose root node
            //is this number, and whose left and right branches  are null.
            //Push this TokenTree on to the stack.
            if (t.isNumber()) {
                s.push(new TokenTree(t));
            }

            //If the token is an operator then pop the top two TokenTrees from 
            //stack S. Create a new TokenTree whose root node is the operator
            //token that you just removed from the queue, and whose left and
            //right branches are the two TokenTrees that you just removed from
            //the stack. Push this TokenTree on to the stack. If you find that you
            //cannot pop two tokens because there are not enough elements
            //on the stack then throw an exception.
            else if(t.isOperator()) {
                s.push(new TokenTree(s.pop(),s.pop(),t));
            }
            else {
                throw new ParseException("Syntax Error", 21);
            }
        }
               
        //There should now be exactly one TokenTree on the stack.  
        //If there is not, then throw an exception. 
        //Pop the TokenTree on top of the stack. 
        //This will be a tree representation of your postfix expression.
        if (s.size() == 1) {
            return s.pop();
        }
        throw new ParseException("Syntax Error", 22);
    }   
}