package SATSolver;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Objects;

/// Representation of a disjunction of literals
public class Clause {
    public HashSet<Literal> literals = new HashSet<Literal>();  // The literals in the clause 

    public Clause() {

    }
    public Clause(Clause that) {
        for (Literal i: that.literals) {
            this.literals.add(new Literal(i.returnVar(), i.isNegation()));
        }
    }
    
    // If it's a unit clause, return the one literal (whose polarity indicates the assignment)
    public Literal unitClause() {
        if (literals.size() == 1) {
            return literals.iterator().next();
        }
        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        Clause that = (Clause) object;
        for (Literal i : literals) {
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

    @Override
    public String toString() {
        ArrayList<String> rep = new ArrayList<>();
        for (Literal i : literals) {
            String temp;
            if (i.isNegation()) {
                temp = "-" + Integer.toString(i.returnVar());
            } else {
                temp = Integer.toString(i.returnVar());
            }
            rep.add(temp);
        }
        return rep.toString();
    }
}
