package SATSolver;

import java.util.ArrayList;

public class App {
    public void readDIMACS() {
        
    }

    public static void main(String[] args) throws Exception {
        DPLLRunner dpllRunner = new DPLLRunner();
        dpllRunner.symbols.add(1);
        dpllRunner.symbols.add(2);
        ArrayList<Clause> sentence = new ArrayList<>();
        Clause clause1 = new Clause();
        clause1.literals.add(new Literal(1, false));
        clause1.literals.add(new Literal(2, false));
        Clause clause2 = new Clause();
        clause2.literals.add(new Literal(2, true));

        sentence.add(clause1);
        sentence.add(clause2);

        System.out.println(dpllRunner.DPLLSAT(sentence, 2));
    }
}
