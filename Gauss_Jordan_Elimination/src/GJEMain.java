// Name: Noah Cyr
// Assignment: HW4-Gauss-Jordan Elimination & Dynamic Programming
// File: GJEMain.java

// ANSWERS TO NON-CODING QUESTIONS ARE IN THE SEPARATE PDF FILE

public class GJEMain {
    public static void main(String[] args) {

        double[][] sysA = {{1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,-1,-1,-1,-1},
                {1,-1,1,-1,1,-1,1,-1,1},
                {1,1,0,0,0,0,0,0,0},
                {0,0,1,1,0,0,0,0,0},
                {0,0,0,0,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1},
                {1,1,-1,1,1,-1,1,1,-1},
                {9,-8,7,-6,5,-4,3,-2,1}};
        double[] sysb = {122,-88,32,3,7,18,76,0,41};

        GaussJordanElimination.gaussJordanElimination(sysA, sysb);
    }

}
