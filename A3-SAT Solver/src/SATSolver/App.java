package SATSolver;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static ArrayList<Clause> readDIMACS(String input, ArrayList<Clause> sentence, DPLLRunner runner) throws Exception{
        File dimacs = new File(input);
        Scanner scanner = new Scanner(dimacs);
        String data = scanner.nextLine();
        String[] curr = data.split(" ");
        int numSymbols = Integer.parseInt(curr[2]);
        int numClauses = Integer.parseInt(curr[3]);

        System.out.println("NUM SYMBOLS: " + numSymbols +", NUM CLAUSES: " + numClauses);
        
        // Create symbols array
        HashSet<Integer> symbols = new HashSet<Integer>();
        for (int k = 1; k <= numSymbols; k++) {
            symbols.add(k);
        }
        runner.symbols = symbols;
        
        
        // Parse the rest of the lines
        for (int i = 1; i <= numClauses; i++) {
            data = scanner.nextLine();
            data = data.trim();
            curr = data.split(" ");
            Clause newClause = new Clause();
            for (int j = 0; j < curr.length; j++) {
                int currLit = Integer.parseInt(curr[j]);
                if (currLit != 0) {
                    if (currLit > 0) {
                        newClause.literals.add(new Literal(currLit, false));
                    } else {
                        newClause.literals.add(new Literal(Math.abs(currLit), true));
                    }
                }
            }
            sentence.add(newClause);
        }
        scanner.close();
        return sentence;
    }
    public static void main(String[] args) throws Exception {
        DPLLRunner dpllRunner = new DPLLRunner();
        dpllRunner.symbols.add(1);
        dpllRunner.symbols.add(2);
        dpllRunner.symbols.add(3);
        ArrayList<Clause> sentence = new ArrayList<>();
        /* 
        Clause clause1 = new Clause();
        clause1.literals.add(new Literal(1, false));
        clause1.literals.add(new Literal(2, false));
        clause1.literals.add(new Literal(3, false));
        Clause clause2 = new Clause();
        clause2.literals.add(new Literal(1, false));
        clause2.literals.add(new Literal(2, true));
        clause2.literals.add(new Literal(3, false));
        */

        //sentence.add(clause1);
        //sentence.add(clause2);

        // System.out.println(dpllRunner.DPLLSAT(sentence, 3));
        sentence = readDIMACS("A3_tests/10.40.160707067.cnf", sentence, dpllRunner);
        System.out.println(sentence.toString());
    }
}
