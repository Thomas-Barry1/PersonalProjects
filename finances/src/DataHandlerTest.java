import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

class DataHandlerTest {

    String testFile = "testFile.txt";

    /**
     * Test dataHandler constructor
     */
    @org.junit.jupiter.api.Test
    void testMain() {
//        InputStream sysInBackup = System.in; // backup System.in to restore it later
//        ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
//        System.setIn(in);

        DataHandler dataHandler = new DataHandler(testFile);
        List<Double> var = dataHandler.getSavings();
        //testFile.delete();
    }

    /**
     * @param testInput
     * Helper function for
     */
    public void setTestFile(String testInput) {
        File testFile = new File(this.testFile);
        testFile.delete();
    }

    @org.junit.jupiter.api.Test
    void testStart() {
        try {
            InputStream sysInBackup = System.in; // backup System.in to restore it later
            ByteArrayInputStream in = new ByteArrayInputStream(("""
                    Thomas
                    2
                    3
                    """).getBytes());
            System.setIn(in);

            DataHandler dataHandler = new DataHandler();
            dataHandler.start();
            in.read("5\n".getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void getSavings() {

    }

    @org.junit.jupiter.api.Test
    void getEarnings() {
    }

    @org.junit.jupiter.api.Test
    void getExpenses() {
    }

    @org.junit.jupiter.api.Test
    void getAmountEarned() {

    }
}