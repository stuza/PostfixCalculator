package calculator;

/**
 *
 * @author Stuart McKenzie 10077518
 */
public class Calculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        createAndShowGui();
                    }
                }
        );
    }
    
    
    private static void createAndShowGui() {    
        GUI view = new GUI();
    }
    
    
}
