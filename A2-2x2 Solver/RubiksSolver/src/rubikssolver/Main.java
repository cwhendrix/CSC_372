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

    ////// BREADTH-FIRST SEARCH
    public static void BFS(int depth, Cube cube, Queue<Node> queue, CubeManipulator cubeManip) {
        int nodesExplored = 0;
        boolean issolved = false;
        Node curr; 
        long startTime = System.currentTimeMillis();

        // Enqueue first node to set
        // Priority is irrelevant since it isn't a factor in BFS
        Node root = new Node(cube, 1, null, MOVES.None, 0);
        queue.add(root);
        while (issolved != true) {
            // pop item from queue
            curr = queue.remove();
            // System.out.println("Current Node State: ");
            // System.out.println(Arrays.toString(curr.state.getCubeletOrder()));
            // System.out.println("Current Queue Size: ");
            // System.out.println(queue.size());

            // check if solved
            issolved = curr.state.isSolved();
            if (issolved == true) {
                Node trans = curr;
                long estimatedTime = (System.currentTimeMillis() - startTime);    // convert to minutes by /60000
                System.out.println("===================================");
                System.out.println("Breadth-First Search Finished.");
                System.out.println("Depth: "+ depth);
                System.out.println("Nodes Visited: " + nodesExplored);
                System.out.println("Size of Queue: " + queue.size());
                System.out.println("Wall Time: " + estimatedTime + " ms");
                // while (trans != null) {
                //    System.out.println(trans.lastMove);
                //    trans = trans.parent;
                //}
                return;
            }
            nodesExplored+=1;

            // if we're here, not solved; enqueue children
            for (MOVES move : MOVES.values()) {
                Cube newCube = new Cube(curr.state.getCubeletOrder());
                if (move == MOVES.FTurn && curr.lastMove != MOVES.NegFTurn) {
                    cubeManip.fTurn(newCube);
                    Node newNode = new Node(newCube, 1, curr, move, curr.pathCost+1);
                    queue.add(newNode);
                } else if (move == MOVES.NegFTurn && curr.lastMove != MOVES.FTurn) {
                    cubeManip.negFTurn(newCube);
                    Node newNode = new Node(newCube, 1, curr, move, curr.pathCost+1);
                    queue.add(newNode);
                } else if (move == MOVES.DTurn && curr.lastMove != MOVES.NegDTurn) {
                    cubeManip.dTurn(newCube);
                    Node newNode = new Node(newCube, 1, curr, move, curr.pathCost+1);
                    queue.add(newNode);
                } else if (move == MOVES.NegDTurn && curr.lastMove != MOVES.DTurn) {
                    cubeManip.negDTurn(newCube);
                    Node newNode = new Node(newCube, 1, curr, move, curr.pathCost+1);
                    queue.add(newNode);
                } else if (move == MOVES.RTurn && curr.lastMove != MOVES.NegRTurn) {
                    cubeManip.rTurn(newCube);
                    Node newNode = new Node(newCube, 1, curr, move, curr.pathCost+1);
                    queue.add(newNode);
                } else if (move == MOVES.NegRTurn && curr.lastMove != MOVES.RTurn) {
                    cubeManip.negRTurn(newCube);
                    Node newNode = new Node(newCube, 1, curr, move, curr.pathCost+1);
                    queue.add(newNode);
                }
            }
        }
    }

    public static int DFS(int maxDepth, Cube cube, Stack<Node> stack, CubeManipulator cubeManip) {
        int nodesExplored = 0;
        boolean issolved = false;
        Node curr; 

        // Enqueue first node to set
        // Priority is irrelevant since it isn't a factor in BFS
        Node root = new Node(cube, 1, null, MOVES.None, 0);
        stack.push(root);
        while (issolved != true) {
            // pop item from queue
            if (stack.empty()) {
                return nodesExplored;
            }
            curr = stack.pop();
            nodesExplored++;

            // check if solved
            issolved = curr.state.isSolved();
            if (issolved == true) {
                Node trans = curr;
                while (trans != null) {
                    System.out.println(trans.lastMove);
                    trans = trans.parent;
                }
                return -(nodesExplored); // negative value indicates that it is solved
            }

            // if we're here, not solved; enqueue children
            // enqueue if it's not past maxDepth
            if (curr.pathCost + 1 <= maxDepth) {
                for (MOVES move : MOVES.values()) {
                    Cube newCube = new Cube(curr.state.getCubeletOrder());
                    if (move == MOVES.FTurn && curr.lastMove != MOVES.NegFTurn) {
                        cubeManip.fTurn(newCube);
                        Node newNode = new Node(newCube, 1, curr, move, curr.pathCost+1);
                        stack.push(newNode);
                    } else if (move == MOVES.NegFTurn && curr.lastMove != MOVES.FTurn) {
                        cubeManip.negFTurn(newCube);
                        Node newNode = new Node(newCube, 1, curr, move, curr.pathCost+1);
                        stack.push(newNode);
                    } else if (move == MOVES.DTurn && curr.lastMove != MOVES.NegDTurn) {
                        cubeManip.dTurn(newCube);
                        Node newNode = new Node(newCube, 1, curr, move, curr.pathCost+1);
                        stack.push(newNode);
                    } else if (move == MOVES.NegDTurn && curr.lastMove != MOVES.DTurn) {
                        cubeManip.negDTurn(newCube);
                        Node newNode = new Node(newCube, 1, curr, move, curr.pathCost+1);
                        stack.push(newNode);
                    } else if (move == MOVES.RTurn && curr.lastMove != MOVES.NegRTurn) {
                        cubeManip.rTurn(newCube);
                        Node newNode = new Node(newCube, 1, curr, move, curr.pathCost+1);
                        stack.push(newNode);
                    } else if (move == MOVES.NegRTurn && curr.lastMove != MOVES.RTurn) {
                        cubeManip.negRTurn(newCube);
                        Node newNode = new Node(newCube, 1, curr, move, curr.pathCost+1);
                        stack.push(newNode);
                    }
                }
            }
        }
        return 0;
    }

    public static void IDS(int depth, Cube cube, Stack<Node> stack, CubeManipulator cubeManip) {
        int nodesExplored = 0;
        boolean issolved = false;
        long startTime = System.currentTimeMillis();
        int i = 1;
        int temp;
        while (issolved != true) {
            if (!stack.isEmpty()) {
                stack.clear();
            }
            temp = DFS(i, cube, stack, cubeManip);
            if (temp < 0) {
                issolved = true;
                temp = temp * -1;
            }
            nodesExplored += temp;
            i += 1;
        }
        long estimatedTime = (System.currentTimeMillis() - startTime);
        System.out.println("===================================");
        System.out.println("Iterative Deepening Search Finished.");
        System.out.println("Depth: "+ depth);
        System.out.println("Nodes Visited: " + nodesExplored);
        System.out.println("Size of Stack: " + stack.size());
        System.out.println("Wall Time: " + estimatedTime + " ms");
    }

    public static void main(String[] args) {
        
        CubeManipulator cubeManip = new CubeManipulator();
        Cube cube = new Cube();
        int depth = 1;
        Queue<Node> BFSqueue = new LinkedList<>();
        Stack<Node> IDSstack = new Stack<>();
        PriorityQueue<Node> IDAPQ = new PriorityQueue<>();
        //Cube testcube = new Cube();
        //cubeManip.negFTurn(testcube);

        System.out.println("Current Cubelet Order: ");
        System.out.println(Arrays.toString(cube.getCubeletOrder()));
        cubeManip.randomizeCube(cube, 3);
        System.out.println("Randomized Cubelet Order: ");
        System.out.println(Arrays.toString(cube.getCubeletOrder()));
        System.out.println("Iterative Deepening Search, depth 3.");
        //BFS(3, cube, BFSqueue, cubeManip);
        IDS(3, cube, IDSstack, cubeManip);
    }
}
