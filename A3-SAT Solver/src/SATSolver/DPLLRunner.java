package SATSolver;

import java.util.HashSet;
import java.util.Iterator;

public class DPLLRunner { 
    HashSet<Integer> symbols = new HashSet<Integer>();  // set of the different symbols
    
    public DPLLRunner() {

    }
    public boolean DPLLSAT(Sentence sentence, int numVariables) {
        int[] model = new int[numVariables + 1];
        return DPLL(sentence, symbols, model);
    }
    private boolean DPLL(Sentence sentence, HashSet<Integer> symbols, int[] model) {
        boolean solved = true;
        // Check if all clauses are true
        if (sentence.isEmpty()) {
            
        }
        // Check if clause is false

        // Check if all clauses are

        return true;
    }
}
