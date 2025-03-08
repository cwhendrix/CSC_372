package rubikssolver;

public class Node implements Comparable<Node>{
    public Cube state;
    public double priority;
    public Node parent;
    public MOVES lastMove;
    public int pathCost;

    public Node(Cube state, double priority, Node parent, MOVES lastMove, int pathCost) {
        this.state = state;
        this.priority = priority;
        this.parent = parent;
        this.lastMove = lastMove;
        this.pathCost = pathCost;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.priority, other.priority);
    }
}
