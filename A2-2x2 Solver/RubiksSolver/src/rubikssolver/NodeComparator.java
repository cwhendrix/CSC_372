package rubikssolver;
import java.util.*;

public class NodeComparator implements Comparator<Node>{
    public int compare(Node n1, Node n2) {
        if (n1.priority > n2.priority)
            return 1;
        else if (n1.priority < n2.priority)
            return -1;
            return 0;
        }
}
