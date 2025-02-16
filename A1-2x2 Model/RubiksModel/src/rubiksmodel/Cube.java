package rubiksmodel;

public class Cube {
    ////// cubeletOrder attribute
    /// This attribute, consisting of an array of characters, represents the order in which the cubelets are arranged. 
    /// "Cubelet" refers to the small, individual cubes which make up a 2x2 Rubik's Cube.
    /// The default arrangement of these cubelets is shown below, showing the colors in their standard positions.
    ///     w w
    ///     w w
    /// g g r r b b o o
    /// g g r r b b o o
    ///     y y
    ///     y y
    /// Next, each cubelet is marked with a letter a-h.
    ///     g f
    ///     d c
    /// g d d c c f f g
    /// h a a b b e e h
    ///     a b
    ///     h e
    /// Each of the eight orientations which a cube can be placed in is labeled with a number, 0-7. 
    /// Those orientations are shown as follows:
    ///     6 5
    ///     1 2
    /// 6 1 1 2 2 5 5 6
    /// 7 0 0 3 3 4 4 7
    ///     0 3
    ///     7 4
    /// The position of the cubelets' characters in the array indicates their orientation.
    /// For the above example, the 'a' cubelet, because it is in the 0 orientation, has its position set to cubeletOrder[0].
    /// The full cubeletOrder for the above example cube would be:
    /// cubeletOrder = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
    private char[] cubeletOrder = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    ////// Cube Constructor 
    /// Generates a cube in a solved state.
    /// That solved state will have cubeletOrder = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
    public Cube() {
    }

    ////// Alternate Cube Constructor
    /// Allows you to generate a cube with a given cubelet order.
    public Cube(char[] cubeletOrder) {
        this.setCubeletOrder(cubeletOrder);
    }

    ////// isSolved Function
    /// Function which checks whether or not the cube is in a solved state.
    /// Returns TRUE if the cube is in a solved state.
    /// Returns FALSE if the cube is not in a solved state.
    public boolean isSolved() {
            return false;
    }

    ////// getCopy Function
    /// Function which returns a deep copy of the cube.
    public Cube getCopy() {
        Cube newCube = new Cube(this.getCubeletOrder());
        return newCube;
    }

    ////// getCubeletOrder Function
    /// Returns the cubeletOrder attribute. 
    public char[] getCubeletOrder() {
        return cubeletOrder;
    }
    ////// setCubeletOrder Function
    /// Allows you to set the cubeletOrder attribute.
    public void setCubeletOrder(char[] cubeletOrder) {
        this.cubeletOrder = cubeletOrder;
    }


}
