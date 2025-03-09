package rubikssolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.PriorityQueue;

public class Main {
    ////// BREADTH-FIRST SEARCH
    public static void BFS(int depth, Cube cube, Queue<Node> queue, CubeManipulator cubeManip, PrintWriter writer) {
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
                writer.println("===================================");
                writer.println("Breadth-First Search Finished.");
                writer.println("Depth: "+ depth);
                writer.println(nodesExplored + " visited, " + queue.size() + " nodes long, " + estimatedTime + " ms.");
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

    ////// DEPTH-FIRST SEARCH
    public static int DFS(int maxDepth, Cube cube, Stack<Node> stack, CubeManipulator cubeManip, PrintWriter writer) {
        int nodesExplored = 0;
        boolean issolved = false;
        Node curr; 

        // Enqueue first node to set
        // Priority is irrelevant since it isn't a factor in BFS
        Node root = new Node(cube, 1, null, MOVES.None, 0);
        stack.push(root);
        while (issolved != true) {
            // pop item from queue
            if (stack.empty()) {    // If stack is empty, we've exhausted our searchable space
                return nodesExplored;
            }
            curr = stack.pop();
            nodesExplored++;

            // check if solved
            issolved = curr.state.isSolved();
            if (issolved == true) {
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
        return nodesExplored;
    }

    ////// ITERATIVE DEEPENING SEARCH
    public static void IDS(int depth, Cube cube, Stack<Node> stack, CubeManipulator cubeManip, PrintWriter writer) {
        int nodesExplored = 0;
        boolean issolved = false;
        long startTime = System.currentTimeMillis();
        int i = 1;
        int temp;
        while (issolved != true) {
            if (!stack.isEmpty()) {
                stack.clear();
            }
            temp = DFS(i, cube, stack, cubeManip, writer);
            if (temp < 0) {
                issolved = true;
                temp = temp * -1;
            }
            nodesExplored += temp;
            i += 1;
        }
        long estimatedTime = (System.currentTimeMillis() - startTime);
        writer.println("===================================");
        writer.println("Iterative Deepening Search Finished.");
        writer.println("Depth: "+ depth);
        writer.println(nodesExplored + " visited, " + stack.size() + " nodes long, " + estimatedTime + " ms.");
    }

    ////// Heuristic Function
    public static double heuristic(Cube cube) {
        double priority = 0;
        String[] cubeletOrder = cube.getCubeletOrder();
        int[][] moves = {{0,1,2,1,2,0,2,1},
                         {1,2,1,0,1,0,1,2},
                         {2,1,0,1,2,0,2,2},
                         {1,0,1,2,3,0,1,2},
                         {2,3,2,1,0,0,2,1},
                         {0,0,0,0,0,0,0,0},
                         {2,1,2,1,2,0,0,1},
                         {1,2,2,2,1,0,1,0}};
        for (int i=0; i<=7; i++) {
            if (cubeletOrder[i].charAt(0) =='a') {
                priority += moves[0][i];
            } else if (cubeletOrder[i].charAt(0) =='d') {
                priority += moves[1][i];
            } else if (cubeletOrder[i].charAt(0) =='c') {
                priority += moves[2][i];
            } else if (cubeletOrder[i].charAt(0) =='b') {
                priority += moves[3][i];
            } else if (cubeletOrder[i].charAt(0) =='e') {
                priority += moves[4][i];
            } else if (cubeletOrder[i].charAt(0) =='f') {
                priority += 0;
            } else if (cubeletOrder[i].charAt(0) =='g') {
                priority += moves[6][i];
            } else if (cubeletOrder[i].charAt(0) =='h') {
                priority += moves[7][i];
            }
        } 
        priority = (priority / 4);
        return priority;
    }
    
    ////// BEST-FIRST SEARCH WITH A*
    public static int BFSA(int maxdepth, Cube cube, PriorityQueue<Node> pq, CubeManipulator cubeManip, PrintWriter writer) {
        int nodesExplored = 0;
        boolean issolved = false;
        Node curr;
        HashSet<Cube> explored = new HashSet<Cube>();

        // Enqueue first node to set
        // Priority is irrelevant on the root node
        Node root = new Node(cube, 0, null, MOVES.None, 0);
        pq.add(root);
        while (issolved != true) {
            if (pq.isEmpty()) {    // If stack is empty, we've exhausted our searchable space
                return nodesExplored;
            }
            // remove first node from pq
            curr = pq.remove();
            explored.add(curr.state);

            // System.out.println("Current Node State: ");
            // System.out.println(Arrays.toString(curr.state.getCubeletOrder()));
            // System.out.println("Current Queue Size: ");
            // System.out.println(pq.size());

            issolved = curr.state.isSolved();
            if (issolved == true) {
                return -(nodesExplored); // negative value indicates that it is solved
            }
            nodesExplored++;

            // if we're here, not solved; enqueue children
            if (curr.pathCost < maxdepth) {
                for (MOVES move : MOVES.values()) {
                    Cube newCube = new Cube(curr.state.getCubeletOrder());
                    double priority;
                    if (move == MOVES.FTurn && curr.lastMove != MOVES.NegFTurn) {
                        cubeManip.fTurn(newCube);
                        priority = heuristic(newCube) + curr.pathCost + 1;
                        Node newNode = new Node(newCube, priority, curr, move, curr.pathCost+1);
                        if (!explored.contains(newCube)){
                            pq.add(newNode);
                        } else {
                            //System.out.println("Removed code: " + newCube.getStateCode());
                        }
                    } else if (move == MOVES.NegFTurn && curr.lastMove != MOVES.FTurn) {
                        cubeManip.negFTurn(newCube);
                        priority = heuristic(newCube) + curr.pathCost+1;
                        Node newNode = new Node(newCube, priority, curr, move, curr.pathCost+1);
                        if (!explored.contains(newCube)){
                            pq.add(newNode);
                        } else {
                            //System.out.println("Removed code: " + newCube.getStateCode());
                        }
                    } else if (move == MOVES.DTurn && curr.lastMove != MOVES.NegDTurn) {
                        cubeManip.dTurn(newCube);
                        priority = heuristic(newCube) + curr.pathCost+1;
                        Node newNode = new Node(newCube, priority, curr, move, curr.pathCost+1);
                        if (!explored.contains(newCube)){
                            pq.add(newNode);
                        } else {
                            //System.out.println("Removed code: " + newCube.getStateCode());
                        }
                    } else if (move == MOVES.NegDTurn && curr.lastMove != MOVES.DTurn) {
                        cubeManip.negDTurn(newCube);
                        priority = heuristic(newCube) + curr.pathCost+1;
                        Node newNode = new Node(newCube, priority, curr, move, curr.pathCost+1);
                        if (!explored.contains(newCube)){
                            pq.add(newNode);
                        } else {
                            //System.out.println("Removed code: " + newCube.getStateCode());
                        }
                    } else if (move == MOVES.RTurn && curr.lastMove != MOVES.NegRTurn) {
                        cubeManip.rTurn(newCube);
                        priority = heuristic(newCube) + curr.pathCost+1;
                        Node newNode = new Node(newCube, priority, curr, move, curr.pathCost+1);
                        if (!explored.contains(newCube)){
                            pq.add(newNode);
                        } else {
                            // System.out.println("Removed code: " + newCube.getStateCode());
                        }
                    } else if (move == MOVES.NegRTurn && curr.lastMove != MOVES.RTurn) {
                        cubeManip.negRTurn(newCube);
                        priority = heuristic(newCube) + curr.pathCost+1;
                        Node newNode = new Node(newCube, priority, curr, move, curr.pathCost+1);
                        if (!explored.contains(newCube)){
                            pq.add(newNode);
                        } else {
                            //System.out.println("Removed code: " + newCube.getStateCode());
                        }
                    }
                }  
            }
        }
        return nodesExplored;
    }

    ////// Iterative deepening A*
    public static void IDA(int depth, Cube cube, PriorityQueue<Node> pq, CubeManipulator cubeManip, PrintWriter writer) {
        int nodesExplored = 0;
        boolean issolved = false;
        long startTime = System.currentTimeMillis();
        int i = 1;
        int temp;
        while (issolved != true) {
            System.out.println("Solution Depth: "+ depth + ", Max Depth: " + i);
            if (!pq.isEmpty()) {
                pq.clear();
            }
            temp = BFSA(i, cube, pq, cubeManip, writer);
            if (temp < 0) {
                issolved = true;
                temp = temp * -1;
            }
            nodesExplored += temp;
            i += 1;
        }
        if (issolved == true) {
            // Node trans = curr;
            long estimatedTime = (System.currentTimeMillis() - startTime);    // convert to minutes by /60000
            writer.println("===================================");
            writer.println("Best-First Search Finished.");
            writer.println("Depth: "+ depth);
            writer.println(nodesExplored + " visited, " + pq.size() + " nodes long, " + estimatedTime + " ms.");
            // while (trans != null) {
            //    System.out.println(trans.lastMove);
            //    trans = trans.parent;
            //}
            return;
        }
    }

    public static Cube[] setup(int depth, CubeManipulator cubeManip) {
        Cube[] cubeSet = new Cube[20];
        Cube temp;
        for (int i = 0; i < 20; i++) {
            temp = new Cube();
            cubeManip.randomizeCube(temp, depth);
            cubeSet[i] = temp;
        }
        return cubeSet;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream file = new FileOutputStream("output.txt");
        PrintWriter writer = new PrintWriter(file, true);
        CubeManipulator cubeManip = new CubeManipulator();
        Cube[] experimentSet;
        Queue<Node> BFSqueue = new LinkedList<>();
        Stack<Node> IDSstack = new Stack<>();
        PriorityQueue<Node> IDAPQ = new PriorityQueue<>(new NodeComparator());
        //Cube test = new Cube();
        //cubeManip.negFTurn(test);
        //cubeManip.negDTurn(test);
        //IDA(2, test, IDAPQ, cubeManip, writer);
        
        //Cube test = new Cube();
        //cubeManip.randomizeCube(test, 3);
        //IDA(3, test, IDAPQ, cubeManip, writer); */

        for (int i=1; i <= 10; i++){
            writer.println("============== BEST FIRST SEARCH  W. A* - DEPTH " + i + " ==============");
            experimentSet = setup(i, cubeManip);
            for (int j=0; j < 20; j++) {
                IDA(i, experimentSet[j], IDAPQ, cubeManip, writer);
            }
            if (!IDAPQ.isEmpty()) {
                IDAPQ.clear();
            }
            System.gc();
        }
        
        /* 
        for (int i=1; i <= 20; i++){
            writer.println("============== ITERATIVE DEEPENING SEARCH - DEPTH " + i + " ==============");
            experimentSet = setup(i, cubeManip);
            for (int j=0; j < 20; j++) {
                IDS(i, experimentSet[j], IDSstack, cubeManip, writer);
            }
            if (!IDSstack.isEmpty()) {
                IDSstack.clear();
            }
            System.gc();
        }
            
        
        for (int i=1; i <= 10; i++){
            writer.println("============== BREADTH-FIRST SEARCH - DEPTH " + i + " ==============");
            experimentSet = setup(i, cubeManip);
            for (int j=0; j < 20; j++) {
                BFS(i, experimentSet[j], BFSqueue, cubeManip, writer);
            }
            if (!BFSqueue.isEmpty()) {
                BFSqueue.clear();
            }
            System.gc();
        } */
        
            
        writer.println("Test");
        writer.close();
        //BFS(3, cube, BFSqueue, cubeManip);
        //IDS(3, cube, IDSstack, cubeManip);
    }
}

