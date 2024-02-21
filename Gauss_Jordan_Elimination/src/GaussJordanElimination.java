// Name: Noah Cyr
// Assignment: HW4-Gauss-Jordan Elimination & Dynamic Programming
// File: GaussJordanElimination.java

// ANSWERS TO NON-CODING QUESTIONS ARE IN THE SEPARATE PDF FILE

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class GaussJordanElimination {

    // input is a 2d array of doubles and a 1d array of doubles
    // output is a 2d array of integers
    // output is the system of equations after the Gauss-Jordan Elimination algorithm is performed on it
    // note: if there is no solution to the system of equations then the function will error out
    // note: due to the homework guidelines, we assume the solution of the system is all integers
    public static int[][] gaussJordanElimination(double[][] A, double[] b) {
        int n = A.length; //set n to number of rows in input matrix A
        double[][] augmented = appendAb(A, b); //appends the column vector b to matrix A as the last column in the matrix
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // if value row below ([j][i]) has a larger absolute value than value in row above ([i][i])
                //    then swap row i and row j
                if (Math.abs(augmented[j][i]) > Math.abs(augmented[i][i])) {
                    double[] temp1 = augmented[j];
                    augmented[j] = augmented[i];
                    augmented[i] = temp1;
                }
            }
            for (int j = i + 1; j < n; j++) {
                double temp2 = augmented[j][i] / augmented[i][i]; //set temp value to row below divided by row above
                for (int k = i; k <= n; k++) { //for every value ([i][i]) and below
                    augmented[j][k] = augmented[j][k] - (augmented[i][k]) * temp2; //subtract ([i][i]) times the temp value
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                double temp3 = augmented[j][i] / augmented[i][i]; //set temp value as row above divided by row below
                for (int k = i; k <= n; k++) { //for every value ([i][i]) and above
                    augmented[j][k] = augmented[j][k] - (augmented[i][k]) * temp3; //subtract ([i][i]) times the temp value
                }
            }
        }

        int[][] intAugmented = cleanMatrix(augmented); //cleans matrix and makes it a matrix of ints
        printMatrix(intAugmented); //prints the final system and the solutions of the system in a readable manner
        return intAugmented; //returns final matrix
    }

    //input is a 2d array of doubles and a 1d array of doubles
    //output is a 2d array of doubles
    //note: only works for inputs that the number of arrays in A is equal to the number of values in array b
    public static double[][] appendAb(double[][] A, double[] b) {
        int n = A.length; //sets n to number of rows in A
        double[][] augmented = new double[n][n + 1]; //creates new double matrix with one more column than matrix A
        for (int i = 1; i <= n; i++) { //for every row
            double[] row = Arrays.copyOf(A[i - 1], n + 1); //create copy of row with an extra zero at the end
            row[n] = b[i - 1]; //make that zero at the end the correct value of b
            augmented[i - 1] = row; //add that row to the new double matrix
        }
        return augmented; //return new double matrix
    }

    //input is 2d array of doubles
    //output is 2d array of integers
    //output matrix only has ones on the diagonal and all values are integers
    public static int[][] cleanMatrix(double[][] A) {
        int n = A.length; //rows in inputted matrix
        for (int i = 0; i < n; i++) { //for each row
            double temp4 = 1 / A[i][i]; //sets temp value as 1 / (value on diagonal in that row)
            for (int j = i; j < n + 1; j++) { //for every value in the row
                A[i][j] = A[i][j] * temp4; //multiply that value by the temp value for that row
            }
        }
        int[][] intAugmented = new int[n][n + 1]; //creation of int 2d array with same dimensions as the input double array
        for (int i = 0; i < n; i++) { //for every row
            for (int j = 0; j < A[i].length; j++) { //for every value in row
                intAugmented[i][j] = round(A[i][j], 8); //set the value in the int 2d array as the corresponding value in the double 2d array rounded and cast as an integer
            }
        }
        return intAugmented; //return new int array
    }

    //input is a double value and an integer value
    //output is the inputted double value rounded to the nearest integer
    public static int round(double val, int places) {
        if (places < 0) { //if the number places we are rounding to is negative
            throw new IllegalArgumentException(); //throw exception
        }
        BigDecimal bd = BigDecimal.valueOf(val);
        bd = bd.setScale(places, RoundingMode.HALF_UP); //sets rounding scale using the inputted number of place
        return (int) bd.doubleValue(); //returns rounded double as an INTEGER
    }

    //input is a 2d array of integers
    //prints the matrix in a readable fashion in the output screen
    //also prints the solutions
    //note: 2nd part of the function works best with a diagonal matrix of 1s with the last column as the solutions
    public static void printMatrix(int[][] A) {
        System.out.println("\nThe reduced matrix is displayed:\n");
        for (int[] row : A) { //loop through each row
            for (int j = 0; j < row.length; j++) { //loop through every value in row
                if (j == A.length) { //if j equals the number of rows, then that is the last value in the row and means it is a solution
                    System.out.println("| " + row[j]); //print accordingly
                }
                else {
                    System.out.print(row[j] + " "); //print accordingly
                }
            }
        }
        System.out.println("\nTherefore, the solutions are:");
        for (int[] row : A) { //loop through each row
            for (int j = 0; j < row.length - 1; j++) { //loop through every value in row besides the last (solution)
                if (row[j] == 1) { //if the value in the row is one
                    System.out.println("x" + (j + 1) + "=" + row[row.length - 1]); //then print the corresponding x value and the solution
                }
            }
        }
    }

}
