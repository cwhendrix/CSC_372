import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        ////// SETUP TIME
        /// create nodes
        ArrayList<Node> nodes = new ArrayList<Node>();

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
        ArrayList<Integer> queryVars = new ArrayList<Integer>(nodes.size() + 1);    // evidencevars must be a subset of queryvars. that is, all elements of eviVars are also elements of queryvars
        ArrayList<Integer> evidenceVars = new ArrayList<Integer>(nodes.size() + 1);

        queryVars.add(0, 1);
        queryVars.add(1, null);
        queryVars.add(2, 0);
        queryVars.add(3, null);
        queryVars.add(4, 0);

        evidenceVars.add(0, null);
        evidenceVars.add(1, null);
        evidenceVars.add(2, null);
        evidenceVars.add(3, null);
        evidenceVars.add(4, 0);

        System.out.println(jdQuery(jointDist, queryVars, evidenceVars));
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
        return (queryAccumulator / evidenceAccumulator);
    }
}
