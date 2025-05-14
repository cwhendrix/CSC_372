import java.util.Arrays;
import java.util.Collections;

public class ValueRunner {
    public ValueRunner() {

    }

    public double transition(State goal, State start, Action action) {
        ////// NORMAL CASES - Moving where you want
        if (goal.ordinal() == start.ordinal() + 4 && action == Action.UP) {    /// they want to go up and they're moving up
            return 0.7;
        }
        if (goal.ordinal() == start.ordinal() - 4 && action == Action.DOWN) {    /// they want to go down and they're moving down
            return 0.7;
        }
        if (goal.ordinal() == start.ordinal() + 1 && action == Action.RIGHT) {    /// they want to go right and they're moving right
            return 0.7;
        }
        if (goal.ordinal() == start.ordinal() - 1 && action == Action.LEFT) {    /// they want to go left and they're moving left
            return 0.7;
        }

        ////// BOUNCE CASES - Moving backwards on on accident
        /// if start mod 4 = 0, if it tries to go left, 90% chance it ends up going to the right
        if (start.ordinal() % 4 == 0 && action == Action.LEFT && goal.ordinal() == start.ordinal() + 1) {
            return 0.9;
        }
        /// if start mod 4 - 3 = 0, if it tries to go right, 90% chance it ends up going to the left
        if (start.ordinal() % 4 == 3 && action == Action.RIGHT && goal.ordinal() == start.ordinal() - 1) {
            return 0.9;
        }
        /// if start ordinality <=3, if it tries to go down, 90% chance it goes up
        if (start.ordinal() <= 3 && action == Action.DOWN && goal.ordinal() == start.ordinal() + 4) {
            return 0.9;
        }
        /// if start ordinality >=12, if it tries to go up, 90% chance it goes up
        if (start.ordinal() >= 12 && action == Action.UP && goal.ordinal() == start.ordinal() - 4) {
            return 0.9;
        }

        ////// NULL MOVEMENT CASE - If you want to stay in the same spot I guess????
        if (start == goal) {
            return 0.1;
        }
        
        ////// If you're here, you're wanting to move to a goal state which you can't actually move to lmao; 0% chance of making it
        return 0.0;
    }

    public Action[] valueIteration(int[] rewards, double discount, double error) {
        double[] utilities = new double[16];
        
        double[] utilitiesPrime = new double[16];
        for (int i = 0; i < utilitiesPrime.length; i++) {
            utilitiesPrime[i] = 0;
        }

        double delta = 1;
        while (delta > ((error * (1.0 - discount)) / discount)) {
            for (int i = 0; i < utilities.length; i++) {
                utilities[i] = utilitiesPrime[i];
            }
            delta = 0;
            for (State state : State.values()) {
                double[] sumOverActions = new double[4];
                double currBestUtility = -1;
                Action currBestAction = Action.UP;
                State currBestGoal = State.s0;
                for (Action action : Action.values()) {
                    sumOverActions[action.ordinal()] = 0;
                    for (State currGoal : State.values()) {
                        sumOverActions[action.ordinal()] += transition(currGoal, state, action) * utilities[currGoal.ordinal()];
                        if (sumOverActions[action.ordinal()] > currBestUtility) {
                            currBestUtility = sumOverActions[action.ordinal()];
                            currBestAction = action;
                            currBestGoal = currGoal;
                        }
                    }
                }
                // System.out.println("BEST ACTION FOR " + state + ": " + currBestAction + ", UTILITY " + currBestUtility);
                utilitiesPrime[state.ordinal()] = rewards[state.ordinal()] + (discount * transition(currBestGoal, state, currBestAction));
                // System.out.println("UTILITIES PRIME FOR STATE " + state + ": " + utilitiesPrime[state.ordinal()]);
                if (Math.abs(utilitiesPrime[state.ordinal()] - utilities[state.ordinal()]) > delta) {
                    delta = (utilitiesPrime[state.ordinal()] - utilities[state.ordinal()]);
                }
            }
            // System.out.println("DELTA = " + delta);
            // System.out.println("(error * (1.0 - discount)) / discount) = " + ((error * (1.0 - discount)) / discount));
        }

        Action[] policy = new Action[16];
        for (State state : State.values()) {
            policy[state.ordinal()] = Action.UP;
            double[] valueofActions = new double[4];
            double currBestUtility = -2;
            Action currBestAction = Action.UP;
            for (Action action : Action.values()) {
                for (State goalState : State.values()) {
                    valueofActions[action.ordinal()] += transition(goalState, state, action) * utilities[goalState.ordinal()];
                }
                if (valueofActions[action.ordinal()] > currBestUtility) {
                    currBestUtility = valueofActions[action.ordinal()];
                    currBestAction = action;
                }
            }
            policy[state.ordinal()] = currBestAction;
        }

        return policy;
    }
}
