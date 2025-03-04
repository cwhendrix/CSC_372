package rubikssolver;

public class Node implements Comparable<Node>{
    public Cube state;
    public int priority;
    public Node parent;
    public char lastMove;
    public int pathCost;

    public Node(Cube state, int priority, Node parent, char lastMove, int pathCost) {
        this.state = state;
        this.priority = priority;
        this.parent = parent;
        this.lastMove = lastMove;
        this.pathCost = pathCost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.priority, other.priority);
    }
}
