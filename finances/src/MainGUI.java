import javax.swing.*;
import java.awt.*;


public class MainGUI extends JFrame {
    DataHandler dataHandler;

    public MainGUI(String title) {
        super(title);
        dataHandler = new DataHandler();
    }

    /**
     * Start method to run this GUI program. Add all the GUI elements
     */
    public void start() {
        //Set up frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,300);
        this.setLocationRelativeTo(null);

        //Create panel to add components and then add textArea with menu
        JPanel jP = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(dataHandler.menuText());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        //Add input field and submit button
        JTextField jTextField = new JTextField(10);
        JButton button1 = new JButton("Enter");
        JTextArea jTextArea2 = new JTextArea("Please Enter Your Name!");

        //Set up button
        button1.addActionListener(e -> {
            //Set input and output components for this menuGUI method
            dataHandler.menuGui(jTextField, jTextArea2);
        });

        //DataHandler panel for input
        JPanel jTextP = new JPanel(new FlowLayout());
        jTextArea2.setEditable(false);
        jTextArea2.setLineWrap(true);
        jTextArea2.setWrapStyleWord(true);
        //jTextArea2.setPreferredSize(new Dimension(200,20));

        //Add panel and label to dataHandler panel for input
        //jTextP.add(jTextPSub);
        jTextP.add(jTextField);
        jTextP.add(button1);
        jTextP.add(jTextArea2);

        //Add JComponents and display frame
        jP.add(textArea, BorderLayout.NORTH);
        jP.add(jTextP, FlowLayout.CENTER);
        this.getContentPane().add(BorderLayout.CENTER, jP);
        this.setVisible(true);
    }
}
