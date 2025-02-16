package rubiksmodel;

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
    private char turnSymbol;     /// Used to hold whatever the current turn being performed is


    public char getTurnSymbol() {
        return turnSymbol;
    }

    public void setTurnSymbol(char turnSymbol) {
        this.turnSymbol = turnSymbol;
    }

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
    /// This function takes the cube as a parameter and changes the cubeletOrder according to the 
    /// turnTable lookup table. 
    /// This function can be called in succession in order to perform the turn multiple times.
    public void fTurn(Cube cube) {

    }
    

    ////// L-Turn function
    /// The L-Turn function turns the cube about the orange face. 
    /// This function takes the cube as a parameter and changes the cubeletOrder according to the 
    /// turnTable lookup table. 
    /// This function can be called in succession in order to perform the turn multiple times.
    public void lTurn(Cube cube) {

    }

    ////// U-Turn function
    /// The U-Turn function turns the cube about the white face. 
    /// This function takes the cube as a parameter and changes the cubeletOrder according to the 
    /// turnTable lookup table. 
    /// This function can be called in succession in order to perform the turn multiple times.
    public void uTurn(Cube cube) {

    }

    ////// R-Turn function
    /// The R-Turn function turns the cube about the red face. 
    /// This function takes the cube as a parameter and changes the cubeletOrder according to the 
    /// turnTable lookup table. 
    /// This function can be called in succession in order to perform the turn multiple times.
    public void rTurn(Cube cube) {

    }

    ////// B-Turn function
    /// The B-Turn function turns the cube about the blue face. 
    /// This function takes the cube as a parameter and changes the cubeletOrder according to the 
    /// turnTable lookup table. 
    /// This function can be called in succession in order to perform the turn multiple times.
    public void bTurn(Cube cube) {

    }

    ////// D-Turn function
    /// The D-Turn function turns the cube about the yellow face. 
    /// This function takes the cube as a parameter and changes the cubeletOrder according to the 
    /// turnTable lookup table. 
    /// This function can be called in succession in order to perform the turn multiple times.
    public void dTurn(Cube cube) {

    }

    ////// randomizeCube Function
    /// Randomizes a given cube by turning it a random number of times. 
    public Cube randomizeCube(Cube cube) {
        return cube;
    }
}
