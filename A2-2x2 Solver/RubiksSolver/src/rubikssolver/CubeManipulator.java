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
    public void fTurn(Cube cube) {
        String[] newCubeletOrder = {" ", " ", " ", " ", " ", " ", " ", " "};
        String[] currCubeletOrder = cube.getCubeletOrder();

        // Handle cubelet at position 0
        if (currCubeletOrder[0].charAt(1) == '1') { 
            // Orientation 1    
            newCubeletOrder[7] = currCubeletOrder[0].charAt(0) + "3";
        } else if (currCubeletOrder[0].charAt(1) == '2') {  
            // Orientation 2
            newCubeletOrder[7] = currCubeletOrder[0].charAt(0) + "2";
        } else {
            // Orientation 3
            newCubeletOrder[7] = currCubeletOrder[0].charAt(0) + "1";
        }
        // Handle cubelet at position 1
        if (currCubeletOrder[1].charAt(1) == '1') {
            // Orientation 1
            newCubeletOrder[0] = currCubeletOrder[1].charAt(0) + "3";
        } else if (currCubeletOrder[1].charAt(1) == '2') {
            // Orientation 2
            newCubeletOrder[0] = currCubeletOrder[1].charAt(0) + "2";
        } else {
            // Orientation 3
            newCubeletOrder[0] = currCubeletOrder[1].charAt(0) + "1";
        }
        // Handle cubelet at position 2, this is unchanged
        newCubeletOrder[2] = currCubeletOrder[2];
        // Handle cubelet at position 3, this is unchanged
        newCubeletOrder[3] = currCubeletOrder[3];
        // Handle cubelet at position 4, this is unchanged
        newCubeletOrder[4] = currCubeletOrder[4];
        // Handle cubelet at position 5, this cubelet is fixed
        newCubeletOrder[5] = currCubeletOrder[5];
        // Handle cubelet at position 6
        if (currCubeletOrder[6].charAt(1) == '1') {
            // Orientation 1
            newCubeletOrder[1] = currCubeletOrder[6].charAt(0) + "3";
        } else if (currCubeletOrder[6].charAt(1) == '2') {
            // Orientation 2
            newCubeletOrder[1] = currCubeletOrder[6].charAt(0) + "2";
        } else {
            // Orientation 3
            newCubeletOrder[1] = currCubeletOrder[6].charAt(0) + "1";
        }
        // Handle cubelet at position 7
        if (currCubeletOrder[7].charAt(1) == '1') {
            // Orientation 1
            newCubeletOrder[6] = currCubeletOrder[7].charAt(0) + "3";
        } else if (currCubeletOrder[7].charAt(1) == '2') {
            // Orientation 2
            newCubeletOrder[6] = currCubeletOrder[7].charAt(0) + "2";
        } else {
            // Orientation 3
            newCubeletOrder[6] = currCubeletOrder[7].charAt(0) + "1";
        }
        cube.setCubeletOrder(newCubeletOrder);
    }

    public void negFTurn(Cube cube) {
        String[] newCubeletOrder = {" ", " ", " ", " ", " ", " ", " ", " "};
        String[] currCubeletOrder = cube.getCubeletOrder();

        // Handle cubelet at position 0, goes to position 1
        if (currCubeletOrder[0].charAt(1) == '1') {
            // Orientation 1, goes to orientation 3
            newCubeletOrder[1] = currCubeletOrder[0].charAt(0) + "3";
        } else if (currCubeletOrder[0].charAt(1) == '2') {
            // Orientation 2, goes to orientation 2
            newCubeletOrder[1] = currCubeletOrder[0].charAt(0) + "2";
        } else {
            // Orientation 3, goes to orientation 1
            newCubeletOrder[1] = currCubeletOrder[0].charAt(0) + "1";
        }
        // Handle cubelet at position 1, goes to position 6
        if (currCubeletOrder[1].charAt(1) == '1') {
            // Orientation 1, goes to orientation 3
            newCubeletOrder[6] = currCubeletOrder[1].charAt(0) + "3";
        } else if (currCubeletOrder[0].charAt(1) == '2') {
            // Orientation 2, goes to orientation 2
            newCubeletOrder[6] = currCubeletOrder[1].charAt(0) + "2";
        } else {
            // Orientation 3, goes to orientation 1
            newCubeletOrder[6] = currCubeletOrder[1].charAt(0) + "1";
        }
        // Handle cubelet at position 2, unchanged
        newCubeletOrder[2] = currCubeletOrder[2];
        // Handle cubelet at position 3, unchanged
        newCubeletOrder[3] = currCubeletOrder[3];
        // Handle cubelet at position 4, unchanged
        newCubeletOrder[4] = currCubeletOrder[4];
        // Handle cubelet at position 5, fixed cubelet
        newCubeletOrder[5] = currCubeletOrder[5];
        // Handle cubelet at position 6, goes to position 7
        if (currCubeletOrder[6].charAt(1) == '1') {
            // Orientation 1, goes to orientation 3
            newCubeletOrder[7] = currCubeletOrder[6].charAt(0) + "3";
        } else if (currCubeletOrder[6].charAt(1) == '2') {
            // Orientation 2, goes to orientation 2
            newCubeletOrder[7] = currCubeletOrder[6].charAt(0) + "2";
        } else {
            // Orientation 3, goes to orientation 1
            newCubeletOrder[7] = currCubeletOrder[6].charAt(0) + "1";
        }
        // Handle cubelet at position 7, goes to position 0
        if (currCubeletOrder[7].charAt(1) == '1') {
            // Orientation 1, goes to orientation 3
            newCubeletOrder[0] = currCubeletOrder[7].charAt(0) + "3";
        } else if (currCubeletOrder[7].charAt(1) == '2') {
            // Orientation 2, goes to orientation 2
            newCubeletOrder[0] = currCubeletOrder[7].charAt(0) + "2";
        } else {
            // Orientation 3, goes to orientation 1
            newCubeletOrder[0] = currCubeletOrder[7].charAt(0) + "1";
        }
        cube.setCubeletOrder(newCubeletOrder);
    }

    public void dTurn(Cube cube) {
        String[] newCubeletOrder = {" ", " ", " ", " ", " ", " ", " ", " "};
        String[] currCubeletOrder = cube.getCubeletOrder();

        // Handle cubelet at position 0, goes to position 3
        if (currCubeletOrder[0].charAt(1) == '1') {
            // Orientation 1, goes to orientation 2
            newCubeletOrder[3] = currCubeletOrder[0].charAt(0) + "2";
        } else if (currCubeletOrder[0].charAt(1) == '2') {
            // Orientation 2, goes to orientation 1
            newCubeletOrder[3] = currCubeletOrder[0].charAt(0) + "1";
        } else {
            // Orientation 3, goes to orientation 3
            newCubeletOrder[3] = currCubeletOrder[0].charAt(0) + "3";
        }
        // Handle cubelet at position 1, unchanged
        newCubeletOrder[1] = currCubeletOrder[1]; 
        // Handle cubelet at position 2, unchanged
        newCubeletOrder[2] = currCubeletOrder[2];
        // Handle cubelet at position 3, goes to position 4
        if (currCubeletOrder[3].charAt(1) == '1') {
            // Orientation 1, goes to orientation 2
            newCubeletOrder[4] = currCubeletOrder[3].charAt(0) + "2";
        } else if (currCubeletOrder[3].charAt(1) == '2') {
            // Orientation 2, goes to orientation 1
            newCubeletOrder[4] = currCubeletOrder[3].charAt(0) + "1";
        } else {
            // Orientation 3, goes to orientation 3
            newCubeletOrder[4] = currCubeletOrder[3].charAt(0) + "3";
        }
        // Handle cubelet at position 4, goes to position 7
        if (currCubeletOrder[4].charAt(1) == '1') {
            // Orientation 1, goes to orientation 2
            newCubeletOrder[7] = currCubeletOrder[4].charAt(0) + "2";
        } else if (currCubeletOrder[4].charAt(1) == '2') {
            // Orientation 2, goes to orientation 1
            newCubeletOrder[7] = currCubeletOrder[4].charAt(0) + "1";
        } else {
            // Orientation 3, goes to orientation 3
            newCubeletOrder[7] = currCubeletOrder[4].charAt(0) + "3";
        }
        // Handle cubelet at position 5, fixed cubelet
        newCubeletOrder[5] = currCubeletOrder[5];
        // Handle cubelet at position 6, unchanged
        newCubeletOrder[6] = currCubeletOrder[6];
        // Handle cubelet at position 7, goes to position 0
        if (currCubeletOrder[7].charAt(1) == '1') {
            // Orientation 1, goes to orientation 2
            newCubeletOrder[0] = currCubeletOrder[7].charAt(0) + "2";
        } else if (currCubeletOrder[7].charAt(1) == '2') {
            // Orientation 2, goes to orientation 1
            newCubeletOrder[0] = currCubeletOrder[7].charAt(0) + "1";
        } else {
            // Orientation 3, goes to orientation 3
            newCubeletOrder[0] = currCubeletOrder[7].charAt(0) + "3";
        }
        cube.setCubeletOrder(newCubeletOrder);
    }
    ////// randomizeCube Function
    /// Randomizes a given cube performing a random set of moves. 
    public Cube randomizeCube(Cube cube) {
        return cube;
    }
}
