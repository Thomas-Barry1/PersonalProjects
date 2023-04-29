import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Algo {

    public static int[][] matrix;
    public static HashSet<Integer> solutions = new HashSet<>();
    public static void main(String[] args) {
        matrix = new int[][]{{7, 8, 9, 10}, {4, 5, 6, 0}, {2, 3, 0, 0}, {1, 0, 0, 0}};
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.addAll(recurrenceRelation(3,0));
        System.out.println(hashSet);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
    public static ArrayList<Integer> recurrenceRelation(int i, int j){
        ArrayList<Integer> arrayList;
        if(j == matrix.length-1 || i == 0){
            arrayList = new ArrayList<Integer>(0);
            arrayList.add(matrix[i][j]);
        }else {
            arrayList = recurrenceRelation(i-1, j);
            arrayList.addAll(recurrenceRelation(i-1,j+1));
            arrayList.replaceAll(e -> e+matrix[i][j]);
        }
        return arrayList;
    }
}
