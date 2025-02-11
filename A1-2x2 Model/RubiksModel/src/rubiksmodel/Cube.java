package rubiksmodel;

public class Cube {
    ////// Net attribute
    /// The Net attribute is a 2-D array of characters which represents the cube in a flat format.
    /// This is the primary way in which we will manipulate the cube in this simulation.
    /// A net of a cube in its solved state looks as follows:
    ///     w w
    ///     w w
    /// g g r r b b o o
    /// g g r r b b o o
    ///     y y
    ///     y y
    /// In this case, each character represents a "sticker" on the face of the cube.  
    /// THe net is spoken about with the assumption that the viewer is looking at the right face of the cube.
    private char[][] net;

    ////// Cube Constructor 
    /// Generates a cube in a solved state.
    /// 
    public Cube() {

    }

    ////// isSolved Function
    /// Function which checks whether or not the cube is in a solved state.
    /// Returns TRUE if the cube is in a solved state.
    /// Returns FALSE if the cube is not in a solved state.
    public boolean isSolved() {
            return false;
    }

    ////// getNet Function
    /// Returns the net attribute, a 2-D representation of the cube. 
    public char[][] getNet() {
        return net;
    }
    ////// setNet Function
    /// Allows you to set the net attribute.
    public void setNet(char[][] net) {
        this.net = net;
    }


}
