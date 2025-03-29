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
        ArrayList<Clause> sentence = new ArrayList<>();

        String[] testCases = {"A3_tests/10.40.160707067.cnf",
                              "A3_tests/10.40.967323288.cnf", 
                              "A3_tests/10.42.504071595.cnf",
                              "A3_tests/10.42.1465130262.cnf",
                              "A3_tests/10.44.1247388329.cnf",
                              "A3_tests/10.44.1667358355.cnf",
                              "A3_tests/10.44.1667358355.cnf",
                              "A3_tests/10.46.183405239.cnf",
                              "A3_tests/10.46.623142927.cnf",
                              "A3_tests/10.48.640112774.cnf",
                              "A3_tests/10.48.1494607484.cnf"};

        String[] formulas = {"A3Formulas/f0020-01-s.cnf",
                             "A3Formulas/f0020-01-u.cnf",
                             "A3Formulas/f0020-02-s.cnf",
                             "A3Formulas/f0020-02-u.cnf",
                             "A3Formulas/f0020-03-s.cnf",
                             "A3Formulas/f0020-03-u.cnf",
                             "A3Formulas/f0020-04-s.cnf",
                             "A3Formulas/f0020-04-u.cnf",
                             "A3Formulas/f0020-05-s.cnf",
                             "A3Formulas/f0020-05-u.cnf",
                             "A3Formulas/f0020-06-s.cnf",
                             "A3Formulas/f0020-06-u.cnf",
                             "A3Formulas/f0020-07-s.cnf",
                             "A3Formulas/f0020-07-u.cnf",
                             "A3Formulas/f0020-08-s.cnf",
                             "A3Formulas/f0020-08-u.cnf",
                             "A3Formulas/f0040-01-s.cnf",
                             "A3Formulas/f0040-01-u.cnf",
                             "A3Formulas/f0040-02-s.cnf",
                             "A3Formulas/f0040-02-u.cnf",
                             "A3Formulas/f0040-03-s.cnf",
                             "A3Formulas/f0040-03-u.cnf",
                             "A3Formulas/f0040-04-s.cnf",
                             "A3Formulas/f0040-04-u.cnf",
                             "A3Formulas/f0040-05-s.cnf",
                             "A3Formulas/f0040-05-u.cnf",
                             "A3Formulas/f0040-06-s.cnf",
                             "A3Formulas/f0040-06-u.cnf",
                             "A3Formulas/f0040-07-s.cnf",
                             "A3Formulas/f0040-07-u.cnf",
                             "A3Formulas/f0040-08-s.cnf",
                             "A3Formulas/f0040-08-u.cnf"};

        for (int i=0; i<formulas.length; i++) {
            sentence = readDIMACS(formulas[i], sentence, dpllRunner);
            System.out.println(dpllRunner.DPLLSAT(sentence, dpllRunner.symbols.size()));
            sentence = new ArrayList<>();
            dpllRunner = new DPLLRunner();
            System.gc();
        } 
/* 
        ArrayList<Clause> testSentence = new ArrayList<>();
        Clause clause1 = new Clause();
        clause1.literals.add(new Literal(1, true));
        clause1.literals.add(new Literal(3, false));
        testSentence.add(clause1);
        Clause clause2 = new Clause();
        clause2.literals.add(new Literal(2, true));
        testSentence.add(clause2);
        Clause clause3 = new Clause();
        clause3.literals.add(new Literal(4, false));
        clause3.literals.add(new Literal(1, true));
        testSentence.add(clause3);
        dpllRunner.symbols.add(1);
        dpllRunner.symbols.add(2);
        dpllRunner.symbols.add(3);
        dpllRunner.symbols.add(4);
        System.out.println(dpllRunner.DPLLSAT(testSentence, dpllRunner.symbols.size()));

        sentence = new ArrayList<>();
        dpllRunner = new DPLLRunner();
        System.gc();

        sentence = readDIMACS("A3_tests/10.48.1494607484.cnf", sentence, dpllRunner);
        System.out.println(dpllRunner.DPLLSAT(sentence, dpllRunner.symbols.size())); */
    }
}
