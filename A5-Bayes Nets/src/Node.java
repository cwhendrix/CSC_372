import java.util.ArrayList;

public class Node {
    // integer representing which variable this node corresponds to
    // this will also correspond to what index it will be in the binary string representation of the variables
    private int variable;

    // Sets the name of the node, just for our sake
    private String name; 

    // list of the variable's "parents" in the Bayes Net
    public ArrayList<Integer> parents;

    // representation of the CPT as an array of doubles
    // The binary encoding of those parents combinations will correspond to where the probabilities are
    // Ex. If a node has two parents, A and B, the encoding for A=T and B=F = 01. 
    // This means that the probability of the variable given A=T and B=F will be stored in index 01 = 1. 
    // Probabilities for true are stored here. Probability of the varable being false can be found by subtracting this probability from 1.
    private double[] cpt;

    public Node(int variable, String name, int numParents) {
        this.variable = variable;
        this.name = name;
        parents = new ArrayList<Integer>();
        if (numParents >= 1) {
            cpt = new double[(int) Math.pow(2, parents.size())];
        } else {    // Node has no parents; only has a probability of true or false
            cpt = new double[1];
        }
        
    }

    // Getters & Setters
    public void setVariable(int variable) {
        this.variable = variable;
    }
    public int getVariable() {
        return variable;
    }
    public ArrayList<Integer> getParents() {
        return parents;
    }

    public void setParents(ArrayList<Integer> parents) {
        this.parents = parents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getCpt() {
        return cpt;
    }

    public void setCpt(double[] cpt) {
        this.cpt = cpt;
    }
}
