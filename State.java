import java.util.LinkedList;


/* -------------------------------------------------------------------------------------------
 *
 *  This class will be used to manage the State for each movement performed 
 *      Getters:
 *          getWorldArray() -> returns the 2D array (String[][])
 *          getInitEndPointCount() -> returns the initial number of end points (int)
 *          getPath() -> returns the current path taken by the player (LinkedList<Directions>)
 *          getPlayer() -> returns the player of that current state (Player)
 *      Other methods:
 *          printPaths() -> print the path taken by the player (void)
 *          addPath() -> add a direction to the current path taken by player (void)
 *
 * -------------------------------------------------------------------------------------------*/

public class State{
    private String[][] worldArray = new String[World.ROWS][World.COLS];
    private Player player;
    private int initEndPointCount;
    private LinkedList<Directions> path;

    public State(String[][] worldArray, Player player, int initEndPointCount, LinkedList<Directions> path){
        this.worldArray = worldArray;
        this.player = player;           // contains the rows and cols of the player 
        this.initEndPointCount = initEndPointCount;
        this.path = path;
    }

    public String[][] getWorldArray(){
        return this.worldArray;
    }

    public int getInitEndPointCount(){
        return this.initEndPointCount;
    }
    
    public LinkedList<Directions> getPath(){
        return this.path;
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    public void printPaths(){
        System.out.println("Printing result:");
        for(Directions direction : path){
            System.out.println(direction);
        }
    }

    public void addPath(Directions direction){
        this.path.add(direction);
    }
}