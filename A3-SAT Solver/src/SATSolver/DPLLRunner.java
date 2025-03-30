package SATSolver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class DPLLRunner { 
    HashSet<Integer> symbols = new HashSet<Integer>();
      // set of the different symbols
    
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
                newSentence.add(newClause); // add it without the literal; because it's negated, the literal is false, so we need something else to make that clause true
            } else {
                newSentence.add(clause);    // clause doesn't contain literal
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

    // returns a pure literal (literal who only appears in one polarity throughout the sentence)
    public static Literal returnPureLiteral(ArrayList<Clause> sentence, HashSet<Integer> symbols) {
        Literal temp = null;
        Literal negTemp = null;
        boolean purity = true;
        boolean negPurity = true;
        for (Integer symbol : symbols) {
            purity = true;
            negPurity = true;
            temp = new Literal(symbol, false);
            negTemp = new Literal(symbol, true);
            for (Clause clause : sentence) {
                if (clause.literals.contains(temp)) {
                    negPurity = false;
                }
                if (clause.literals.contains(negTemp)) {
                    purity = false;
                }
                if (!purity && !negPurity) {
                    break;
                }
            }
        }
        if (purity) {
            return temp;
        } else if (negPurity) {
            return negTemp;
        }
        return null;
    }

    public boolean DPLLSAT(ArrayList<Clause> sentence, int numVariables, PrintWriter writer) {
        Map<Integer, Boolean>  model = new HashMap<>();
        long startTime = System.currentTimeMillis();
        boolean sat = DPLL(sentence, symbols, model);
        if (sat) {
            long estimatedTime = (System.currentTimeMillis() - startTime);
            writer.println("SENTENCE SATISFIABLE: " + model.toString());
            writer.println("Time to complete: " + estimatedTime + " ms.");
            writer.println(" ");
        } else {
            long estimatedTime = (System.currentTimeMillis() - startTime);
            writer.println("SENTENCE UNSATISFIABLE: " + model.toString());
            writer.println("Time to complete: " + estimatedTime + " ms.");
            writer.println(" ");
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
            //System.out.println("REMOVED UNIT CLAUSE: " + unitLit.toString());
            if (!unitLit.isNegation()) {
                model.put(unitLit.returnVar(), true);
            } else {
                model.put(unitLit.returnVar(), false);
            }
            unit = returnUnitClause(sentence);
        }

        // Pure Literal Elimination
        Literal pure = returnPureLiteral(sentence, symbols);
        while (pure != null) {
            sentence = assign(sentence, pure);
            symbols.remove(pure.returnVar());
            //System.out.println("REMOVED PURE LITERAL: " + pure.toString());
            if (!pure.isNegation()) {
                model.put(pure.returnVar(), true);
            } else {
                model.put(pure.returnVar(), false);
            }
            pure = returnPureLiteral(sentence, symbols);
        }

        // Check if all clauses are true = BASE CASE
        if (sentence.isEmpty()) {
            //System.out.println("BRANCH SATISFIABLE: " + model.toString());
            return true;
        }
        // Check if clause is false = BASE CASE
        if (hasEmpty(sentence)) {
            //System.out.println("BRANCH UNSATISFIABLE: " + model.toString());
            return false;
        }

        // Heuristic Time\
        Integer var = symbols.iterator().next();
        symbols.remove(var);
        ArrayList<Clause> newSentenceTrue = assign(new ArrayList<Clause>(sentence), new Literal(var, false)); /// Assign literal true
        Map<Integer, Boolean>  trueModel = new HashMap<>();
        for (Integer assignment : model.keySet()) {
            trueModel.put(assignment, model.get(assignment));
        }
        trueModel.put(var, true);
        HashSet<Integer> symbolsTrue = new HashSet<Integer>(symbols);

        ArrayList<Clause> newSentenceFalse = assign(new ArrayList<Clause>(sentence), new Literal(var, true)); /// Assign literal false
        Map<Integer, Boolean>  falseModel = new HashMap<>();
        for (Integer assignment : model.keySet()) {
            falseModel.put(assignment, model.get(assignment));
        }
        falseModel.put(var, false);
        HashSet<Integer> symbolsFalse = new HashSet<Integer>(symbols);

        return DPLL(newSentenceTrue, symbolsTrue, trueModel) | DPLL(newSentenceFalse, symbolsFalse, falseModel);
    }
}
