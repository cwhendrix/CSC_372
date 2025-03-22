package SATSolver;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Objects;

/// Representation of a disjunction of literals
public class Clause {
    public HashSet<Integer> literals = new HashSet<Integer>();

    public Clause() {

    }
    // If it's a unit clause, return the one literal (whose polarity indicates the assignment)
    public int unitClause() {
        if (literals.size() == 1) {
            return literals.iterator().next();
        }
        return -1;
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        Clause that = (Clause) object;
        for (Integer i : literals) {
            if (!that.literals.contains(i)) {
                return false;
            }
        }
        if (this.literals.size() == that.literals.size()) {
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(literals);
    }
    // Returns true if the clause evaluates to true given the current assignments to the model
    public boolean isTrue(int[] model) {
        return true;
    }
}
