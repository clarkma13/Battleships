
/**
 * Program used to trial different components of my battleships project
 *
 * Max Clarke
 * 14/5/24
 */
import java.util.Scanner;
public class Trialling
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        final int WIDTH = 11;
        final int HEIGHT = 11;
        final int SHIPS = 5;
        final String SHIPTILE = "■";
        int currentShipLength = 0;
        String shipDirection;
        int placingShipX = 0;
        int placingShipY = 0;
        int missileX;
        int missileY;
        String playerGrid[][] = new String[WIDTH][HEIGHT];
        String computerGrid [][] = new String[WIDTH][HEIGHT];
        String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String [] ships = {"Patrol Boat (2)", "Submarine (3)", "Destroyer (3)", "Battleship (4)", "Carrier (5)"};
        
        System.out.println("Your guesses:");
        //Sets top row of array as numbers
        computerGrid[0][0] = " ";
        for(int x=1; x<WIDTH; x++){
                computerGrid[x][0] = String.valueOf(x);
        }
        for(int y=1; y<HEIGHT; y++){
                computerGrid[0][y] = letters[y-1];
        }
        //Fills the rest of the grid with blank
        for(int y=1; y<HEIGHT; y++){
            for(int x=1; x<WIDTH; x++){
                computerGrid[x][y] = "."; 
            }
        }
        //Print grid
        for(int y=0; y<HEIGHT; y++){
            for(int x=0; x<WIDTH; x++){
                    System.out.print(computerGrid[x][y]+" "); 
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("Your ships:");
        //Sets top row of array as numbers
        playerGrid[0][0] = " ";
        for(int x=1; x<WIDTH; x++){
                playerGrid[x][0] = String.valueOf(x);
        }
        for(int y=1; y<HEIGHT; y++){
                playerGrid[0][y] = letters[y-1];
        }
        //Fills the rest of the grid with blank
        for(int y=1; y<HEIGHT; y++){
            for(int x=1; x<WIDTH; x++){
                playerGrid[x][y] = "."; 
            }
        }
        //Print grid
        for(int y=0; y<HEIGHT; y++){
            for(int x=0; x<WIDTH; x++){
                    System.out.print(playerGrid[x][y]+" "); 
            }
            System.out.println("");
        }
        for(int n=0; n<SHIPS; n++){
            if(n==0){
                currentShipLength = 2;
            } else if(n==1 || n==2){
                currentShipLength = 3;
            } else if(n==3){
                currentShipLength = 4;
            } else {
                currentShipLength = 5;
            }
            
            System.out.println("Where would you like to place your "+ships[n]+"?");
            System.out.println("Letter A-J:");
            String ySelect = keyboard.nextLine();
            for(int i=0; i<letters.length; i++){
                if(letters[i].equals(ySelect.toUpperCase())){
                    placingShipY = i+1;
                }
            }
            
            System.out.println("Number 1-10:");
            placingShipX = keyboard.nextInt();
            keyboard.nextLine();
            
            System.out.println("Would you like the ship to go across or down?");
            System.out.println("(a/d)");
            shipDirection = keyboard.nextLine();
            
            shipPlacement(placingShipX, placingShipY, shipDirection, currentShipLength, SHIPTILE, playerGrid);
            
            gridPrinter(HEIGHT, WIDTH, playerGrid, computerGrid);
        }
        computerShipPlacement(computerGrid, SHIPS, SHIPTILE);
        gridPrinter(HEIGHT, WIDTH, playerGrid, computerGrid);
        
        System.out.println("Where would you like to fire your missile?");
        System.out.println("Letter A-J:");
        String ySelect = keyboard.nextLine();
        for(int i=0; i<letters.length; i++){
            if(letters[i].equals(ySelect.toUpperCase())){
                missileY = i+1;
            }
        }   
        System.out.println("Number 1-10:");
        missileX = keyboard.nextInt();
        keyboard.nextLine();
        
        // computerGrid[missileX][missileY] = 
    }
    static String[][] computerShipPlacement(String[][] grid, int SHIPS, String ship){
        int length;
        for(int n=0; n<SHIPS; n++){
            // if(n==0){
                // length = 2;
            // } else if(n==1 || n==2){
                // length = 3;
            // } else if(n==3){
                // length = 4;
            // } else {
                // length = 5;
            // }
            // int x = (int)Math.random()*10+1;
            // int y = (int)Math.random()*10+1;
            // int dir = (int)Math.round(Math.random());
            // System.out.println(dir);
            // if(grid[x][y].equals(".")){
                // if(dir==0){
                    // for(int i=0; i<length; i++){
                        // grid[x+i][y] = ship;
                    // }
                // } else if(dir==1){
                    // for(int i=0; i<length; i++){
                        // grid[x][y+i] = ship;
                    // }
                // }
            // }
            if(n==0){
                for(int i=0; i<2; i++){
                    grid[i+1][1] = ship;
                }
            } else if(n==1){
                for(int i=0; i<3; i++){
                    grid[i+1][2] = ship;
                }
            } else if(n==2){
                for(int i=0; i<3; i++){
                    grid[i+1][3] = ship;
                }
            } else if(n==3){
                for(int i=0; i<4; i++){
                    grid[i+1][4] = ship;
                }
            } else {
                for(int i=0; i<5; i++){
                    grid[i+1][5] = ship;
                }
            }
        }
        return(grid);
    }
    // static int yCoordinate(String[] ships, Scanner keyboard){
        // System.out.println("Where would you like to place your "+ships[n]+"?");
            // System.out.println("Letter A-J:");
            // String ySelect = keyboard.nextLine();
            // for(int i=0; i<letters.length; i++){
                // if(letters[i].equals(ySelect.toUpperCase())){
                    // placingShipY = i+1;
                // }
            // }
    // }
    static String[][] shipPlacement(int x, int y, String dir, int length, String ship, String[][] grid){
        if(dir.equals("a")){
            for(int i=0; i<length; i++){
                grid[x+i][y] = ship;
            }
        } else if(dir.equals("d")){
            for(int i=0; i<length; i++){
                grid[x][y+i] = ship;
            }
        }
        return(grid);
    }
    static void gridPrinter(int h, int w, String[][] pGrid, String[][] cGrid){
        System.out.println('\u000c');
        System.out.println("Your Guesses:");
        for(int y=0; y<h; y++){
            for(int x=0; x<w; x++){
               System.out.print(cGrid[x][y]+" "); 
            }
            System.out.println("");
        }
        System.out.println("Your Ships:");
        for(int y=0; y<h; y++){
            for(int x=0; x<w; x++){
                System.out.print(pGrid[x][y]+" "); 
            }
            System.out.println("");
        }
    }
}
