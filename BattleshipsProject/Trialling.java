
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
        int placingShipX = 0;
        int placingShipY = 0;
        String grid[][] = new String[WIDTH][HEIGHT];
        String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        String [] ships = {"Patrol Boat (2)", "Submarine (3)", "Destroyer (3)", "Battleship (4)", "Carrier (5)"};
        
        System.out.println("Your guesses:");
        //Sets top row of array as numbers
        grid[0][0] = " ";
        for(int x=1; x<WIDTH; x++){
                grid[x][0] = String.valueOf(x);
        }
        for(int y=1; y<HEIGHT; y++){
                grid[0][y] = letters[y-1];
        }
        //Fills the rest of the grid with blank
        for(int y=1; y<HEIGHT; y++){
            for(int x=1; x<WIDTH; x++){
                grid[x][y] = "."; 
            }
        }
        //Print grid
        for(int y=0; y<HEIGHT; y++){
            for(int x=0; x<WIDTH; x++){
                    System.out.print(grid[x][y]+" "); 
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("Your ships:");
        //Sets top row of array as numbers
        grid[0][0] = " ";
        for(int x=1; x<WIDTH; x++){
                grid[x][0] = String.valueOf(x);
        }
        for(int y=1; y<HEIGHT; y++){
                grid[0][y] = letters[y-1];
        }
        //Fills the rest of the grid with blank
        for(int y=1; y<HEIGHT; y++){
            for(int x=1; x<WIDTH; x++){
                grid[x][y] = "."; 
            }
        }
        //Print grid
        for(int y=0; y<HEIGHT; y++){
            for(int x=0; x<WIDTH; x++){
                    System.out.print(grid[x][y]+" "); 
            }
            System.out.println("");
        }
        for(int n=0; n<SHIPS; n++){
            System.out.println("Where would you like to place your "+ships[n]);
            System.out.println("Letter A-J:");
            for(int i=0; i<letters.length; i++){
                if(letters[i].equals(keyboard.nextLine())){
                    placingShipY = i;
                }
                System.out.println(placingShipY);
            }
            
        }
        
    }
}
