package rubikssolver;

import java.util.Arrays;
import java.util.Random;

public class CubeManipulator {

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
    public void negDTurn(Cube cube) {
        String[] newCubeletOrder = {" ", " ", " ", " ", " ", " ", " ", " "};
        String[] currCubeletOrder = cube.getCubeletOrder();

        // Handle cubelet at position 0, goes to position 7
        if (currCubeletOrder[0].charAt(1) == '1') {
            // Orientation 1, goes to orientation 2
            newCubeletOrder[7] = currCubeletOrder[0].charAt(0) + "2";
        } else if (currCubeletOrder[0].charAt(1) == '2') {
            // Orientation 2, goes to orientation 1
            newCubeletOrder[7] = currCubeletOrder[0].charAt(0) + "1";
        } else {
            // Orientation 3, goes to orientation 3
            newCubeletOrder[7] = currCubeletOrder[0].charAt(0) + "3";
        }
        // Handle cubelet at position 1, unchanged
        newCubeletOrder[1] = currCubeletOrder[1];
        // Handle cubelet at position 2, unchanged
        newCubeletOrder[2] = currCubeletOrder[2];
        // Handle cubelet at position 3, goes to position 0
        if (currCubeletOrder[3].charAt(1) == '1') {
            // Orientation 1, goes to orientation 2
            newCubeletOrder[0] = currCubeletOrder[3].charAt(0) + "2";
        } else if (currCubeletOrder[3].charAt(1) == '2') {
            // Orientation 2, goes to orientation 1
            newCubeletOrder[0] = currCubeletOrder[3].charAt(0) + "1";
        } else {
            // Orientation 3, goes to orientation 3
            newCubeletOrder[0] = currCubeletOrder[3].charAt(0) + "3";
        }
        // Handle cubelet at position 4, goes to position 3
        if (currCubeletOrder[4].charAt(1) == '1') {
            // Orientation 1, goes to orientation 2
            newCubeletOrder[3] = currCubeletOrder[4].charAt(0) + "2";
        } else if (currCubeletOrder[4].charAt(1) == '2') {
            // Orientation 2, goes to orientation 1
            newCubeletOrder[3] = currCubeletOrder[4].charAt(0) + "1";
        } else {
            // Orientation 3, goes to orientation 3
            newCubeletOrder[3] = currCubeletOrder[4].charAt(0) + "3";
        }
        // Handle cubelet at position 5, fixed cubelet
        newCubeletOrder[5] = currCubeletOrder[5];
        // Handle cubelet at position 6, unchanged
        newCubeletOrder[6] = currCubeletOrder[6];
        // Handle cubelet at position 7, to position 4
        if (currCubeletOrder[7].charAt(1) == '1') {
            // Orientation 1, goes to orientation 2
            newCubeletOrder[4] = currCubeletOrder[7].charAt(0) + "2";
        } else if (currCubeletOrder[7].charAt(1) == '2') {
            // Orientation 2, goes to orientation 1
            newCubeletOrder[4] = currCubeletOrder[7].charAt(0) + "1";
        } else {
            // Orientation 3, goes to orientation 3
            newCubeletOrder[4] = currCubeletOrder[7].charAt(0) + "3";
        }
        cube.setCubeletOrder(newCubeletOrder);
    }

    public void rTurn(Cube cube) {
        String[] newCubeletOrder = {" ", " ", " ", " ", " ", " ", " ", " "};
        String[] currCubeletOrder = cube.getCubeletOrder();

        // Handle cubelet at position 0, to position 1
        if (currCubeletOrder[0].charAt(1) == '1') {
            // Orientation 1, goes to orientation 1
            newCubeletOrder[1] = currCubeletOrder[0].charAt(0) + "1";
        } else if (currCubeletOrder[0].charAt(1) == '2') {
            // Orientation 2, goes to orientation 3
            newCubeletOrder[1] = currCubeletOrder[0].charAt(0) + "3";
        } else {
            // Orientation 3, goes to orientation 2
            newCubeletOrder[1] = currCubeletOrder[0].charAt(0) + "2";
        }
        // Handle cubelet at position 1, to position 2
        if (currCubeletOrder[1].charAt(1) == '1') {
            // Orientation 1, goes to orientation 1
            newCubeletOrder[2] = currCubeletOrder[1].charAt(0) + "1";
        } else if (currCubeletOrder[1].charAt(1) == '2') {
            // Orientation 2, goes to orientation 3
            newCubeletOrder[2] = currCubeletOrder[1].charAt(0) + "3";
        } else {
            // Orientation 3, goes to orientation 2
            newCubeletOrder[2] = currCubeletOrder[1].charAt(0) + "2";
        }
        // Handle cubelet at position 2, goes to position 3
        if (currCubeletOrder[2].charAt(1) == '1') {
            // Orientation 1, goes to orientation 1
            newCubeletOrder[3] = currCubeletOrder[2].charAt(0) + "1";
        } else if (currCubeletOrder[2].charAt(1) == '2') {
            // Orientation 2, goes to orientation 3
            newCubeletOrder[3] = currCubeletOrder[2].charAt(0) + "3";
        } else {
            // Orientation 3, goes to orientation 2
            newCubeletOrder[3] = currCubeletOrder[2].charAt(0) + "2";
        }
        // Handle cubelet at position 3, goes to position 0
        if (currCubeletOrder[3].charAt(1) == '1') {
            // Orientation 1, goes to orientation 1
            newCubeletOrder[0] = currCubeletOrder[3].charAt(0) + "1";
        } else if (currCubeletOrder[3].charAt(1) == '2') {
            // Orientation 2, goes to orientation 3
            newCubeletOrder[0] = currCubeletOrder[3].charAt(0) + "3";
        } else {
            // Orientation 3, goes to orientation 2
            newCubeletOrder[0] = currCubeletOrder[3].charAt(0) + "2";
        }
        // handle cubelet at position 4, unchanged
        newCubeletOrder[4] = currCubeletOrder[4];
        // handle cubelet at position 5, fixed cubelet
        newCubeletOrder[5] = currCubeletOrder[5];
        // handle cubelet at position 6, unchanged
        newCubeletOrder[6] = currCubeletOrder[6];
        // handle cubelet at position 7, unchanged 
        newCubeletOrder[7] = currCubeletOrder[7];
        cube.setCubeletOrder(newCubeletOrder);
    }
    public void negRTurn(Cube cube) {
        String[] newCubeletOrder = {" ", " ", " ", " ", " ", " ", " ", " "};
        String[] currCubeletOrder = cube.getCubeletOrder();

        // Handle cubelet at position 0, to position 3
        if (currCubeletOrder[0].charAt(1) == '1') {
            // Orientation 1, goes to orientation 1
            newCubeletOrder[3] = currCubeletOrder[0].charAt(0) + "1";
        } else if (currCubeletOrder[0].charAt(1) == '2') {
            // Orientation 2, goes to orientation 3
            newCubeletOrder[3] = currCubeletOrder[0].charAt(0) + "3";
        } else {
            // Orientation 3, goes to orientation 2
            newCubeletOrder[3] = currCubeletOrder[0].charAt(0) + "2";
        }
        // Handle cubelet at position 1, to position 0
        if (currCubeletOrder[1].charAt(1) == '1') {
            // Orientation 1, goes to orientation 1
            newCubeletOrder[0] = currCubeletOrder[1].charAt(0) + "1";
        } else if (currCubeletOrder[1].charAt(1) == '2') {
            // Orientation 2, goes to orientation 3
            newCubeletOrder[0] = currCubeletOrder[1].charAt(0) + "3";
        } else {
            // Orientation 3, goes to orientation 2
            newCubeletOrder[0] = currCubeletOrder[1].charAt(0) + "2";
        }
        // Handle cubelet at position 2, goes to position 1
        if (currCubeletOrder[2].charAt(1) == '1') {
            // Orientation 1, goes to orientation 1
            newCubeletOrder[1] = currCubeletOrder[2].charAt(0) + "1";
        } else if (currCubeletOrder[2].charAt(1) == '2') {
            // Orientation 2, goes to orientation 3
            newCubeletOrder[1] = currCubeletOrder[2].charAt(0) + "3";
        } else {
            // Orientation 3, goes to orientation 2
            newCubeletOrder[1] = currCubeletOrder[2].charAt(0) + "2";
        }
        // Handle cubelet at position 3, goes to position 2
        if (currCubeletOrder[3].charAt(1) == '1') {
            // Orientation 1, goes to orientation 1
            newCubeletOrder[2] = currCubeletOrder[3].charAt(0) + "1";
        } else if (currCubeletOrder[3].charAt(1) == '2') {
            // Orientation 2, goes to orientation 3
            newCubeletOrder[2] = currCubeletOrder[3].charAt(0) + "3";
        } else {
            // Orientation 3, goes to orientation 2
            newCubeletOrder[2] = currCubeletOrder[3].charAt(0) + "2";
        }
        // handle cubelet at position 4, unchanged
        newCubeletOrder[4] = currCubeletOrder[4];
        // handle cubelet at position 5, fixed cubelet
        newCubeletOrder[5] = currCubeletOrder[5];
        // handle cubelet at position 6, unchanged
        newCubeletOrder[6] = currCubeletOrder[6];
        // handle cubelet at position 7, unchanged 
        newCubeletOrder[7] = currCubeletOrder[7];
        cube.setCubeletOrder(newCubeletOrder);
    }
    ////// randomizeCube Function
    /// Randomizes a given cube performing a random set of moves. 
    public Cube randomizeCube(Cube cube, int depth) {
        Random rand = new Random();
        int randnum; 
        int lastnum = -1;
        
        for (int i = 0; i < depth; i++) {
            randnum = rand.nextInt(6);
            if (randnum == MOVES.FTurn.ordinal()) {
                if (lastnum != MOVES.NegFTurn.ordinal()) {
                    // System.out.println("Fturn Performed");
                    this.fTurn(cube);
                    lastnum = 0;
                } else {
                    // System.out.println("Dturn Performed");
                    this.dTurn(cube);
                    lastnum = 2;
                }
            } else if (randnum == MOVES.NegFTurn.ordinal()) {
                if (lastnum != MOVES.FTurn.ordinal()) {
                    // System.out.println("Neg. Fturn Performed");
                    this.negFTurn(cube);
                    lastnum = 1;
                } else {
                    // System.out.println("Neg. Dturn Performed");
                    this.negDTurn(cube);
                    lastnum = 3;
                }
            } else if (randnum == MOVES.DTurn.ordinal()) {
                if (lastnum != MOVES.NegDTurn.ordinal()) {
                    // System.out.println("Dturn Performed");
                    this.dTurn(cube);
                    lastnum = 2;
                } else {
                    // System.out.println("Rturn Performed");
                    this.rTurn(cube);
                    lastnum = 4;
                }
            } else if (randnum == MOVES.NegDTurn.ordinal()) {
                if (lastnum != MOVES.DTurn.ordinal()) {
                    // System.out.println("Neg. Dturn Performed");
                    this.negDTurn(cube);
                    lastnum = 3;
                } else {
                    // System.out.println("Neg. Rturn Performed");
                    this.negRTurn(cube);
                    lastnum = 5;
                }
            } else if (randnum == MOVES.RTurn.ordinal()) {
                if (lastnum != MOVES.NegRTurn.ordinal()) {
                    // System.out.println("Rturn Performed");
                    this.rTurn(cube);
                    lastnum = 4;
                } else {
                    // System.out.println("Fturn Performed");
                    this.fTurn(cube);
                    lastnum = 0;
                }
            } else if (randnum == MOVES.NegRTurn.ordinal()) {
                if (lastnum != MOVES.RTurn.ordinal()) {
                    // System.out.println("Neg. Rturn Performed");
                    this.negRTurn(cube);
                    lastnum = 5;
                } else {
                    // System.out.println("Neg. Fturn Performed");
                    this.negFTurn(cube);
                    lastnum = 1;
                }
            }
        } 
        return cube;
    }
}
