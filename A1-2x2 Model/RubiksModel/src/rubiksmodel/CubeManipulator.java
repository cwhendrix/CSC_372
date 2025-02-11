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
    /// This function takes one argument: an integer, denoting the number of times that this move be performed. 
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
    /// This function takes one argument: an integer, denoting the number of times that this move be performed. 
    /// The net of the cube is altered within the body of this function.
    public void rotateleft(int numMoves, Cube cube) {
        
    }
}
