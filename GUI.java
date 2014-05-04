package calculator;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.util.NoSuchElementException;
/**
 *
 * @author Stuart McKenzie 10077518
 */
public class GUI {

    private final JFrame frame = new JFrame("Calculator");
    private final JTextField entryField = new JTextField(30);
    private char mode = 'i';   
    
    
    public GUI() {
               
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
                
        //make the radio buttons and add them to a ButtonGroup
        JRadioButton infixButton = makeInfixButton();
        JRadioButton postfixButton = makePostfixButton();
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(infixButton);
        radioButtonGroup.add(postfixButton);
        //Now add them to a Panel
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1, 2));
        radioPanel.add(infixButton);
        radioPanel.add(postfixButton);
        //Set a border and make add it to the content pane
        radioPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), ""));
        contentPane.add(radioPanel);
        
        //Now create the main input button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6,4));
 
        //row 1
        buttonPanel.add(makeEntryButton("("));      
        buttonPanel.add(makeEntryButton(")"));
        buttonPanel.add(makeDelButton());
        buttonPanel.add(makeClearButton());
               
        //row 2
        for (int i = 7; i <= 9; i++) {
            buttonPanel.add(makeEntryButton(Integer.toString(i)));
        }
        buttonPanel.add(makeEntryButton(" / "));
        
        //row 3
        for (int i = 4; i <= 6; i++) {
            buttonPanel.add(makeEntryButton(Integer.toString(i)));
        }
        buttonPanel.add(makeEntryButton(" * "));
             
        //row 4
        for (int i = 1; i <= 3; i++) {
            buttonPanel.add(makeEntryButton(Integer.toString(i)));
        }
        buttonPanel.add(makeEntryButton(" - "));
               
        //row 5
        buttonPanel.add(makeEntryButton("0"));
        buttonPanel.add(makeEntryButton("."));
        buttonPanel.add(makeEqualsButton());
        buttonPanel.add(makeEntryButton(" + "));
        
        //row 6
        buttonPanel.add(makeEntryButton(" "));

        //Add the button panel to the visiable pane
        contentPane.add(buttonPanel);
        frame.pack();
        frame.setVisible(true);
    }

    
    private JButton makeEntryButton(String entry) {
        final String buttonText = entry;
        final JButton button = new JButton(buttonText);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addToEntry(buttonText);
            }
        });
        return button;
    }


    private void addToEntry(String s) {
        String current = entryField.getText();
        if(current.length() < 30 ) {
            entryField.setText(current + s);
        }
    }

    
    private JButton makeEqualsButton() {
        final JButton eqButton = new JButton("=");
        eqButton.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                TokenQueue queue = new TokenQueue();
                Evaluator evaluator = new Evaluator();
                Tokeniser tokeniser = new Tokeniser(entryField.getText());
                Parser parser = new Parser();
                while(tokeniser.hasNext()) {
                        queue.add(tokeniser.next());
                }
                //create a Tokeniser and pass it the string in the entry field
                //and add each token into the queue. Then pass that queue to the
                //parser which, if given a valid infix expression, should
                //return a queue as a valid postfix expression. This queue is
                //then passed to the evaluator.
                try {
                    if(mode == 'i') {
                        updateEntry(evaluator.evaluate(parser.infixToPostfix(queue))); 
                    }
                    else { //postfix mode
                        updateEntry(evaluator.evaluate(queue));
                    }
                }
                catch(ParseException parseEx) {
                    queue = null;
                    evaluator = null;
                    tokeniser = null;
                    updateEntry(parseEx.getMessage());
                }
                catch(NumberFormatException numForEx) {
                    queue = null;
                    evaluator = null;
                    tokeniser = null;
                    updateEntry(numForEx.getMessage());
                }
                catch(NoSuchElementException elEx) {
                    queue = null;
                    evaluator = null;
                    tokeniser = null;
                    updateEntry(elEx.getMessage());
                }
                catch(UnsupportedOperationException unsOpEx) {
                    queue = null;
                    evaluator = null;
                    tokeniser = null;
                    updateEntry(unsOpEx.getMessage());
                }                
                               
            }

        });
        return eqButton;

    }  
    
    
    private JButton makeClearButton() {
        final JButton clrButton = new JButton("AC");
        clrButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateEntry("");
            }

        });
        return clrButton;
    }

    
    private JButton makeDelButton() {
        final JButton clrButton = new JButton("Del");
        clrButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String text = entryField.getText();
                
                if (text != null && !text.isEmpty()) {
                    text = text.substring(0, text.length() - 1);
                    updateEntry(text);
                }            
            }

        });
        return clrButton;
    }
    
    
    private JRadioButton makeInfixButton () {
        final JRadioButton ifButton = new JRadioButton("Infix",true);
        
        ifButton.addActionListener(new ActionListener() {           
        @Override
            public void actionPerformed(ActionEvent e) {
                //if the Infix button is already selected do nothing.
                if (mode != 'i') {
                    mode = 'i';
                    Parser parser = new Parser();
                    TokenQueue queue = new TokenQueue();
                    Tokeniser tokeniser = new Tokeniser(entryField.getText());
                    while(tokeniser.hasNext()) {
                        queue.add(tokeniser.next());
                    }
                    try {
                        updateEntry(parser.postfixToInfix(queue).toString());
                    }
                    catch(ParseException parseEx) {
                        queue = null;                        
                        tokeniser = null;
                        updateEntry(parseEx.getMessage());
                    }
                    catch(NoSuchElementException elEx) {
                        queue = null;
                        tokeniser = null;
                        updateEntry(elEx.getMessage());
                    }
                    catch(UnsupportedOperationException unsOpEx) {
                        queue = null;
                        tokeniser = null;
                        updateEntry(unsOpEx.getMessage());
                    }
                }
            }
        });
        
        return ifButton;
    }
    
    
    private JRadioButton makePostfixButton () {
        final JRadioButton pfButton = new JRadioButton("Postfix",false);
        
        pfButton.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent e) {        
                //if the Postfix button is already selected do nothing.
                if (mode != 'p') {
                    mode = 'p';
                    TokenQueue queue = new TokenQueue();
                    Tokeniser tokeniser = new Tokeniser(entryField.getText());
                    Parser parser = new Parser();
                    while(tokeniser.hasNext()) {
                        queue.add(tokeniser.next());
                    }
                    try {                 
                        updateEntry(parser.infixToPostfix(queue).toString());                   
                    }
                    catch(ParseException parseEx) {
                        queue = null;                        
                        tokeniser = null;
                        updateEntry(parseEx.getMessage());
                    }
                    catch(NoSuchElementException elEx) {
                        queue = null;
                        tokeniser = null;
                        updateEntry(elEx.getMessage());
                    }
                    catch(UnsupportedOperationException unsOpEx) {
                        queue = null;
                        tokeniser = null;
                        updateEntry(unsOpEx.getMessage());
                    }
                }                       
                               
            }
        });
        return pfButton;
    }
    

    private void updateEntry(String s) {
        entryField.setText(s);
    }

    
    private void updateEntry(double d) {
        entryField.setText(Double.toString(d));
    }
   
    
    
}
