import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");

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
        System.out.println(Arrays.toString(jointDist));

    }

    public static void JDConstructor(double[] jd, ArrayList<Node> nodes) {
        String currDist;
        double probAccumulator = 1;
        for (int i = 0; i <= jd.length - 1; i++) { //// For each entry in the JD
            probAccumulator = 1;
            currDist = String.format("%5s", Integer.toBinaryString(i)).replace(' ', '0');   // format = number of nodes 
            System.out.println(currDist);
            //System.out.println(currDist.length());
            int nodeIterator = 0;
            for (int j = currDist.length() - 1; j >= 0; j--) {  // for each node in that entry of the JD
                // System.out.println(currDist.charAt(j));
                Node currVar = nodes.get(nodeIterator);
                System.out.println(currVar.getName());

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

                System.out.println(probAccumulator);
                nodeIterator++;
            }
            jd[i] = probAccumulator;
        }
    }
}
