import java.sql.SQLOutput;
import java.util.Scanner;

public class Algo {
    public static void main(String[] args) {
        System.out.println("Enter n number for f(n)");
        Scanner scan = new Scanner(System.in);
        double value = scan.nextDouble();
        System.out.println(solveForN(value));
    }

    private static double solveForN(double value) {
        double ret = 0;
        for (double i = Math.sqrt(value); i*(Math.log(i) / Math.log(2)) < value; i++) {
            ret = i;
        }
        return ret;
    }
}
