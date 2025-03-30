package SATSolver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class HillclimbRunner {
    HashSet<Integer> symbols = new HashSet<Integer>();
    int maxFlips = 100; // maximum number of flips before algorithm times out
    Random random = new Random();

    public HillclimbRunner() {}

    public Map<Integer, Boolean> randomModel() {
        Map<Integer, Boolean>  model = new HashMap<>();
        int randInt = -1;
        for (Integer symbol : symbols) {
            randInt = random.nextInt(2);
            if (randInt == 0) {
                model.put(symbol, false);   // assign value to be false
            } else {
                model.put(symbol, true);    // assign value to be true
            }
        }
        return model;
    }

    public boolean isSatisfied(boolean[] evals) {
        for (boolean clause: evals) {
            if (!clause) {
                return false;
            }
        }
        return true;
    }

    public boolean[] evalModel(boolean[] evals, Map<Integer, Boolean> model, ArrayList<Clause> sentence) {
        Boolean tempBool;
        int index = 0;
        boolean[] newEvals = new boolean[evals.length]; // new set of boolean values to match this changed model
        for (int i = 0; i < newEvals.length; i++) {
            newEvals[i] = false;
        }
        for (Clause clause : sentence) {
            for (Literal lit : clause.literals) {
                tempBool = model.get(lit.returnVar());
                if (tempBool && !lit.isNegation()) {
                    newEvals[index] = true;   // assignment works, clause evaluates to true
                } else if (!tempBool && lit.isNegation()) {
                    newEvals[index] = true;   // assignment works, clause evaluates to true
                }
            }
            index++;
        }
        return newEvals;
    }
    public int evalToInt(boolean[] evals) {
        int evalInt = 0;    // number of clauses which, based on this evaluation, end up as true
        for (int i=0; i<evals.length; i++) {
            if (evals[i]) {
                evalInt++;
            }
        }
        return evalInt;
    }

    public int minConflicts(Map<Integer, Boolean>  model, ArrayList<Clause> sentence, boolean[] evals) {
        int bestFlip = 1;   // keeps track of the best variable to flip
        int tempTruths = 0; // keeps track of the current evaluation of the current assignments
        int currEval = evalToInt(evals);    //evaluation of the state which WalkSAT is currently in, for comparison
        int bestEval = currEval;   // best evaluation value we've got via iteration
        boolean[] tempEvals = new boolean[evals.length];
        // System.out.println("CURRENT TRUE CLAUSES: " + currEval);

        for (Integer symbol : symbols) {
            // setup model
            Map<Integer, Boolean>  tempModel = new HashMap<>();
            for (Integer assignment : model.keySet()) {
                tempModel.put(assignment, model.get(assignment));
            }

            // flip symbol
            int flipVal = symbol;
            boolean currPol = model.get(flipVal);
            tempModel.remove(flipVal);
            if (currPol) {
                tempModel.put(flipVal, false);  // was true, flip to false
            } else {
                tempModel.put(flipVal, true);
            }
            // evaluate model
            tempEvals = evalModel(evals, tempModel, sentence);
            tempTruths = evalToInt(tempEvals);
            if (tempTruths >= currEval && tempTruths >= bestEval) {
                bestFlip = symbol;
                // System.out.println("FLIP " + symbol + " TO " + !currPol + " FOR " + tempTruths + " TRUE CLAUSES VS. " + bestEval + "PREVIOUSLY");
                bestEval = tempTruths;
            }
        }

        return bestFlip;
    }

    public boolean HillClimb(ArrayList<Clause> sentence, int numVariables, PrintWriter writer) {
        /// Setup
        Map<Integer, Boolean>  model = randomModel();
        double rand;
        boolean[] evals = new boolean[sentence.size()];
        evals = evalModel(evals, model, sentence);
        long startTime = System.currentTimeMillis();

        for (int i=0; i < maxFlips; i++) {
            // if model is satisfied, return
            if (isSatisfied(evals)) {
                long estimatedTime = (System.currentTimeMillis() - startTime);
                writer.println("SENTENCE SATISFIABLE: " + model.toString());
                writer.println("Time to complete: " + estimatedTime + " ms.");
                writer.println(" ");
                return true;
            }

            // if not satisfied, get climbin
            int minConflictFlip = minConflicts(model, sentence, evals);
            // System.out.println("VALUE TO OPTIMALLY FLIP: " + minConflictFlip);
            boolean currPol = model.get(minConflictFlip);
            model.remove(minConflictFlip);
            if (currPol) {
                model.put(minConflictFlip, false);  // was true, flip to false
            } else {
                model.put(minConflictFlip, true);
            }
            // System.out.println(model.toString());
            evals = evalModel(evals, model, sentence);

        }
        long estimatedTime = (System.currentTimeMillis() - startTime);
        int finalSat = evalToInt(evals);
        writer.println("HILLCLIMB TIMEOUT: " + model.toString());
        writer.println("Time to complete: " + estimatedTime + " ms.");
        writer.println("NUMBER OF SATISFIED CLAUSES: " + finalSat + "/" + sentence.size());
        writer.println(" ");
        return false; // TIMEOUT
    }
}
