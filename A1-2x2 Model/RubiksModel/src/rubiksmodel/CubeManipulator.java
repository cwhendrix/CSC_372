package rubiksmodel;

public class CubeManipulator {

    ////// CubeManipulator Constructor
    /// This is the constructor for this class.
    /// This class handles the alterations of the Cube for the purpose of solving it.
    public CubeManipulator() {

    }
    ////// rotateRight Function
    /// Function which rotates the right side of the face being viewed. 
    /// After calling this function (with 1 as the argument), 
    /// the net of the solved Cube (as seen in the description of the net attribute) would look like this:
    ///     w w
    ///     w w
    /// g g r r b b o o
    /// r r b b o o g g
    ///     y y
    ///     y y
    /// numMoves denotes the number of times that this move should be performed. 
    /// A negative numMoves indicates that the move be done counter (i.e. the right side is rotated down.)
    /// The net of the cube is altered within the body of this function.
    public void rotateRight(int numMoves, Cube cube) {
        
    }

    ////// rotateLeft Function
    /// Function which rotates the left side of the face being viewed. 
    /// After calling this function (with 1 as the argument), 
    /// the net of the solved Cube (as seen in the description of the net attribute) would look like this:
    ///     w w
    ///     w w
    /// r r b b o o g g
    /// g g r r b b o o
    ///     y y
    ///     y y
    /// numMoves denotes the number of times that this move should be performed. 
    /// A negative numMoves indicates that the move be done counter (i.e. the left side is rotated down.)
    /// The net of the cube is altered within the body of this function.
    public void rotateLeft(int numMoves, Cube cube) {
        
    }

    ////// rotateTop Function
    /// Function which rotates the top layer of the face being viewed. 
    /// After calling this function (with 1 as the argument), 
    /// the net of the solved Cube (as seen in the description of the net attribute) would look like this:
    ///     w o
    ///     w o
    /// g g r w b b y o
    /// g g r w b b y o
    ///     y r
    ///     y r
    /// numMoves denotes the number of times that this move should be performed. 
    /// A negative numMoves indicates that the move be done counter (i.e. the top layer is rotated to the left.)
    /// The net of the cube is altered within the body of this function.
    public void rotateTop(int numMoves, Cube cube) {
        
    }
    ////// rotateBottom Function
    /// Function which rotates the bottom layer of the face being viewed. 
    /// After calling this function (with 1 as the argument), 
    /// the net of the solved Cube (as seen in the description of the net attribute) would look like this:
    ///     o w
    ///     o w
    /// g g w r b b o y
    /// g g w r b b o y
    ///     r y
    ///     r y
    /// numMoves denotes the number of times that this move should be performed. 
    /// A negative numMoves indicates that the move be done counter (i.e. the bottom layer is rotated to the left.)
    /// The net of the cube is altered within the body of this function.
    public void rotateBottom(int numMoves, Cube cube) {
        
    }
    ////// rotateBack Function
    /// Function which rotates the back face of the cube. 
    /// After calling this function (with 1 as the argument), 
    /// the net of the solved Cube (as seen in the description of the net attribute) would look like this:
    ///     b b
    ///     w w
    /// w g r r b y o o
    /// w g r r b y o o
    ///     y y
    ///     g g
    /// numMoves denotes the number of times that this move should be performed. 
    /// A negative numMoves indicates that the move be done counter (i.e. the back layer is rotated to the viewer's right.)
    /// The net of the cube is altered within the body of this function.
    public void rotateBack(int numMoves, Cube cube) {
        
    }
    ////// randomizeCube Function
    /// Randomizes a given cube by turning it a random number of times. 
    public Cube randomizeCube(Cube cube) {
        return cube;
    }
}
