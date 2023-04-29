import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {

    //Holds the file we're reading from and writing to
    private File file;

    Main main; //The main file where we can add information about the variables.

    public FileIO(String filePath, Main main) {
        this.main = main;
        file = new File(filePath);
        try {
            file.createNewFile(); //Creates new file if it doesn't already exit
            readFile(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds to the earnings, savings, and spending lists from the doubles it
     * gets from the files. Also adds to the variables at the top.
     *
     * @param file to read data from
     */
    protected void readFile(File file) throws FileNotFoundException {
        Scanner fileScan = new Scanner(file);
        if(fileScan.hasNextLine() == false){
            return;
        }
        String line = fileScan.nextLine();
        if(line.equals("Earnings")){
            line = fileScan.nextLine();
            //Reading through earnings
            Double finalEarnedValue = 0.0;
            while(!line.equals("Savings")){
                Double value = Double.parseDouble(line);
                main.getEarnings().add(value);
                main.setAmountEarned(main.getAmountEarned()+value);
                //main.toSpend += value*(1-main.savingsRate);
                finalEarnedValue += value;
                line = fileScan.nextLine();
            }
            line = fileScan.nextLine();
            //Reading through savings
            while(!line.equals("Expenses")){
                Double value = Double.parseDouble(line);
                main.getSavings().add(value);
                main.setSaved(main.getSavingTotal()+value);
                finalEarnedValue -= value;
                line = fileScan.nextLine();
            }
            //Reading through expenses
            while(fileScan.hasNextLine()){
                line = fileScan.nextLine();
                Double value = Double.parseDouble(line);
                main.setSpendingTotal(main.getSpendingTotal() - value);
                main.getExpenses().add(value);
            }
            main.setSpendingTotal(main.getSpendingTotal() + finalEarnedValue);
        }
    }

    /**
     * Saves data in the global lists to the data file.
     * Splits into "Earnings", "Savings", and "Expenses" sections.
     */
    protected void saveToFile() {
        try{
            FileWriter data = new FileWriter(file);
            data.write("Earnings\n");
            for (Double earn : main.getEarnings()) {
                data.write(earn+"\n");
            }
            data.write("Savings\n");
            for (Double earn : main.getSavings()) {
                data.write(earn+"\n");
            }
            data.write("Expenses\n");
            for (Double earn : main.getExpenses()) {
                data.write(earn+"\n");
            }
            data.close();
        }catch (IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
