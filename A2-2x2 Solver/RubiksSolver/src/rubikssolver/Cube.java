package rubikssolver;

public class Cube {
    
    /// The position of the cubelets' characters in the array indicates their orientation.
    /// For the above example, the 'a' cubelet, because it is in the 0 orientation, has its position set to cubeletOrder[0].
    private String[] cubeletOrder = {"a1", "d1", "c1", "b1", "e1", "f1", "g1", "h1"};

    ////// Cube Constructor 
    /// Generates a cube in a solved state.
    /// That solved state will have cubeletOrder = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
    public Cube() {
    }

    ////// Alternate Cube Constructor
    /// Allows you to generate a cube with a given cubelet order.
    public Cube(String[] cubeletOrder) {
        this.setCubeletOrder(cubeletOrder);
    }

    ////// isSolved Function
    /// Function which checks whether or not the cube is in a solved state.
    /// Returns TRUE if the cube is in a solved state.
    /// Returns FALSE if the cube is not in a solved state.
    public boolean isSolved() {
        if (!cubeletOrder[0].equals("a1")) {
            return false;
        } else if (!cubeletOrder[1].equals("d1")) {
            return false;
        } else if (!cubeletOrder[2].equals("c1")) {
            return false;
        } else if (!cubeletOrder[3].equals("b1")) {
            return false;
        } else if (!cubeletOrder[4].equals("e1")) {
            return false;
        } else if (!cubeletOrder[5].equals("f1")) {
            return false;
        } else if (!cubeletOrder[6].equals("g1")) {
            return false;
        } else if (!cubeletOrder[7].equals("h1")) {
            return false;
        }
        return true;
    }

    ////// getCopy Function
    /// Function which returns a deep copy of the cube.
    public Cube getCopy() {
        Cube newCube = new Cube(this.getCubeletOrder());
        return newCube;
    }

    ////// getCubeletOrder Function
    /// Returns the cubeletOrder attribute. 
    public String[] getCubeletOrder() {
        return cubeletOrder;
    }
    ////// setCubeletOrder Function
    /// Allows you to set the cubeletOrder attribute.
    public void setCubeletOrder(String[] cubeletOrder) {
        this.cubeletOrder = cubeletOrder;
    }
}
