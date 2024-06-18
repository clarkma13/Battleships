
/**
 * My battleships project
 *
 * Max Clarke
 * 15/5/24
 */
import java.util.Scanner;
import java.util.Arrays;

public class Project
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        final int WIDTH = 11;
        final int HEIGHT = 11;
        final int SHIPS = 5;
        final String SHIPTILE = "■";
        final String BLANKTILE = ".";
        final String HIT = "X";
        final String MISS = "O";
        int currentShipLength = 0;
        int placingX = 0;
        int placingY = 0;
        int computerMissileX = 0;
        int computerMissileY = 0;
        String playerGrid[][] = new String[WIDTH][HEIGHT];
        String computerShipsGrid [][] = new String[WIDTH][HEIGHT];
        String computerSeaGrid [][] = new String[WIDTH][HEIGHT];
        String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String [] ships = {"Patrol Boat (2)", "Submarine (3)", "Destroyer (3)", "Battleship (4)", "Carrier (5)"};
        String computerShipTile = ".";
        String shipDirection;
        boolean playerWon = false;
        boolean computerWon = false;

        computerShipsGrid = gridFiller(computerShipsGrid, BLANKTILE, letters, HEIGHT, WIDTH);
        playerGrid = gridFiller(playerGrid, BLANKTILE, letters, HEIGHT, WIDTH);
        computerSeaGrid = gridFiller(computerSeaGrid, BLANKTILE, letters, HEIGHT, WIDTH);


        gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);

        // Human placing ships

        for(int n=0; n<SHIPS; n++){
            // Sets ship length
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
            
            // Takes y coordinate
            placingY = getYInput(letters);

            // Takes x coordinate

            System.out.println("(Number 1-10)");
            placingX = 0;
            placingX = getXInput("Input Error: Invalid input", "Please choose a new x coordinate", "(Number 1-10)", placingX);
            
            

            // Takes direction
            System.out.println("Would you like the ship to go across or down?");
            System.out.println("(a/d)");
            shipDirection = keyboard.nextLine();
            while(!(shipDirection.equals("a") || shipDirection.equals("d"))){
                System.out.println("Input Error: Invalid input");
                System.out.println("(a/d)");
                shipDirection = keyboard.nextLine();
            }

            while(shipDirection.equals("a") && placingX+currentShipLength>11 ||
            shipDirection.equals("d") && placingY+currentShipLength>11){
                int c = 0;
                for(int i=0; i<currentShipLength; i++){
                    
                }
                gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);
                System.out.println("Input Error: Ship out of bounds");
                System.out.println("Please select alternative coordinates");
                System.out.println("Where would you like to place your "+ships[n]+"?");
                
                // Takes y coordinate
                placingY = getYInput(letters);

                // Takes x coordinate
                System.out.println("(Number 1-10)");
                placingX = 0;
                placingX = getXInput("Input Error: Invalid input", "Please choose a new x coordinate", "(Number 1-10)", placingX);
                

                // Takes direction
                System.out.println("Would you like the ship to go across or down?");
                System.out.println("(a/d)");
                shipDirection = keyboard.nextLine();
                while(!(shipDirection.equals("a") || shipDirection.equals("d"))){
                    System.out.println("Input Error: Invalid input");
                    System.out.println("(a/d)");
                    shipDirection = keyboard.nextLine();
                }
            }

            // Calls function to place the ship
            shipPlacement(placingX, placingY, shipDirection, currentShipLength, SHIPTILE, playerGrid);
            gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);
        }

        // Places computer ships
        computerShipPlacement(computerShipsGrid, SHIPS, computerShipTile);
        gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);

        while(playerWon==false && computerWon==false){
            // Players turn
            System.out.println("Your turn");
            System.out.println("Where would you like to fire your missile?");
            
            // Takes y coordinate
            placingY = getYInput(letters);
               
            // Takes x coordinate
            System.out.println("(Number 1-10)");
            placingX = 0;
            placingX = getXInput("Input Error: Invalid input", "Please choose a new x coordinate", "(Number 1-10)", placingX);

            // Checks that tile has not already been guessed and asks for different coordinate
            while(computerSeaGrid[placingX][placingY].equals(MISS)||computerSeaGrid[placingX][placingY].equals(HIT)){
                gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);
                System.out.println("Input Error: Space already guessed");
                System.out.println("Please select alternative coordinates");
                
                placingY = getYInput(letters);
                  
                System.out.println("(Number 1-10)");
                placingX = 0;
                placingX = getXInput("Input Error: Invalid input", "Please choose a new x coordinate", "(Number 1-10)", placingX);
            }

            // Checks for hit or miss and displays on screen
            if(computerShipsGrid[placingX][placingY].equals("A") || computerShipsGrid[placingX][placingY].equals("B") || computerShipsGrid[placingX][placingY].equals("C") || computerShipsGrid[placingX][placingY].equals("D") || computerShipsGrid[placingX][placingY].equals("E")){
                computerShipTile = computerShipsGrid[placingX][placingY];
                computerSeaGrid[placingX][placingY] = HIT;
                computerShipsGrid[placingX][placingY] = HIT;
            } else if(computerShipsGrid[placingX][placingY].equals(BLANKTILE)){
                computerSeaGrid[placingX][placingY] = MISS; 
                computerShipsGrid[placingX][placingY] = MISS; 
                computerShipTile = computerShipsGrid[placingX][placingY];
            }
            gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);

            // Calls player win checker and ends game if true
            if(playerWinChecker(computerShipsGrid, SHIPTILE, HEIGHT, WIDTH)==true){
                playerWon=true;
            }

            // Computers turn

            System.out.println("Computers turn");
            // If the last guess was a hit, give an equal chance of guessing one space to the left, right, up, or down
            if(playerGrid[computerMissileX][computerMissileY] == HIT){
                if(Math.random()>0.5){
                    if(Math.random()>0.5){
                        computerMissileX++;
                    } else {
                        computerMissileX--;
                    }
                } else {
                    if(Math.random()>0.5){
                        computerMissileY++;
                    } else {
                        computerMissileY--;
                    }
                }
            } else {
                computerMissileX = (int)(Math.random()*10+1);
                computerMissileY = (int)(Math.random()*10+1);
            }
            while(playerGrid[computerMissileX][computerMissileY].equals(MISS)||playerGrid[computerMissileX][computerMissileY].equals(HIT)){
                computerMissileX = (int)(Math.random()*10+1);
                computerMissileY = (int)(Math.random()*10+1);
            }
            if(playerGrid[computerMissileX][computerMissileY].equals(SHIPTILE)){
                playerGrid[computerMissileX][computerMissileY] = HIT;
            } else if(playerGrid[computerMissileX][computerMissileY].equals(BLANKTILE)){
                playerGrid[computerMissileX][computerMissileY] = MISS; 
            }
            if(computerWinChecker(playerGrid, SHIPTILE, HEIGHT, WIDTH)==true){
                computerWon=true;
            }

            gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);

            
            if(sunkShipChecker(computerShipsGrid, HEIGHT, WIDTH, computerShipTile) == true){
                for(int z=0; z<5; z++){
                    if(computerShipTile == letters[z]){
                        System.out.println("You sunk the "+ships[z]);
                    }
                }
            }
        }
        gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);
        if(playerWon == true){
            winMessage("Congratulations, you ");
        }
        if(computerWon == true){
            winMessage("Unlucky, the computer ");
        }
    }

    static int getXInput(String msg1, String msg2, String msg3, int placingX){
        Scanner keyboard = new Scanner(System.in);
        while(placingX<1 || placingX>10){
            while(!keyboard.hasNextInt()){
                keyboard.nextLine();
                System.out.println(msg1);
                System.out.println(msg2);
                System.out.println(msg3);
            }
            placingX = keyboard.nextInt();
            keyboard.nextLine();
            if(placingX<1 || placingX>10){
                System.out.println(msg1);
                System.out.println(msg2);
                System.out.println(msg3);
            }
        }
        return(placingX);
    }
    
    static int getYInput(String[] letters){
        Scanner keyboard = new Scanner(System.in);
        int y = 0;
        boolean found = false;
        
        System.out.println("(Letter A-J)");
        String ySelect = keyboard.nextLine();
        while(!Arrays.asList(letters).contains(ySelect.toUpperCase())){
            System.out.println("Input Error: Invalid input");
            System.out.println("Please choose a new y coordinate");
            System.out.println("(Letter A-J)");
            ySelect = keyboard.nextLine();
        }    
        for(int i=0; i<letters.length; i++){
            if(letters[i].equals(ySelect.toUpperCase())){
                y = i+1;
            }
        }   
        return(y);
    }

    static void winMessage(String msg){
        System.out.println(msg+"won");
    }

    static boolean playerWinChecker(String[][] grid, String ship, int h, int w){
        int n = 0;
        for(int y=1; y<h; y++){
            for(int x=1; x<w; x++){
                if(grid[x][y].equals("A") || grid[x][y].equals("B") || grid[x][y].equals("C") || grid[x][y].equals("D") || grid[x][y].equals("E")){
                    n++;
                }
            }
        }
        if(n == 0){
            return(true);
        }else{
            return(false);
        }
    }

    static boolean computerWinChecker(String[][] grid, String ship, int h, int w){
        int n = 0;
        for(int y=1; y<h; y++){
            for(int x=1; x<w; x++){
                if(grid[x][y].equals(ship)){
                    n++;
                }
            }
        }
        if(n == 0){
            return(true);
        }else{
            return(false);
        }
    }

    static boolean sunkShipChecker(String[][] grid, int h, int w, String ship){
        int n = 0;
        for(int y=1; y<h; y++){
            for(int x=1; x<w; x++){
                if(grid[x][y].equals(ship)){
                    n++;
                }
            }
        }
        if(n == 0){
            return(true);
        }else{
            return(false);
        }
    }

    static String[][] computerShipPlacement(String[][] grid, int SHIPS, String ship){
        // int length;
        for(int n=0; n<SHIPS; n++){
            // boolean goodShip = false;
            // if(n==0){
            // length = 2;
            // } else if(n==1 || n==2){
            // length = 3;
            // } else if(n==3){
            // length = 4;
            // } else {
            // length = 5;
            // }
            // int dir = (int)Math.floor(Math.random()*2);
            // int x;
            // int y;

            // if(dir == 0){
            // x = (int)Math.round(Math.random()*(10-length));
            // y = (int)Math.round(Math.random()*10);

            // for(int i=0; i<length; i++){
            // if(grid[x+i][y].equals(ship)){
            // goodShip = false;
            // }else{
            // goodShip = true;
            // }
            // }

                
            // if(goodShip==true){
            // for(int i=0; i<length; i++){
            // grid[x+i][y+1] = ship;
            // }
            // }
            // }else if(dir == 1){
            // x = (int)Math.round(Math.random()*10);
            // y = (int)Math.round(Math.random()*(10-length));

            // for(int i=0; i<length; i++){
            // if(grid[x][y+1].equals(ship)){
            // goodShip = false;
            // }
            // }

                
            // if(goodShip==true){
            // for(int i=0; i<length; i++){
            // grid[x+1][y+i+1] = ship;
            // }
            // }
            // }

            if(n==0){
                ship = "A";
                for(int i=0; i<2; i++){
                    grid[i+1][1] = ship;
                }
            } else if(n==1){
                ship = "B";
                for(int i=0; i<3; i++){
                    grid[i+1][2] = ship;
                }
            } else if(n==2){
                ship = "C";
                for(int i=0; i<3; i++){
                    grid[i+1][3] = ship;
                }
            } else if(n==3){
                ship = "D";
                for(int i=0; i<4; i++){
                    grid[i+1][4] = ship;
                }
            } else {
                ship = "E";
                for(int i=0; i<5; i++){
                    grid[i+1][5] = ship;
                }
            }
        }
        return(grid);
    }

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

    static String[][] gridFiller(String[][] grid, String blank, String[] letters, int h, int w){
        //Sets top row of array as numbers
        grid[0][0] = " ";
        for(int x=1; x<w; x++){
            grid[x][0] = String.valueOf(x);
        }
        for(int y=1; y<h; y++){
            grid[0][y] = letters[y-1];
        }
        //Fills the rest of the grid with blank
        for(int y=1; y<h; y++){
            for(int x=1; x<w; x++){
                grid[x][y] = blank; 
            }
        }
        return(grid);
    }
}