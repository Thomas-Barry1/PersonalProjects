import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    FileIO fileIO; //class for handling reading and writing to file

    //Holds the filePath of the file we're using
    private String filePath = "/Users/thomaslb/IdeaProjects/Personal Projects/finances/src/data.txt";
    private final Scanner scan = new Scanner(System.in); //Old code

    private String name = " "; //Name of account holder
    private List<Double> earnings = new ArrayList<Double>(); //Holds earnings
    private List<Double> savings = new ArrayList<Double>(); //Holds savings
    private List<Double> expenses = new ArrayList<Double>(); //Holds expenses

    private double saved = 0; //Keep track of total money in savings
    private double toSpend = 0; //Keep track of total money available to spend
    private double amountEarned = 0; //Keep track of total earnings without expenses
    private final double savingsRate = .2+.2; //Determines percentage of earnings to go in savings.
    //Includes percentage added on for taxes
    private double maxAmount = 1000000; //MaxAmount to add

    private ArrayList<String> buttonStates = new ArrayList<>(List.of(new String[]{"noName", "askInp", "addMon", "spendMon"})); //List of all possible variable states
    private String buttonState = "noName"; //String to keep track of the current state of the button

    /**
     * Constructor for class. Reads in any input from file to the lists and sets name.
     */
    public Main(){
        //Handles creation of the file and adds to these variables.
        fileIO = new FileIO(filePath, this);
    }

    /**
     * Constructor for testing. Reads in any input from file parameter to the lists and sets name.
     */
    public Main(String filePath){
        //Handles creation of the file and adds to these variables.
        this.filePath = filePath;
        fileIO = new FileIO(filePath, this);
    }

    /**
     * Start method for the method, using simple System output and scanners
     */
    public void start() {
        //Set name
        System.out.println("Enter name");
        this.name = scan.nextLine();
        System.out.println("Hello " + name);

        int choice = menu();
        while (choice != -1){
            switch (choice){
                case 1:
                    addMoney();
                    break;
                case 2:
                    System.out.println("$" + String.format("%.2f", getSpendingTotal()));
                    break;
                case 3:
                    System.out.println("$" + String.format("%.2f", getSavingTotal()));
                    break;
                case 4:
                    spend();
                    break;
            }
            choice = menu();
        }
        fileIO.saveToFile();
        System.out.println("Bye " + getName());
    }

    /**
     * @param inputC Input component for getting input
     * @param outputC Output component for outputting information
     *
     *  Sets up menu of the GUI
     */
    public void menuGui(JTextField inputC, JLabel outputC) {

        int choice = -2;
        if(this.name.equals(" ")) { //Set name and ask for choice
            this.name = inputC.getText();
            outputC.setText("Hello " + this.name + ". Please enter an integer choice.");
            buttonState = "askInp";
        }else if(buttonState.equals("addMon")){
            choice = 1;
        } else if (buttonState.equals("spendMon")) {
            choice = 4;
        }else {
            choice = getChoice(inputC, outputC);
        }

        switch (choice) {
            case 1:
                addMoney(inputC, outputC);
                break;
            case 2:
                outputC.setText("Your spending money is: " +
                        "$" + String.format("%.2f", getSpendingTotal()) + ". You can select another " +
                        "option.");
                break;
            case 3:
                outputC.setText("Your total in savings is: " +
                        "$" + String.format("%.2f", getSavingTotal()) + ". You can select another " +
                        "option.");
                break;
            case 4:
                spend(inputC, outputC);
                break;
            case -1: //Ends this program
                outputC.setText("Bye " + getName());
                System.exit(0);
                break;
            default:
                return;
        }
        fileIO.saveToFile();
    }

    /**
     * @param inputC Read from this input
     * @param outputC Write to this output
     * @return Read the input JComponent for the choice
     * Return integer of the user's choice from the menu
     * If the user picked the exit option return -1 instead.
     * If the user entered a wrong value, return -2
     */
    private int getChoice(JTextField inputC, JLabel outputC) {
        int choice = 0;
        int exitInt = 5; //Variable corresponding to exit/final choice.
        try{
            choice = Integer.parseInt(inputC.getText());
        }catch (Exception e){
            choice = -1; //Sets value to be incorrect value for choice for error checking below
        }
        if(choice < 1 || choice > exitInt){
            outputC.setText("Incorrect value for choice entered.");
            choice = -2;
        }
        if (choice == exitInt){ //Change choice to -1 for exit condition
            choice = -1;
        }
        return choice;
    }

    /**
     * @param inputC JComponent to get input from
     * @param outputC JComponent to get output from
     *
     * Depending on button state either:
     *               1. Ask for amount to subtract
     *               2. Pass amount received from input to method addMoney
     */
    private void spend(JTextField inputC, JLabel outputC) {
        switch(buttonState){
            case "askInp":
                outputC.setText("Enter amount to subtract: ");
                buttonState = "spendMon";
                break;
            case "spendMon":
                double amount = Double.parseDouble(inputC.getText());

                if(amount > 0 && amount < maxAmount){ //Error checking
                    expenses.add(amount);
                    toSpend-=amount;
                    buttonState = "askInp";
                    outputC.setText("All set!" + " You can select another " +
                            "option.");
                }else{
                    outputC.setText("Amount entered is not accepted. Reenter value");
                    break;
                }
        }
    }

    /**
     * @param inputC JComponent to get input from
     * @param outputC JComponent to get output from
     *
     * Depending on button state either:
     *               1. Ask for amount to add
     *               2. Pass amount received from input to method addMoney
     */
    private void addMoney(JTextField inputC, JLabel outputC) {
        switch(buttonState){
            case "askInp":
                outputC.setText("Enter amount to add: ");
                buttonState = "addMon";
                break;
            case "addMon":
                double amount = Double.parseDouble(inputC.getText());

                if(amount > 0 && amount < maxAmount){ //Error checking
                    addMoney(amount);
                    buttonState = "askInp";
                    outputC.setText("All set!" + " You select another " +
                            "option.");
                }else{
                    outputC.setText("Amount entered is not accepted. Reenter value");
                    break;
                }
        }
    }

    /**
     * Remove money available to spend. Add to expenses
     */
    public void spend() {
        System.out.println("Enter amount to subtract: ");
        double amount = scan.nextDouble();
        while(amount < 0 || amount > maxAmount){
            System.out.println("Amount entered is not accepted. Reenter value.");
            amount = scan.nextDouble();
        }
        expenses.add(amount);
        toSpend-=amount;
    }

    /**
     * Add money to earnings
     * and take percentage off to put in spending and savings.
     *
     * @calls addMoney
     */
    private void addMoney() {
        System.out.println("Enter amount to add: ");
        double amount = scan.nextDouble();

        //Error checking
        while(amount < 0 || amount > maxAmount){
            System.out.println("Amount entered is not accepted. Reenter value");
            amount = scan.nextDouble();
        }

        addMoney(amount);
    }

    /**
     * Adds amount to earnings, amount earned, savings, and toSpend
     *
     *
     * @throws IllegalArgumentException
     * @param amount to add
     */
    private void addMoney(double amount) {
        if(amount < 0 || amount > maxAmount){
            throw new IllegalArgumentException();
        }
        earnings.add(amount);
        amountEarned+=(amount);
        double save = (double) Math.round(amount*savingsRate*100)/100.0;
        savings.add(save);
        saved+=save;
        toSpend+=amount-save;
    }

    /**
     * @return Return integer from 1 to 4 of the user's choice from the menu
     * If the user picked the exit option return -1 instead.
     */
    private int menu() {
        System.out.println(menuText());
        int choice = 0;
        try{
            choice = Integer.parseInt(scan.next());
            scan.nextLine();
        }catch (Exception e){
            choice = -1; //Sets value to be incorrect value for choice for error checking below
        }
        int exitInt = 5; //Variable corresponding to exit/final choice.
        while(choice < 1 || choice > exitInt){
            System.out.println("Incorrect value for choice entered.");
            System.out.println(menuText());
            try{
                choice = Integer.parseInt(scan.next());
                scan.nextLine();
            }catch (Exception e){
                System.out.println("Not correct");
                choice = -1; //Sets value to be incorrect value for choice for error checking in loop
            }
        }
        if (choice == exitInt){ //Change choice to -1 for exit condition
            choice = -1;
        }
        return choice;
    }

    /**
     * @return Text of the menu options
     */
    public String menuText() {
        return """
                    Would you like to:\s
                    1) Add money to the account?
                    2) See total available spending money?
                    3) See savings money
                    4) Spend money
                    5) Exit""";
    }

//    /**
//     * Deprecated
//     * Increases the size of the count for the number of button presses by 1
//     */
//    private void incrementCountB() {
//        buttonState++;
//    }

//    /**
//     * Deprecated
//     * Increases the size of the count for the number of button presses by 1
//     */
//    private void decrementCountB() {
//        buttonState--;
//    }

    /**
     * Getter for name variable.
     *
     * @return Name global variable
     */
    public String getName() {
        return name;
    }

    public String setName(String name) {
        return this.name = name;
    }

    /**
     * @return Total amount in savings
     */
    public double getSavingTotal() {
        return saved;
    }

    /**
     * @return Total amount of money available to spend
     */
    public double getSpendingTotal() {
        return toSpend;
    }

    /**
     * @return Expenses list
     */
    public List<Double> getExpenses() {
        return expenses;
    }

    /**
     * @return Savings list
     */
    public List<Double> getSavings() {
        return savings;
    }

    /**
     * @return Earnings list
     */
    public List<Double> getEarnings() {
        return earnings;
    }

    /**
     * @return Total Amount Earned without Expenses
     */
    public double getAmountEarned() {
        return amountEarned;
    }

    public void setAmountEarned(double newAmountEarned) {
        this.amountEarned = newAmountEarned;
    }

    public void setSaved(double newSaved) {
        this.saved = newSaved;
    }

    public void setSpendingTotal(double newToSpend) {
        this.toSpend = newToSpend;
    }
}