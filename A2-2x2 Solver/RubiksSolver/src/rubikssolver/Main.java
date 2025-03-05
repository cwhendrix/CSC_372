package rubikssolver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;
import java.util.Stack;
import java.util.PriorityQueue;

public class Main {
    static Scanner scn = new Scanner(System.in);
    ////// guiPrint Function   
    /// This function is used to print out the GUI of the model. 
    /// An example of the gui is as follows: 
    /// 
    /// ============================
    /// Current Cubelet Order: 
    ///    a, b, c, d, e, f, g, h
    /// Next move:
    /// 1. F-Turn (-1 for reverse)
    /// 2. L-Turn (-2 for reverse)
    /// 3. U-Turn (-3 for reverse)
    /// 4. R-Turn (-4 for reverse)
    /// 5. B-Turn (-5 for reverse)
    /// 6. D-Turn (-6 for reverse)
    /// 7. Check if solved. 
    /// 8. Randomize cube.
    /// 9. Exit.
    public static int guiPrint(Cube cube) {
            int response = -1; 
            System.out.println("============================");
            System.out.println("Current Cubelet Order: ");
            System.out.println(Arrays.toString(cube.getCubeletOrder()));
            System.out.println("Next move:");
            System.out.println("1. F-Turn (-1 for reverse).\n" +
                            "2. L-Turn (-2 for reverse).\n" +
                            "3. U-Turn (-3 for reverse).\n" +
                            "4. R-Turn (-4 for reverse).\n" +
                            "5. B-Turn (-5 for reverse).\n" + 
                            "6. D-Turn (-6 for reverse).\n" + 
                            "7. Check if solved. \n" +
                            "8. Randomize cube.\n" + 
                            "9. Exit.\n");
            System.out.println("Input:");
            response = Integer.valueOf(scn.nextLine());        
            return response;    
            //TODO: Handle invalid input
        }

    public static void BFS(int depth, Cube cube, Queue<Node> queue, CubeManipulator cubeManip) {
        int nodesExplored = 0;
        boolean issolved = false;
        Node curr; 
        long startTime = System.currentTimeMillis();

        // Enqueue first node to set
            // Compute root's priority
        Node root = new Node(cube, 1, null, ' ', 0);
        queue.add(root);
        while (issolved != true) {
            // pop item from queue
            curr = queue.remove();
            nodesExplored++;

            // check if solved
            issolved = curr.state.isSolved();
            if (issolved == true) {
                long estimatedTime = (System.currentTimeMillis() - startTime)/60000;    // convert to minutes
                System.out.println("===================================");
                System.out.println("Breadth-First Search Finished.");
                System.out.println("Depth: "+ depth);
                System.out.println("Nodes Visited: " + nodesExplored);
                System.out.println("Size of Queue: " + queue.size());
                System.out.println("Wall Time: " + estimatedTime + " ms");
                return;
            }

            // if we're here, not solved; enqueue children
        }
    }

    public static int DFS(int maxDepth, Cube cube, Stack<Node> stack, CubeManipulator cubeManip) {
        int nodesExplored = 0;
        boolean issolved = false;
        Node curr; 

        // Enqueue first node to set
            // Compute root's priority
        Node root = new Node(cube, 1, null, ' ', 0);
        stack.add(root);
        while (issolved != true) {
            // pop item from queue
            curr = stack.pop();
            nodesExplored++;

            // check if solved
            issolved = curr.state.isSolved();
            if (issolved == true) {
                return nodesExplored;
            }

            // if we're here, not solved; enqueue children
            // enqueue if it's not past maxDepth
            if (curr.pathCost + 1 <= maxDepth) {

            }
        }
        return 0;
    }

    public static void IDS(int depth, Cube cube, Stack<Node> stack, CubeManipulator cubeManip) {
        int nodesExplored = 0;
        boolean issolved = false;
        Node curr; 
        long startTime = System.currentTimeMillis();
        int i = 1;
        int temp;
        while (issolved != true) {
            temp = DFS(i, cube, stack, cubeManip);
            nodesExplored += temp;
            if (issolved != true) {
                stack.clear();
            }
        }

    }

    public static void main(String[] args) {
        
        CubeManipulator cubeManip = new CubeManipulator();
        Cube cube = new Cube();
        int depth = 1;
        Queue<Node> BFSqueue = new LinkedList<>();



        Stack<Node> IDSstack = new Stack<>();




        PriorityQueue<Node> IDAPQ = new PriorityQueue<>();

        System.out.println("Current Cubelet Order: ");
        System.out.println(Arrays.toString(cube.getCubeletOrder()));
        cubeManip.fTurn(cube);
        System.out.println("FTurn:");
        System.out.println(Arrays.toString(cube.getCubeletOrder()));
        cubeManip.negFTurn(cube);
        System.out.println("Neg. FTurn:");
        System.out.println(Arrays.toString(cube.getCubeletOrder()));
        System.out.println("DTurn:");
        cubeManip.dTurn(cube);
        System.out.println(Arrays.toString(cube.getCubeletOrder()));
    }
}
