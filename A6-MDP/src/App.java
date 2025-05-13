import java.util.Random;

public class App {
    ////// MDP Map
    /// s12 s13 s14 s15
    /// s8  s9  s10 s11
    /// s4  s5  s6  s7
    /// s0  s1  s2  s3


    private double transition(State goal, State start, Action action) {
        /// if start mod 4 = 0, if it tries to go left, 90% chance it ends up going to the right
        if (start.ordinal() % 4 == 0) {
            if (action == Action.LEFT) {
                return 0.9;
            } 
        }
        /// if start mod 4 - 3 = 0, if it tries to go right, 90% chance it ends up going to the left
        if (start.ordinal() % 4 == 3) {
            if (action == Action.RIGHT) {
                return 0.9;
            }
        }
        /// if start ordinality <=3, if it tries to go down, 90% chance it goes up
        if (start.ordinal() <= 3) {
            if (action == Action.DOWN) {
                return 0.9;
            }
        }
        /// if start ordinality >=12, if it tries to go up, 90% chance it goes up
        if (start.ordinal() >= 12) {
            if (action == Action.UP) {
                return 0.9;
            }
        }
        
        return 0.7;
    }
    /* 
    private Action moveFinal(Action intendedAction, double prob) {
        Random rand = new Random();
        double r = rand.nextDouble();

        if (r <= prob) {

        }

        return Action.UP;
    }
    */

    private int[] valueIteration() {
        return null;
    }

    public static void main(String[] args) throws Exception {
        
        ///// Initial Setup
        int[] states = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] rewards = {0, 50, 0, 50, 0, 50, 0, 50, 0, 50, 100, 50, 200, 50, 0, 50};

        ///// Value Iteration
    }
}
