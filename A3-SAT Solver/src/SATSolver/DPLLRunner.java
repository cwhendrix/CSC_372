package SATSolver;

import java.util.HashSet;

public class DPLLRunner { 
    HashSet<Clause> clauses = new HashSet<Clause>();    // set of clauses making up the sentence in CNF
    HashSet<Integer> symbols = new HashSet<Integer>();  // set of the different symbols
    
    public DPLLRunner() {

    }
    public boolean DPLLSAT(HashSet<Clause> clauses, int numVariables) {
        int[] model = new int[numVariables + 1];
        return DPLL(clauses, symbols, model);
    }
    private boolean DPLL(HashSet<Clause> clauses, HashSet<Integer> symbols, int[] model) {
        return true;
    }
}
