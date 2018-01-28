public class State{
    private String[][] worldArray = new String[World.ROWS][World.COLS];
    private Player player;
    private int initEndPointCount;
    public State(String[][] worldArray, Player player, int initEndPointCount){
        this.worldArray = worldArray;
        this.player = player;           // contains the rows and cols of the player 
        this.initEndPointCount = initEndPointCount;
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
}