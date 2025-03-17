package SATSolver;

import java.util.Dictionary;
import java.util.HashSet;

/// Representation of a disjunction of literals
public class Clause {
    HashSet<Integer> literals = new HashSet<Integer>();

    public Clause() {

    }
    // Returns true if the clause evaluates to true given the current assignments to the model
    public boolean isTrue(int[] model) {
        return true;
    }


}
