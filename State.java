public class State{
    private String[][] worldArray = new String[World.ROWS][World.COLS];
    private Player player;
    public State(String[][] worldArray, Player player){
        this.worldArray = worldArray;
        this.player = player;           // contains the rows and cols of the player 
    }
    public String[][] getWorldArray(){
        return this.worldArray;
    }
    public Player getPlayer(){
        return this.player;
    }
}