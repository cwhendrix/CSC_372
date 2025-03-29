package SATSolver;

import java.util.HashSet;

public class Sentence {
    public HashSet<Clause> clauses;
    public HashSet<Integer> variables;
    
    public Sentence() {}
    
    public boolean isEmpty() {
        if (clauses.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean contradicts() {
        for (Clause c: clauses) {
            if (c.literals.size() == 0) {
                return true;
            }
        }
        return false;
    }
    
    public int checkUnitClause() {
        int unit;
        for (Clause c:clauses) {
            unit = c.unitClause();
            if (unit != 0) {
                return unit;
            }
        }
        return 0;
    }

    public Sentence assign(int var) {

        return null;
    }
}
