import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        ////// SETUP TIME
        /// create nodes
        ArrayList<Node> nodes = new ArrayList<Node>();
        Scanner scn = new Scanner(System.in);

        Node burglarNode = new Node(0, "Burglar", 0);
        double[] burglarCPT = {0.02};
        burglarNode.setCpt(burglarCPT);
        // System.out.println(Arrays.toString(burglarNode.getCpt()));
        nodes.add(burglarNode);

        Node earthNode = new Node(1, "Earthquake", 0);
        double[] earthCPT = {0.03};
        earthNode.setCpt(earthCPT);
        // System.out.println(Arrays.toString(earthNode.getCpt()));
        nodes.add(earthNode);

        Node alarmNode = new Node(2, "Alarm", 2);
        double[] alarmCPT = {0.03, 0.36, 0.92, 0.97};   // FF, FT, TF, TT
        alarmNode.setCpt(alarmCPT);
        alarmNode.parents.add(0);
        alarmNode.parents.add(1);
        // System.out.println(Arrays.toString(alarmNode.getCpt()));
        nodes.add(alarmNode);

        Node johnNode = new Node(3, "John Calls", 1);
        double[] johnCPT = {0.07, 0.85};    // F, T
        johnNode.setCpt(johnCPT);
        johnNode.parents.add(2);
        // System.out.println(Arrays.toString(johnNode.getCpt()));
        nodes.add(johnNode);

        Node maryNode = new Node(4, "Mary Calls", 1);
        double[] maryCPT = {0.02, 0.69};
        maryNode.setCpt(maryCPT);
        maryNode.parents.add(2);
        // System.out.println(Arrays.toString(maryNode.getCpt()));
        nodes.add(maryNode);

        // THIS HOLDS THE JOINT DISTRIBUTION
        double[] jointDist = new double[(int) Math.pow(2, nodes.size()) - 1];
        JDConstructor(jointDist, nodes);
        // System.out.println(Arrays.toString(jointDist));

        while (true) {
            String response = printGUI(scn);
            ArrayList<Integer> queryVars = new ArrayList<Integer>(nodes.size() + 1);    // evidencevars must be a subset of queryvars. that is, all elements of eviVars are also elements of queryvars
            ArrayList<Integer> evidenceVars = new ArrayList<Integer>(nodes.size() + 1);
            // System.out.println("INPUT: " + response);
            parseInput(response, queryVars, evidenceVars);
            // System.out.println(queryVars);
            // System.out.println(evidenceVars);
            System.out.println("=====");
            System.out.println("EXACT INFERENCE: JOINT DISTRIBUTION");
            System.out.println("Probability (as decimal): " + jdQuery(jointDist, queryVars, evidenceVars));
            System.out.println("=====");
            System.out.println("APPROXIMATE INFERENCE: REJECTION SAMPLING");
            ArrayList<String> simulations = runSimulations(nodes, 100000);
            System.out.println("Probability (as decimal): " + rsQuery(simulations, queryVars, evidenceVars));
        }

        // System.out.println(jdQuery(jointDist, queryVars, evidenceVars));

        // ArrayList<String> simulations = runSimulations(nodes, 100000);
        // System.out.println(simulations);
        // System.out.println(rsQuery(simulations, queryVars, evidenceVars));
    }

    public static void JDConstructor(double[] jd, ArrayList<Node> nodes) {
        String currDist;
        double probAccumulator = 1;
        for (int i = 0; i <= jd.length - 1; i++) { //// For each entry in the JD
            probAccumulator = 1;
            currDist = String.format("%5s", Integer.toBinaryString(i)).replace(' ', '0');   // format = number of nodes 
            // System.out.println(currDist);
            // System.out.println(currDist.length());
            int nodeIterator = 0;
            for (int j = currDist.length() - 1; j >= 0; j--) {  // for each node in that entry of the JD
                // System.out.println(currDist.charAt(j));
                Node currVar = nodes.get(nodeIterator);
                // System.out.println(currVar.getName());
                if (currVar.parents.isEmpty()) {    /// no parents; it's just the value of this digit that matters :)
                    if (currDist.charAt(j) == '0') {    // false
                        probAccumulator = probAccumulator * (1 - currVar.getCpt()[0]);
                    } else {    // true
                        probAccumulator = probAccumulator * currVar.getCpt()[0];
                    }
                } else {   // we have parents, we have to figure this out
                    String currBiStr = "";
                    //for (int k = 0; k <= currVar.parents.size(); k++) {     // For each parent, add to binary string for this node
                    for (Integer parent : currVar.parents) {
                        String tempBiStr = String.valueOf(currDist.charAt(currDist.length() - parent - 1));
                        currBiStr = tempBiStr + currBiStr; 
                    }
                    int BiStr = Integer.parseInt(currBiStr, 2);
                    if (currDist.charAt(j) == '0') {    // false
                        probAccumulator = probAccumulator * (1 - currVar.getCpt()[BiStr]);
                    } else {    // true
                        probAccumulator = probAccumulator * currVar.getCpt()[BiStr];
                    }
                }
                // System.out.println(probAccumulator);
                nodeIterator++;
            }
            jd[i] = probAccumulator;
        }
    }

    public static double jdQuery(double[] jd, ArrayList<Integer> queryVars, ArrayList<Integer> evidenceVars) {
        // queryVars.add(0, null);     // element is the polarity, index is the variable
        double queryAccumulator = 0;
        double evidenceAccumulator = 0;
        if (evidenceVars.isEmpty()) {
            evidenceAccumulator = 1;
        }
        boolean queryHolds = true;
        boolean evidenceHolds = true;
        for (int i = 0; i <= jd.length - 1; i++) {  // for each combination in the joint distribution
            queryHolds = true;
            evidenceHolds = true;
            String currDist = String.format("%5s", Integer.toBinaryString(i)).replace(' ', '0');
            for (Integer queryVar : queryVars) {    // for each variable in the joint distribution
                if (queryVar != null) {
                    int varIndex = currDist.length() - queryVars.indexOf(queryVar) - 1; // index of the item is the variable's number
                    int varPol = queryVar;      // element is the polarity
                    if (!String.valueOf(currDist.charAt(varIndex)).equals(Integer.toString(varPol))) {
                        queryHolds = false;
                        break;
                    }
                }
            }
            if (!evidenceVars.isEmpty()) {
                for (Integer eviVar : evidenceVars) {    // for each variable in the joint distribution
                    if (eviVar != null) {
                        int eviIndex = currDist.length() - evidenceVars.indexOf(eviVar) - 1; // index of the item is the variable's number
                        int eviPol = eviVar;      // element is the polarity
                        if (!String.valueOf(currDist.charAt(eviIndex)).equals(Integer.toString(eviPol))) {
                            evidenceHolds = false;
                            break;
                        }
                    }
                }
            }
            if (queryHolds) {
                queryAccumulator = queryAccumulator + jd[i];
            }
            if (evidenceHolds && !evidenceVars.isEmpty()) {
                evidenceAccumulator = evidenceAccumulator + jd[i];
            }
        }
        if (evidenceAccumulator == 0) {
            evidenceAccumulator = 1;
        }
        return (queryAccumulator / evidenceAccumulator);
    }

    public static ArrayList<String> runSimulations(ArrayList<Node> nodes, int numSims) {
        Random random = new Random();
        double rand = 0;
        ArrayList<String> sims = new ArrayList<String>();
        
        for (int i = 0; i <= numSims; i++) {    // for each simulation
            String currSim = "";
            for(Node node : nodes) {    // calculate each variable for the simulation
                if (node.parents.isEmpty()) {
                    rand = random.nextDouble();
                    if (rand <= node.getCpt()[0]) {
                        currSim = "1" + currSim;
                    } else {
                        currSim = "0" + currSim;
                    }
                } else {
                    String parString = "";
                    for (Integer parent : node.parents) {
                        parString = String.valueOf(currSim.charAt(currSim.length() - parent - 1)) + parString;
                    }
                    int nodeParents = Integer.parseInt(parString, 2);
                    rand = random.nextDouble();
                    if (rand <= node.getCpt()[nodeParents]) {
                        currSim = "1" + currSim;
                    } else {
                        currSim = "0" + currSim;
                    }
                }
            }
            // Put into the array of simulations
            sims.add(currSim);
        }
        return sims;
    }

    public static double rsQuery(ArrayList<String> sims, ArrayList<Integer> queryVars, ArrayList<Integer> evidenceVars) {
        double queryAccumulator = 0;
        double evidenceAccumulator = 0;
        if (evidenceVars.isEmpty()) {
            evidenceAccumulator = sims.size();
        }
        boolean queryHolds = true;
        boolean evidenceHolds = true;
        for (String simulation : sims) {
            queryHolds = true;
            evidenceHolds = true;
            for (Integer queryVar : queryVars) {
                if (queryVar != null) {
                    int varIndex = simulation.length() - queryVars.indexOf(queryVar) - 1; // index of the item is the variable's number
                    int varPol = queryVar;      // element is the polarity
                    if (!String.valueOf(simulation.charAt(varIndex)).equals(Integer.toString(varPol))) {
                        queryHolds = false;
                        break;
                    }
                }
            }
            if (!evidenceVars.isEmpty()) {
                for (Integer eviVar : evidenceVars) {    // for each variable in the joint distribution
                    if (eviVar != null) {
                        int eviIndex = simulation.length() - evidenceVars.indexOf(eviVar) - 1; // index of the item is the variable's number
                        int eviPol = eviVar;      // element is the polarity
                        if (!String.valueOf(simulation.charAt(eviIndex)).equals(Integer.toString(eviPol))) {
                            evidenceHolds = false;
                            break;
                        }
                    }
                }
            }
            if (queryHolds) {
                queryAccumulator = queryAccumulator + 1;
            }
            if (evidenceHolds && !evidenceVars.isEmpty()) {
                evidenceAccumulator = evidenceAccumulator + 1;
            }
        }
        if (evidenceAccumulator == 0) {
            evidenceAccumulator = sims.size();
        }
        return (queryAccumulator / evidenceAccumulator);
    }

    public static String printGUI(Scanner scn) {
        String response = ""; 
        System.out.println("============================");
        System.out.println("PEARL'S BAYES NET INTERFACE");
        System.out.println("Input Query");
        System.out.println("- Begin query with 'bnet'\n" +
                        "- Give variables as NP, where N = variable number and P = Polarity (t/f)\n" +
                        "- Separate query and evidence variables with 'given'\n" +
                        "- Ex. bnet 0t 2f given 4f\n" +
                        "VARIABLE NUMBERS:\n" +
                        "0. Burglar\n" + 
                        "1. Earthquake\n" + 
                        "2. Alarm\n" +
                        "3. John Calls\n" + 
                        "4. Mary Calls\n");
        System.out.println("Input:");
        response = scn.nextLine();        
        return response;
    }

    public static void parseInput(String input, ArrayList<Integer> queryVars, ArrayList<Integer> evidenceVars) {
        String[] query = input.split(" ");
        boolean isQueryVar = true;
        int[] queries = new int[5];
        Arrays.fill(queries, -1);
        int[] evidence = new int[5];
        Arrays.fill(evidence, -1);
        for (int i = 0; i < query.length; i++) {
            // System.out.println(query[i]);
            if (query[i].equals("given")) {
                isQueryVar = false;
            } else if (query[i].equals("bnet")) {
                // nothing :)
            } else {    // we've reached a variable
                int varNum = Integer.parseInt(String.valueOf(query[i].charAt(0)));  // number of the query
                if (query[i].charAt(1) == 't' && isQueryVar) {
                    queries[varNum] = 1;
                } else if (query[i].charAt(1) == 'f' && isQueryVar) {
                    queries[varNum] = 0;
                } else if (query[i].charAt(1) == 't' && !isQueryVar) {
                    queries[varNum] = 1;
                    evidence[varNum] = 1;
                } else if (query[i].charAt(1) == 'f' && !isQueryVar) {
                    queries[varNum] = 0;
                    evidence[varNum] = 0;
                }
            }
        }
        // System.out.println(Arrays.toString(queries));
        // System.out.println(Arrays.toString(evidence));
        for (int i = 0; i < queries.length; i++) {
            if (queries[i] == -1) {
                queryVars.add(null);
            } else {
                queryVars.add(queries[i]);
            }
        }
        for (int i = 0; i < evidence.length; i++) {
            if (evidence[i] == -1) {
                evidenceVars.add(null);
            } else {
                evidenceVars.add(evidence[i]);
            }
        }
    }
}
