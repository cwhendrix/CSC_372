import java.util.Random;

public class App {
    ////// MDP Map
    /// s12 s13 s14 s15
    /// s8  s9  s10 s11
    /// s4  s5  s6  s7
    /// s0  s1  s2  s3

    /* 
    private Action moveFinal(Action intendedAction, double prob) {
        Random rand = new Random();
        double r = rand.nextDouble();

        if (r <= prob) {

        }

        return Action.UP;
    }
    */

    public static void main(String[] args) throws Exception {
        
        ///// Initial Setup
        int[] states = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] rewards = {0, 50, 0, 50, 0, 50, 0, 50, 0, 50, 100, 50, 200, 50, 0, 50};
        double discount = 0.95;

        ///// Value Iteration
    }
}
