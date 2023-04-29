import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

class MainTest {

    String testFile = "testFile.txt";

    /**
     * Test main constructor
     */
    @org.junit.jupiter.api.Test
    void testMain() {
//        InputStream sysInBackup = System.in; // backup System.in to restore it later
//        ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
//        System.setIn(in);

        Main main = new Main(testFile);
        List<Double> var = main.getSavings();
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

            Main main = new Main();
            main.start();
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