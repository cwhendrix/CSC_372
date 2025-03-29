package SATSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class DPLLRunner { 
    HashSet<Integer> symbols = new HashSet<Integer>();  // set of the different symbols
    
    public DPLLRunner() {

    }

    // Assign a variable; remove satisfied clauses; remove negated literals from clauses
    public static ArrayList<Clause> assign(ArrayList<Clause> oldSentence, Literal assigned) {
        ArrayList<Clause> newSentence = new ArrayList<>();
        for (Clause clause : oldSentence) {
            if (clause.literals.contains(assigned)) {
                continue;   // we can skip adding this one back because it's satisfied
            }
            if (clause.literals.contains(new Literal(assigned.returnVar(), !assigned.isNegation()))) {
                Clause newClause = new Clause(clause);
                newClause.literals.remove(new Literal(assigned.returnVar(), !assigned.isNegation()));
                newSentence.add(newClause);
            } else {
                newSentence.add(clause);
            }
        }

        return newSentence;
    }

    // if there is a random empty clause in our sentence, we know that there's a contradiction
    // Our code is still waiting for an assignment to satisfy that clause, but no assignment in this model can do it
    public static boolean hasEmpty(ArrayList<Clause> sentence) {
        for (Clause clause : sentence) {
            if (clause.literals.isEmpty()) {
                return true;
            }
        }
        return false;   //we've gotten here, everything's fine
    }

    // Returns a unit clause (clause with only one literal)
    public static Clause returnUnitClause(ArrayList<Clause> sentence) {
        for (Clause clause : sentence) {
            if (clause.unitClause() != null) {
                return clause;
            }
        }
        return null;
    }

    public boolean DPLLSAT(ArrayList<Clause> sentence, int numVariables) {
        Map<Integer, Boolean>  model = new HashMap<>();
        boolean sat = DPLL(sentence, symbols, model);
        if (sat) {
            System.out.println("SENTENCE SATISFIABLE");
        } else {
            System.out.println("SENTENCE UNSATISFIABLE");
        }
        return sat;
    }
    private boolean DPLL(ArrayList<Clause> sentence, HashSet<Integer> symbols, Map<Integer, Boolean> model) {
        // Unit Clause Elimination
        Clause unit = returnUnitClause(sentence);
        while (unit != null) {
            Literal unitLit = unit.unitClause();
            sentence = assign(sentence, unitLit);
            symbols.remove(unitLit.returnVar());
            System.out.println("REMOVED UNIT CLAUSE");
            unit = returnUnitClause(sentence);
        }

        // Check if all clauses are true = BASE CASE
        if (sentence.isEmpty()) {
            System.out.println("BRANCH SATISFIABLE");
            return true;
        }
        // Check if clause is false = BASE CASE
        if (hasEmpty(sentence)) {
            System.out.println("BRANCH UNSATISFIABLE");
            return false;
        }
        // Pure Symbol Elimination

        // Heuristic Time
        Integer var = symbols.iterator().next();
        symbols.remove(var);
        ArrayList<Clause> newSentenceTrue = assign(new ArrayList<Clause>(sentence), new Literal(var, false)); /// Assign literal true
        Map<Integer, Boolean>  trueModel = new HashMap<>(model);
        trueModel.put(var, true);
        ArrayList<Clause> newSentenceFalse = assign(new ArrayList<Clause>(sentence), new Literal(var, true)); /// Assign literal false
        Map<Integer, Boolean>  falseModel = new HashMap<>(model);
        falseModel.put(var, false);

        return DPLL(newSentenceTrue, symbols, trueModel) | DPLL(newSentenceFalse, symbols, falseModel);
    }
}
