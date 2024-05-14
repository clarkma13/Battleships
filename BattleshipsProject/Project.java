
/**
 * My battleships project
 *
 * Max Clarke
 * 15/5/24
 */
public class Project
{
    public static void main(String[] args){
        final int WIDTH = 11;
        final int HEIGHT = 11;
        String grid[][] = new String[WIDTH][HEIGHT];
        String [] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        
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
    }
}
