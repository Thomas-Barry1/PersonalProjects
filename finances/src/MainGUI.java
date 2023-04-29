import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    Main main;

    public static void main(String[] args) {
//        Main main = new Main();
//        main.start();

        MainGUI mainG = new MainGUI("Finance Tool");
        mainG.start();
    }

    public MainGUI(String title) {
        super(title);
        main = new Main();
    }

    /**
     * Start method to run this GUI program.
     */
    public void start() {
        //Set up frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,300);
        this.setLocationRelativeTo(null);

        //Create panel to add components and then add textArea with menu
        JPanel jP = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(main.menuText());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        //Add input field and submit button
        JTextField jTextField = new JTextField(10);
        JButton button1 = new JButton("Enter");
        JLabel jLabel = new JLabel("Please Enter Your Name!");

        //Set up button
        button1.addActionListener(e -> {
            //Set input and output components for this menuGUI method
            main.menuGui(jTextField, jLabel);
        });

        //Add to a panel
        JPanel jTextP = new JPanel();
        jTextP.add(jTextField);
        jTextP.add(button1);
        jTextP.add(jLabel);

        //Add JComponents and display frame
        jP.add(textArea, BorderLayout.NORTH);
        jP.add(jTextP, BorderLayout.CENTER);
        this.getContentPane().add(BorderLayout.CENTER, jP);
        this.setVisible(true);
    }
}
