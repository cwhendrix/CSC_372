import java.util.ArrayList;

public class Node {
    // integer representing which variable this node corresponds to
    // this will also correspond to what index it will be in the binary string representation of the variables
    private int variable;
    // list of the variable's "parents" in the Bayes Net
    private ArrayList<Integer> parents;

    public Node(int variable) {
        this.variable = variable;
        parents = new ArrayList<Integer>();
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
}
