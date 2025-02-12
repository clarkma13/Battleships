
/**
 * Program used to trial different components of my battleships project
 *
 * Max Clarke
 * 14/5/24
 */
import java.util.Scanner;
import java.util.Arrays;

public class Trialling
{
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        final int WIDTH = 11;
        final int HEIGHT = 11;
        final int SHIPS = 2;
        final String SHIPTILE = "■";
        final String BLANKTILE = ".";
        final String HIT = "X";
        final String MISS = "o";
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
        String shipDirection = "";
        boolean playerWon = false;
        boolean computerWon = false;
        boolean playAgain = true;

        while(playAgain == true){
        
            computerShipsGrid = gridFiller(computerShipsGrid, BLANKTILE, letters, HEIGHT, WIDTH);
            playerGrid = gridFiller(playerGrid, BLANKTILE, letters, HEIGHT, WIDTH);
            computerSeaGrid = gridFiller(computerSeaGrid, BLANKTILE, letters, HEIGHT, WIDTH);
            computerWon = false;
            playerWon = false;
            
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

                boolean goodShip = false;

                while(goodShip == false){
                    int count = 0;
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
                    shipDirection = keyboard.nextLine().toLowerCase();
                    // Error checking for direction
                    while(!(shipDirection.equals("a") || shipDirection.equals("d"))){
                        System.out.println("Input Error: Invalid input");
                        System.out.println("(a/d)");
                        shipDirection = keyboard.nextLine();
                    }

                    // While  loop makes sure ship cannot be placed off the grid, and  asks for new coordinates
                    while(shipDirection.equals("a") && placingX+currentShipLength>11 ||
                    shipDirection.equals("d") && placingY+currentShipLength>11){
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
                        shipDirection = keyboard.nextLine().toLowerCase();
                        // Error checking for direction
                        while(!(shipDirection.equals("a") || shipDirection.equals("d"))){
                            System.out.println("Input Error: Invalid input");
                            System.out.println("(a/d)");
                            shipDirection = keyboard.nextLine();
                        }
                    }

                    // Checks if the ship being placed would overlap with existing ships
                    for(int i=0; i<currentShipLength; i++){
                        if(shipDirection.equals("a")){
                            if(playerGrid[placingX+i][placingY].equals(SHIPTILE)){
                                count++;
                            }
                        }else if(shipDirection.equals("d")){
                            if(playerGrid[placingX][placingY+i].equals(SHIPTILE)){
                                count++;
                            }
                        }
                    }

                    // If the ship overlaps, go back through the loop and ask for new coordinates. Otherwise place the ship
                    if(count == 0){
                        goodShip = true;
                    } else if(count > 0){
                        goodShip = false;
                        gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);
                        System.out.println("Input Error: Ship overlap");
                        System.out.println("Please choose new coordinates");
                    }

                }

                // Calls function to place the ship and refreshes grid
                shipPlacement(placingX, placingY, shipDirection, currentShipLength, SHIPTILE, playerGrid);
                gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);
            }

            // Places computer ships and refreshes grid
            computerShipPlacement(computerShipsGrid, SHIPS, computerShipTile, letters);
            gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);

            // Repeats this loop until a win is achieved
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

                // Checks for hit or miss and displays on screen and refreshes grid
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

                } // If last shot wasn't a hit, choose random coordinates
                else {
                    computerMissileX = (int)(Math.random()*10+1);
                    computerMissileY = (int)(Math.random()*10+1);
                }
                // Checks if the chosen coordinates have already been guessed, and if true randomises coordinates until valid
                while(playerGrid[computerMissileX][computerMissileY].equals(MISS)||playerGrid[computerMissileX][computerMissileY].equals(HIT)){
                    computerMissileX = (int)(Math.random()*10+1);
                    computerMissileY = (int)(Math.random()*10+1);
                }
                // Checks whether guess was hit or miss
                if(playerGrid[computerMissileX][computerMissileY].equals(SHIPTILE)){
                    playerGrid[computerMissileX][computerMissileY] = HIT;
                } else if(playerGrid[computerMissileX][computerMissileY].equals(BLANKTILE)){
                    playerGrid[computerMissileX][computerMissileY] = MISS; 
                }
                // Calls win checker function
                if(computerWinChecker(playerGrid, SHIPTILE, HEIGHT, WIDTH)==true){
                    computerWon=true;
                }

                // Refreshes grid
                gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);

                // Calls sunk ship checker function, and if true, tells the player which ship they sunk
                if(sunkShipChecker(computerShipsGrid, HEIGHT, WIDTH, computerShipTile) == true){
                    for(int z=0; z<5; z++){
                        if(computerShipTile == letters[z]){
                            System.out.println("You sunk the "+ships[z]);
                        }
                    }
                }
            }

            // Refreshes the grid and then calls the
            gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);
            if(playerWon == true){
                winMessage("Congratulations, you ");
            }
            if(computerWon == true){
                for(int y=1; y<HEIGHT; y++){
                    for(int x=1; x<WIDTH; x++){
                        if(Arrays.asList(letters).contains(computerShipsGrid[x][y])){
                            computerSeaGrid[x][y] = SHIPTILE;
                        }
                    }
                }
                gridPrinter(HEIGHT, WIDTH, playerGrid, computerSeaGrid);
                winMessage("Unlucky, the computer ");
            }
            
            playAgain = replayChecker();
            
        }
    }

    /**
     * Takes player input for an x coordinate and checks that it is valid
     * Has to be an integer and between 1 and 10
     * If it fails an error message is printed and a new coordinate is requested
     * Once it has recieved valid input it returns the input
     */
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

    /**
     * Takes player input for an y coordinate and checks that it is valid
     * Has to be included in the array of letters (A-J)
     * If it fails an error message is printed and a new coordinate is requested
     * Once it has recieved valid input it converts it to a number and returns the number
     */
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

    /**
     * Prints out a win message depending on whether player or computer won
     */
    static void winMessage(String msg){
        System.out.println(msg+"won");
    }
    
    static boolean replayChecker(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Would you like to play again?");
        System.out.println("(y/n)");
        String replayInput = keyboard.nextLine().toLowerCase();
        while(!(replayInput.equals("y") || replayInput.equals("n"))){
            System.out.println("Input Error: Invalid input");
            System.out.println("(y/n)");
            replayInput = keyboard.nextLine();
        }
        if(replayInput.equals("y")){
            return(true);
        }else{
            return(false);
        }
    }

    /**
     * Checks if the player has won
     * For each tile on the grid, if it contains a ship, add 1 to n
     * If n equals 0 then there are no ships left and therefore the player has won
     * Otherwise there are still ships left and the player hasn't won
     */
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

    /**
     * Checks if the computer has won
     * For each tile on the grid, if it contains a ship, add 1 to n
     * If n equals 0 then there are no ships left and therefore the computer has won
     * Otherwise there are still ships left and the computer hasn't won
     */
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

    /**
     * Takes the tile that has just been hit
     * For each tile on the grid, if it contains that ship, add 1 to n
     * If n equals 0 then there is none of that ship left and therefore the ship has been sunk
     * Otherwise there is still some of that ship left and it hasnt been sunk
     */
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

    /**
     * This function places the computer ships
     * Sets the length of the ship
     * Chooses a random direction, and then generates random x and y coordinates that will be within the grids bounds
     * Then checks for overlap with existing ships. If there is some overlap it will randomise x and y until there is none
     * Places a ship and then repeats until all ships are placed and then returns the grid with ships in it
     */
    static String[][] computerShipPlacement(String[][] grid, int SHIPS, String ship, String[] letters){
        int length;
        for(int n=0; n<SHIPS; n++){

            int count = 0;
            if(n==0){
                length = 2;            
            } else if(n==1){
                length = 3;             
            } else if(n==2){
                length = 3;            
            } else if(n==3){
                length = 4;               
            } else {
                length = 5;                
            }
            ship = letters[n];
            int dir = (int)Math.floor(Math.random()*2);
            int x = 0;
            int y = 0;

            if(dir == 0){
                x = (int)Math.floor(Math.random()*(10-length)+1);
                y = (int)Math.floor(Math.random()*10+1);

                for(int i=0; i<length; i++){
                    if(Arrays.asList(letters).contains(grid[x+i][y])){
                        count++;
                    }
                }

                while(count != 0){
                    count = 0;
                    x = (int)Math.floor(Math.random()*(10-length)+1);
                    y = (int)Math.floor(Math.random()*10+1);

                    for(int i=0; i<length; i++){
                        if(Arrays.asList(letters).contains(grid[x+i][y])){
                            count++;
                        }
                    }
                }

                
                for(int i=0; i<length; i++){
                    grid[x+i][y] = ship;
                }

            }else if(dir == 1){
                x = (int)Math.floor(Math.random()*10+1);
                y = (int)Math.floor(Math.random()*(10-length)+1);

                for(int i=0; i<length; i++){
                    if(Arrays.asList(letters).contains(grid[x][y+i])){
                        count++;
                    }
                }

                while(count != 0){
                    count = 0;
                    x = (int)Math.floor(Math.random()*10+1);
                    y = (int)Math.floor(Math.random()*(10-length)+1);

                    for(int i=0; i<length; i++){
                        if(Arrays.asList(letters).contains(grid[x][y+i])){
                            count++;
                        }
                    }
                }

                for(int i=0; i<length; i++){
                    grid[x][y+i] = ship;
                }

            }

        }
        return(grid);
    }

    /**
     * Takes the player input for x, y, and direction
     * Places a ship on the player grid using those values
     * Returns new grid
     */
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

    /**
     * Clears the screen, then prints out the computer and player grids, with labels for the player above each
     */
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

    /*
     * Takes a 2d array and fills it with values to make a 'blank' starting grid with labels on x and y axes 
     */
    static String[][] gridFiller(String[][] grid, String blank, String[] letters, int h, int w){

        grid[0][0] = " ";
        //Sets top row of array as numbers
        for(int x=1; x<w; x++){
            grid[x][0] = String.valueOf(x);
        }
        //Sets left column of array as letters
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
