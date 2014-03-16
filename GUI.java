package calculator;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Stuart McKenzie 10077518
 * Thanks to p0073862
 */
public class GUI {

    private final JFrame frame = new JFrame("Postfix Calculator");
    private final JTextField entryField = new JTextField(30);


    public GUI() {
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        entryField.setEditable(false);
        contentPane.add(entryField);
        

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,5));
        
        
        //row 1
        for (int i = 7; i <= 9; i++) {
            buttonPanel.add(makeEntryButton(Integer.toString(i)));
        }
        buttonPanel.add(makeDelButton());
        buttonPanel.add(makeClearButton());
        
        
        //row 2
        for (int i = 4; i <= 6; i++) {
            buttonPanel.add(makeEntryButton(Integer.toString(i)));
        }
        buttonPanel.add(makeEntryButton("*"));
        buttonPanel.add(makeEntryButton("/"));
        
        
        //row 3
        for (int i = 1; i <= 3; i++) {
            buttonPanel.add(makeEntryButton(Integer.toString(i)));
        }
        buttonPanel.add(makeEntryButton("+"));
        buttonPanel.add(makeEntryButton("-"));
        
        
        //row 4
        buttonPanel.add(makeEntryButton("0"));
        buttonPanel.add(makeEntryButton(" "));
        buttonPanel.add(makeEqualsButton());

        
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
        entryField.setText(current + s);
    }

    
    private JButton makeEqualsButton() {
        final JButton eqButton = new JButton("=");
        eqButton.addActionListener(new ActionListener() {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //create a Tokeniser and pass it the string in the entry field
                //and add each token into the queue. Then pass that queue to the
                //evaluator which, if given a valid postfix expression, should
                //return an int
                PostfixQueue queue = new PostfixQueue();
                PostfixEvaluator evaluator = new PostfixEvaluator();
                Tokeniser tokeniser = new Tokeniser(entryField.getText());
                while(tokeniser.hasNext()) {
                    queue.add(tokeniser.next());
                }
                try {
                    updateEntry(evaluator.evaluate(queue));
                }
                catch(ParseException exception) {
                    queue = null;
                    evaluator = null;
                    tokeniser = null;
                    updateEntry(exception.getMessage());
                }
            }

        });
        return eqButton;

    }  
    

    private void updateEntry(String s) {
        entryField.setText(s);
    }

    
    private void updateEntry(int i) {
        entryField.setText(Integer.toString(i));
    }
    
    
    private JButton makeClearButton() {
        final JButton clrButton = new JButton("AC");
        clrButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                entryField.setText("");
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
                entryField.setText(text);
                }            
            }

        });
        return clrButton;
    }
}
