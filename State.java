import java.util.LinkedList;

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
    public Player getPlayer(){
        return this.player;
    }
    
    public int getInitEndPointCount(){
        return this.initEndPointCount;
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

    public LinkedList<Directions> getPath(){
        return this.path;
    }
}