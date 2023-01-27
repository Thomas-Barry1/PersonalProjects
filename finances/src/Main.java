import java.util.List;
import java.util.Scanner;

public class Main {
    final private Scanner file = new Scanner("data.txt");
    private final Scanner scan = new Scanner(System.in);

    List earnings;
    List savings;
    List spendings;

    final private double savingsRate = .2;

    public Main(){
        readFile(file);
    }

    private void readFile(Scanner file) {

    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("Hello Thomas");
        int choice = main.menu();
        switch (choice){
            case 1:
                main.addMoney();
                break;
            case 2:
                main.spendingTotal();
                break;
            case 3:
                main.savingTotal();
                break;
            case 4:
                main.spend();
                break;
        }
    }


    /**
     *
     */
    private void spend() {

    }

    /**
     *
     */
    private void savingTotal() {
    }


    /**
     *
     */
    private void spendingTotal() {

    }


    /**
     *
     */
    private void addMoney() {

    }


    /**
     * @return Spendings list
     */
    public List getSpendings() {
        return spendings;
    }

    /**
     * @return Savings list
     */
    public List getSavings() {
        return savings;
    }

    /**
     * @return Earnings list
     */
    public List getEarnings() {
        return earnings;
    }

    /**
     * @return Return integer from 1 to 4 of the user's choice from the menu
     */
    private int menu() {
        System.out.println("Would you like to: \n" +
                "1) Add money to the account?\n" +
                "2) See total available spending money?\n" +
                "3) See savings money\n" +
                "4) Spend money");
        int choice = scan.nextInt();
        while(choice < 1 || choice > 4){
            System.out.println("Incorrect value for choice entered.");
            //
            System.out.println("Would you like to: \n" +
                    "1) Add money to the account?\n" +
                    "2) See total available spending money?\n" +
                    "3) See savings money\n" +
                    "4) Spend money");
            choice = scan.nextInt();
        }
        return choice;
    }


}