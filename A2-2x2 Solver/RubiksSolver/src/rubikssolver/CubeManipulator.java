package rubikssolver;

import java.util.Arrays;
import java.util.Random;

public class CubeManipulator {

    ////// The turnTable attribute
    /// The turnTable attribute is a 2-D array of characters.
    /// It is utilized to show, when a given move is performed,
    /// where the cubelet in a given orientation will be. 
    /// The table's values are as shown below:
    ///     F L U R B D
    /// 0 | 7 0 0 1 0 3
    /// 1 | 0 1 6 2 1 1
    /// 2 | 2 2 1 3 5 2
    /// 3 | 3 3 3 0 2 4
    /// 4 | 4 5 4 4 3 7
    /// 5 | 5 6 2 5 4 5
    /// 6 | 1 7 5 6 6 6
    /// 7 | 6 4 7 7 7 0
    /// For example, when the F move is performed, whatever cubelet which is in the 0 position
    /// will be relocated to the 7 position. At the same time, the cubelet in the 1 position will be
    /// relocated to the 0 position, and so on.
    /// This table forms the basic functionality of the cube; that is, being able to turn. 
    public int[][] turnTable = {{7, 0, 0, 1, 0, 3}, 
                                {0, 1, 6, 2, 1, 1}, 
                                {2, 2, 1, 3, 5, 2},
                                {3, 3, 3, 0, 2, 4},
                                {4, 5, 4, 4, 3, 7},
                                {5, 6, 2, 5, 4, 5},
                                {1, 7, 5, 6, 6, 6},
                                {6, 4, 7, 7, 7, 0}};
    ////// ReverseTable Attribute
    /// This performs the same function for the turnTable attribute; however,
    /// It tracks what position a cubelet will move to when the reverse operation is performed.
    ///     F' L' U' R' B' D'
    /// 0 | 1  0  0  3  0  7
    /// 1 | 6  1  2  0  1  1
    /// 2 | 2  2  5  1  3  2
    /// 3 | 3  3  3  2  4  0
    /// 4 | 4  7  4  4  5  3
    /// 5 | 5  4  6  5  2  5
    /// 6 | 7  5  1  6  6  6
    /// 7 | 0  6  7  7  7  4
    public int[][] reverseTable = {{1, 0, 0, 3, 0, 7},
                                   {6, 1, 2, 0, 1, 1},
                                   {2, 2, 5, 1, 3, 2},
                                   {3, 3, 3, 2, 4, 0},
                                   {4, 7, 4, 4, 5, 3},
                                   {5, 4, 6, 5, 2, 5},
                                   {7, 5, 1, 6, 6, 6},
                                   {0, 6, 7, 7, 7, 4}};

    ////// CubeManipulator Constructor
    /// This is the constructor for this class.
    /// This class handles the alterations of the Cube for the purpose of solving it.
    /// I have decided to use the same face/turn notation used bby cubers, since it was standardized and
    /// easy to remember. They use letters to denote turns about specific faces.
    /// The letters which correspond to each face are as follows:
    /// F = Green face
    /// L = Orange face
    /// U = White face
    /// R = Red face
    /// B = Blue face
    /// D = Yellow face
    /// So, when something is referred to as a "F turn", it will involve turning the cubelets about the
    /// green face (or the face where, when solved, the stickers on the cube are entirely green.) 
    public CubeManipulator() {}

    ////// F-Turn function
    /// The F-Turn function turns the cube about the green face. 
    /// This function changes the cubeletOrder according to the lookup table. 
    /// The currTable parameter is the lookup table to be used.
    /// The cube parameter is whatever cube is currently being altered.
    /// This function can be called in succession in order to perform the turn multiple times.
    public void fTurn(int[][] currTable, Cube cube) {
        char[] newCubeletOrder = {' ', ' ',' ',' ',' ',' ',' ',' '};
        char currCubelet;
        // F-Turn = column 0 of turnTable
        for (int i=0; i<(cube.getCubeletOrder().length); i++) {
            // System.out.println("Current Position: " + i);
            // System.out.println("Cubelet in Position " + i + ": " + cube.getCubeletOrder()[i]);
            currCubelet = cube.getCubeletOrder()[i];
            // System.out.println("New position for " + cube.getCubeletOrder()[i] + ": " + turnTable[i][0]);
            newCubeletOrder[currTable[i][0]] = currCubelet;
            // System.out.println("NewCubeletOrder:" + Arrays.toString(newCubeletOrder));
        }
        cube.setCubeletOrder(newCubeletOrder);
    }
    

    ////// L-Turn function
    /// The L-Turn function turns the cube about the orange face. 
    /// This function takes the cube as a parameter and changes the cubeletOrder according to the 
    /// turnTable lookup table. 
    /// This function can be called in succession in order to perform the turn multiple times.
    public void lTurn(int[][] currTable, Cube cube) {
        char[] newCubeletOrder = {' ', ' ',' ',' ',' ',' ',' ',' '};
        char currCubelet;
        // L-Turn = column 1 of turnTable
        for (int i=0; i<(cube.getCubeletOrder().length); i++) {
            // System.out.println("Current Position: " + i);
            // System.out.println("Cubelet in Position " + i + ": " + cube.getCubeletOrder()[i]);
            currCubelet = cube.getCubeletOrder()[i];
            // System.out.println("New position for " + cube.getCubeletOrder()[i] + ": " + turnTable[i][1]);
            newCubeletOrder[currTable[i][1]] = currCubelet;
            // System.out.println("NewCubeletOrder:" + Arrays.toString(newCubeletOrder));
        }
        cube.setCubeletOrder(newCubeletOrder);
    }

    ////// U-Turn function
    /// The U-Turn function turns the cube about the white face. 
    /// This function takes the cube as a parameter and changes the cubeletOrder according to the 
    /// turnTable lookup table. 
    /// This function can be called in succession in order to perform the turn multiple times.
    public void uTurn(int[][] currTable, Cube cube) {
        char[] newCubeletOrder = {' ', ' ',' ',' ',' ',' ',' ',' '};
        char currCubelet;
        // U-Turn = column 2 of turnTable
        for (int i=0; i<(cube.getCubeletOrder().length); i++) {
            // System.out.println("Current Position: " + i);
            // System.out.println("Cubelet in Position " + i + ": " + cube.getCubeletOrder()[i]);
            currCubelet = cube.getCubeletOrder()[i];
            // System.out.println("New position for " + cube.getCubeletOrder()[i] + ": " + turnTable[i][2]);
            newCubeletOrder[currTable[i][2]] = currCubelet;
            // System.out.println("NewCubeletOrder:" + Arrays.toString(newCubeletOrder));
        }
        cube.setCubeletOrder(newCubeletOrder);
    }

    ////// R-Turn function
    /// The R-Turn function turns the cube about the red face. 
    /// This function takes the cube as a parameter and changes the cubeletOrder according to the 
    /// turnTable lookup table. 
    /// This function can be called in succession in order to perform the turn multiple times.
    public void rTurn(int[][] currTable, Cube cube) {
        char[] newCubeletOrder = {' ', ' ',' ',' ',' ',' ',' ',' '};
        char currCubelet;
        // R-Turn = column 3 of turnTable
        for (int i=0; i<(cube.getCubeletOrder().length); i++) {
            // System.out.println("Current Position: " + i);
            // System.out.println("Cubelet in Position " + i + ": " + cube.getCubeletOrder()[i]);
            currCubelet = cube.getCubeletOrder()[i];
            // System.out.println("New position for " + cube.getCubeletOrder()[i] + ": " + turnTable[i][3]);
            newCubeletOrder[currTable[i][3]] = currCubelet;
            // System.out.println("NewCubeletOrder:" + Arrays.toString(newCubeletOrder));
        }
        cube.setCubeletOrder(newCubeletOrder);
    }

    ////// B-Turn function
    /// The B-Turn function turns the cube about the blue face. 
    /// This function takes the cube as a parameter and changes the cubeletOrder according to the 
    /// turnTable lookup table. 
    /// This function can be called in succession in order to perform the turn multiple times.
    public void bTurn(int[][] currTable, Cube cube) {
        char[] newCubeletOrder = {' ', ' ',' ',' ',' ',' ',' ',' '};
        char currCubelet;
        // B-Turn = column 4 of turnTable
        for (int i=0; i<(cube.getCubeletOrder().length); i++) {
            // System.out.println("Current Position: " + i);
            // System.out.println("Cubelet in Position " + i + ": " + cube.getCubeletOrder()[i]);
            currCubelet = cube.getCubeletOrder()[i];
            // System.out.println("New position for " + cube.getCubeletOrder()[i] + ": " + turnTable[i][4]);
            newCubeletOrder[currTable[i][4]] = currCubelet;
            // System.out.println("NewCubeletOrder:" + Arrays.toString(newCubeletOrder));
        }
        cube.setCubeletOrder(newCubeletOrder);
    }

    ////// D-Turn function
    /// The D-Turn function turns the cube about the yellow face. 
    /// This function takes the cube as a parameter and changes the cubeletOrder according to the 
    /// turnTable lookup table. 
    /// This function can be called in succession in order to perform the turn multiple times.
    public void dTurn(int[][] currTable, Cube cube) {
        char[] newCubeletOrder = {' ', ' ',' ',' ',' ',' ',' ',' '};
        char currCubelet;
        // D-Turn = column 5 of turnTable
        for (int i=0; i<(cube.getCubeletOrder().length); i++) {
            // System.out.println("Current Position: " + i);
            // System.out.println("Cubelet in Position " + i + ": " + cube.getCubeletOrder()[i]);
            currCubelet = cube.getCubeletOrder()[i];
            // System.out.println("New position for " + cube.getCubeletOrder()[i] + ": " + turnTable[i][5]);
            newCubeletOrder[currTable[i][5]] = currCubelet;
            // System.out.println("NewCubeletOrder:" + Arrays.toString(newCubeletOrder));
        }
        cube.setCubeletOrder(newCubeletOrder);
    }

    ////// randomizeCube Function
    /// Randomizes a given cube performing a random set of moves. 
    public Cube randomizeCube(Cube cube) {
        Random rand = new Random();
        int numMoves = rand.nextInt(100);
        int move;
        for (int i=0; i < numMoves; i++) {
            move = rand.nextInt(6);
            if (move == 0) {
                this.fTurn(this.turnTable, cube);
            } else if (move == 1) {
                this.lTurn(this.turnTable, cube);
            } else if (move == 2) {
                this.uTurn(this.turnTable, cube);
            } else if (move == 3) {
                this.rTurn(this.turnTable, cube);
            } else if (move == 4) {
                this.bTurn(this.turnTable, cube);
            } else {
                this.dTurn(this.turnTable, cube);
            }
        }
        return cube;
    }
}
