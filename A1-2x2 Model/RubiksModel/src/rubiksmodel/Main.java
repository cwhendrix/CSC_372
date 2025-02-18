package rubiksmodel;

import java.util.Arrays;
import java.util.Scanner;

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
    /// 1. F-Turn
    /// 2. L-Turn
    /// 3. U-Turn
    /// 4. R-Turn
    /// 5. B-Turn
    /// 6. D-Turn
    /// 7. Check if solved. 
    /// 8. Randomize cube.
    /// 9. Exit.
    public static int guiPrint(Cube cube) {
            int response = -1; 
            System.out.println("============================");
            System.out.println("Current Cubelet Order: ");
            System.out.println(Arrays.toString(cube.getCubeletOrder()));
            System.out.println("Next move:");
            System.out.println("1. F-Turn\n" +
                            "2. L-Turn\n" +
                            "3. U-Turn\n" +
                            "4. R-Turn\n" +
                            "5. B-Turn\n" + 
                            "6. D-Turn\n" + 
                            "7. Check if solved. \n" +
                            "8. Randomize cube.\n" + 
                            "9. Exit.\n");
            System.out.println("Input:");
            response = Integer.valueOf(scn.nextLine());        
            return response;    
            //TODO: Handle invalid input
        }
    
    public static void main(String[] args) {
        CubeManipulator cubeManip = new CubeManipulator();
        Cube cube = new Cube();
        int response = -1;

        while (response != 9) {
            response = guiPrint(cube);
            if (response == 1) {
                // System.out.println("F-Turn Chosen");
                cubeManip.fTurn(cube);
            } else if (response == 2) {
                // System.out.println("L-Turn Chosen");
                cubeManip.lTurn(cube);
            } else if (response == 3) {
                // System.out.println("U-Turn Chosen");
                cubeManip.uTurn(cube);
            } else if (response == 4) {
                // System.out.println("R-Turn Chosen");
                cubeManip.rTurn(cube);
            } else if (response == 5) {
                // System.out.println("B-Turn Chosen");
                cubeManip.bTurn(cube);
            } else if (response == 6) {
                // System.out.println("D-Turn Chosen");
            } else if (response == 7) {
                System.out.println("Check Chosen");
            } else if (response == 8) {
                System.out.println("Randomize Chosen");
                cubeManip.randomizeCube(cube);
            } else if (response == 9) {
                System.out.println("Exit Chosen");
            } else {
                System.out.println("Invalid input detected");
            }
        }
    }
}
