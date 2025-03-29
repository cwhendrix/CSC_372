package SATSolver;

import java.util.Objects;

public class Literal {
    private int var; // the number of the literal, corresponding to the variable of the same name
    private boolean neg; // true if the literal is negated

    // Constructor with var & neg
    public Literal(int var, boolean neg) {
        this.var = var;
        this.neg = neg;
    }

    // Returns true if negated, returns false if normal
    public boolean isNegation() {
        return neg;
    }
    
    // Returns literal number
    public int returnVar() {
        return var;
    }

    @Override
    public boolean equals(Object object) {
        Literal that = (Literal) object;
        if (this == that) {
            return true;
        } else if (this.var == that.returnVar() && this.neg == that.isNegation()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(var, neg);
    }
}

