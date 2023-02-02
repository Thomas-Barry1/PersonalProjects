import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner file;
    private final Scanner scan = new Scanner(System.in);

    private String name; //Name of account holder
    private List<Double> earnings = new ArrayList<Double>(); //Holds earnings
    private List<Double> savings = new ArrayList<Double>(); //Holds savings
    private List<Double> expenses = new ArrayList<Double>(); //Holds expenses

    private double saved = 0; //Keep track of total money in savings

    private double toSpend = 0; //Keep track of total money available to spend

    private double amountEarned = 0; //Keep track of total earnings

    final private double savingsRate = .2; //Determines percentage of earnings to go in savings

    /**
     * Constructor for class. Reads in any input from file to the lists and sets name.
     */
    public Main(){
        try {
            file = new Scanner(new File("/Users/thomaslb/IdeaProjects/Personal Projects/finances/src/data.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        readFile(file);
        System.out.println("Enter name");
        this.name = scan.nextLine();
        System.out.println("Hello " + name);
    }

    /**
     * Adds to the earnings, savings, and spending lists from the doubles it
     * gets from the files. Also adds to the variables at the top.
     *
     * @param file to read data from
     */
    private void readFile(Scanner file) {
        String line = file.nextLine();
        if(line.equals("Earnings")){
            line = file.nextLine();
            while(!line.equals("Savings")){
                Double value = Double.parseDouble(line);
                earnings.add(value);
                amountEarned+= value;
                toSpend += value*(1-savingsRate);
                line = file.nextLine();
            }
            line = file.nextLine();
            while(!line.equals("Expenses")){
                Double value = Double.parseDouble(line);
                savings.add(value*savingsRate);
                saved += value;
                line = file.nextLine();
            }
            while(file.hasNextLine()){
                line = file.nextLine();
                Double value = Double.parseDouble(line);
                toSpend -= value;
                expenses.add(value);
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    private void start() {
        int choice = menu();
        while (choice != -1){
            switch (choice){
                case 1:
                    addMoney();
                    break;
                case 2:
                    System.out.println(spendingTotal());
                    break;
                case 3:
                    System.out.println(savingTotal());
                    break;
                case 4:
                    spend();
                    break;
            }
            choice = menu();
        }
        saveToFile();
        System.out.println("Bye " + getName());
    }

    /**
     * Saves data in the global lists to the data file.
     * Splits into "Earnings", "Savings", and "Expenses" sections.
     */
    //TODO
    private void saveToFile() {
        try{
            FileWriter data = new FileWriter("/Users/thomaslb/IdeaProjects/Personal Projects/finances/src/data.txt");
            data.write("Earnings\n");
            for (Double earn : getEarnings()) {
                data.write(earn+"\n");
            }
            data.write("Savings\n");
            for (Double earn : getSavings()) {
                data.write(earn+"\n");
            }
            data.write("Expenses\n");
            for (Double earn : getExpenses()) {
                data.write(earn+"\n");
            }
            data.close();
        }catch (IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Getter for name variable.
     *
     * @return Name global variable
     */
    private String getName() {
        return name;
    }


    /**
     * Remove money available to spend. Add to expenses
     */
    private void spend() {
        System.out.println("Enter amount to subtract: ");
        double amount = scan.nextDouble();
        while(amount < 0 || amount > 1000000){
            System.out.println("Amount entered is not accepted. Reenter value");
            amount = scan.nextDouble();
        }
        expenses.add(amount);
        toSpend-=amount;
    }

    /**
     * @return Total amount in savings
     */
    private double savingTotal() {
        return saved;
    }


    /**
     * @return Total amount of money available to spend
     */
    private double spendingTotal() {
        return toSpend;
    }


    /**
     * Add money to earnings
     * and take percentage off to put in spending and savings.
     */
    private void addMoney() {
        System.out.println("Enter amount to add: ");
        double amount = scan.nextDouble();
        while(amount < 0 || amount > 1000000){
            System.out.println("Amount entered is not accepted. Reenter value");
            amount = scan.nextDouble();
        }
        earnings.add(amount);
        amountEarned+=(amount);
        double save = amount*savingsRate;
        savings.add(save);
        saved+=save;
        toSpend+=amount-save;
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
     * @return Return integer from 1 to 4 of the user's choice from the menu
     * If the user picked the exit option return -1 instead.
     */
    private int menu() {
        System.out.println("""
                    Would you like to:\s
                    1) Add money to the account?
                    2) See total available spending money?
                    3) See savings money
                    4) Spend money
                    5) Exit""");
        int choice = 0;
        try{
            choice = Integer.parseInt(scan.next());
            scan.nextLine();
        }catch (Exception e){
            choice = -1; //Sets value to be incorrect value for choice for error checking below
        }
        while(choice < 1 || choice > 5){
            System.out.println("Incorrect value for choice entered.");
            System.out.println("""
                    Would you like to:\s
                    1) Add money to the account?
                    2) See total available spending money?
                    3) See savings money
                    4) Spend money
                    5) Exit""");
            try{
                choice = Integer.parseInt(scan.next());
                scan.nextLine();
            }catch (Exception e){
                choice = -1; //Sets value to be incorrect value for choice for error checking in loop
            }
        }
        if (choice == 5){ //Change choice to -1 for exit condition
            choice = -1;
        }
        return choice;
    }


    /**
     * @return Expenses list
     */
    public List<Double> getExpenses() {
        return expenses;
    }

    public double getAmountEarned() {
        return amountEarned;
    }
}