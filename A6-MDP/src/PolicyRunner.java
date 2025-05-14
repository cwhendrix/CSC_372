public class PolicyRunner {
    public PolicyRunner() {}

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

    public Action[] policyIteration(int[] rewards, double discount, int numIterations) {
        // make default policy
        Action[] policy = new Action[16];
        double[] utility = new double[16];
        for (int i=0; i<policy.length; i++) {
            policy[i] = Action.UP;
            utility[i] = 0;
        }

        while (true) {
            for (int i=0; i < numIterations; i++) {

                /// EVALUATE CURRENT POLICY
                for (State state : State.values()) {
                    double total = 0;
                    for (State statePrime : State.values()) {
                        Action a = policy[state.ordinal()];
                        total += transition(statePrime, state, a) * utility[statePrime.ordinal()];
                    }
                    utility[state.ordinal()] = rewards[state.ordinal()] + discount * total;
                    // System.out.println("UTILITY FOR STATE " + state + ": " + utility[state.ordinal()]);
                }

                boolean unchanged = true;

                /// IMPROVE CURRENT POLICY
                for (State state : State.values()) {
                    double[] sumOverActions = new double[4];
                    double currBestUtility = -1;
                    Action currBestAction = Action.UP;
                    for (Action action : Action.values()) {
                        for (State goalState : State.values()) {
                            sumOverActions[action.ordinal()] += transition(goalState, state, action) * utility[goalState.ordinal()];
                            if (sumOverActions[action.ordinal()] > currBestUtility) {
                                currBestUtility = sumOverActions[action.ordinal()];
                                currBestAction = action;
                            }
                        }
                    }
                    if (currBestUtility > sumOverActions[policy[state.ordinal()].ordinal()]) {
                        // System.out.println("NEW UTILITY FOR STATE " + state + ": " + currBestUtility + " > " + sumOverActions[policy[state.ordinal()].ordinal()]);
                        policy[state.ordinal()] = currBestAction;
                        unchanged = false;
                    }
                }

                if (unchanged) {
                    return policy;
                }

            }
            System.out.println("Algorithm timeout.");
            return null;
        }
    }
}
