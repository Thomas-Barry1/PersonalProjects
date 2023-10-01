import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {

    //Holds the file we're reading from and writing to
    private File file;

    DataHandler dataHandler; //The dataHandler file where we can add information about the variables.

    public FileIO(String filePath, DataHandler dataHandler) {
        this.dataHandler = dataHandler;
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
                dataHandler.getEarnings().add(value);
                dataHandler.setAmountEarned(dataHandler.getAmountEarned()+value);
                //dataHandler.toSpend += value*(1-dataHandler.savingsRate);
                finalEarnedValue += value;
                line = fileScan.nextLine();
            }
            line = fileScan.nextLine();
            //Reading through savings
            while(!line.equals("Expenses")){
                Double value = Double.parseDouble(line);
                dataHandler.getSavings().add(value);
                dataHandler.setSaved(dataHandler.getSavingTotal()+value);
                finalEarnedValue -= value;
                line = fileScan.nextLine();
            }
            //Reading through expenses
            while(fileScan.hasNextLine()){
                line = fileScan.nextLine();
                Double value = Double.parseDouble(line);
                dataHandler.setSpendingTotal(dataHandler.getSpendingTotal() - value);
                dataHandler.getExpenses().add(value);
            }
            dataHandler.setSpendingTotal(dataHandler.getSpendingTotal() + finalEarnedValue);
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
            for (Double earn : dataHandler.getEarnings()) {
                data.write(earn+"\n");
            }
            data.write("Savings\n");
            for (Double earn : dataHandler.getSavings()) {
                data.write(earn+"\n");
            }
            data.write("Expenses\n");
            for (Double earn : dataHandler.getExpenses()) {
                data.write(earn+"\n");
            }
            data.close();
        }catch (IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
