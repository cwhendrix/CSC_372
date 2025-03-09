package rubikssolver;

import java.util.Arrays;
import java.util.Objects;

public class Cube {
    
    /// The position of the cubelets' characters in the array indicates their orientation.
    /// For the above example, the 'a' cubelet, because it is in the 0 orientation, has its position set to cubeletOrder[0].
    private String[] cubeletOrder = {"a1", "d1", "c1", "b1", "e1", "f1", "g1", "h1"};
    private StringBuffer stateCode= new StringBuffer();

    public String getStateCode() {
        return stateCode.toString();
    }

    ////// Cube Constructor 
    /// Generates a cube in a solved state.
    /// That solved state will have cubeletOrder = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
    public Cube() {
        stateCode.append("a1d1c1b1e1f1g1h1");
    }

    public void updateStatecode() {
        stateCode.delete(0, stateCode.length());
        for (int i=0; i<=7; i++) {
            stateCode.append(cubeletOrder[i]);
        }
    }

    ////// Alternate Cube Constructor
    /// Allows you to generate a cube with a given cubelet order.
    public Cube(String[] cubeletOrder) {
        this.setCubeletOrder(cubeletOrder);
    }

    @Override
    public boolean equals(Object other) {
        Cube that = (Cube) other;
        if (this.stateCode.toString().equals(that.stateCode.toString()) == true) {
            return true;
        } else {
            return false;
        } 
    }
    @Override
    public int hashCode() {
        String hashCode = "";
        int hashCodeint = 0;
        String tempCode = stateCode.toString();
        for (int i=0; i<stateCode.length(); i++) {
            if (tempCode.charAt(i) == '1' ||  tempCode.charAt(i) == '2' ||  tempCode.charAt(i) == '3') {
                hashCode = hashCode += tempCode.charAt(i);
            } else if (tempCode.charAt(i) == 'a') {
                hashCode = hashCode += 0;
            } else if (tempCode.charAt(i) == 'b') {
                hashCode = hashCode += 1;
            } else if (tempCode.charAt(i) == 'c') {
                hashCode = hashCode += 2;
            } else if (tempCode.charAt(i) == 'd') {
                hashCode = hashCode += 3;
            } else if (tempCode.charAt(i) == 'e') {
                hashCode = hashCode += 4;
            } else if (tempCode.charAt(i) == 'f') {
                hashCode = hashCode += 5;
            } else if (tempCode.charAt(i) == 'g') {
                hashCode = hashCode += 6;
            } else if (tempCode.charAt(i) == 'h') {
                hashCode = hashCode += 7;
            }
        }
        try {
            hashCodeint = Integer.parseInt(hashCode);
        } catch (Exception e) {
            
        }
        return hashCodeint;
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
        this.updateStatecode();
    }
}
