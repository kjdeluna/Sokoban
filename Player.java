import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/* -------------------------------------------------------------------------------------------
 *
 *  This class will be used to manage the State for each movement performed 
 *      Getters:
 *          getCurrRow() -> returns the current row of the player (int)
 *          getCurrCol() -> returns the current column of the player (int)
 *          getPreviousPosition() -> returns the previous value of the tile that the player 
 *                                       is stepping on
 *          getTexture() -> returns the texture currently used by player (Texture)
        Setters:
            setPreviousPosition() -> change the previous value of the tile the player is on
            setTexture() -> change the current image of the player
 *      Other methods:
 *          moveUp(), moveDown()      -> sets the actions that the player will take
 *          moveLeft(), moveRight()         -> will decrease xPos, yPos, currRow, currCol
 *
 * -------------------------------------------------------------------------------------------*/

public class Player{
    private Texture texture;
    private int currRow;
    private int currCol;
    private String previousPosition;
    public Player(int currRow, int currCol, Texture texture, String previousPosition){
        this.currRow = currRow;
        this.currCol = currCol;
        this.texture = texture;
        this.previousPosition = previousPosition;
    }

    public void moveLeft(){
        this.currCol -= 1;
    }

    public void moveRight(){
        this.currCol += 1;
    }

    public void moveUp(){
        this.currRow -= 1;
    }

    public void moveDown(){
        this.currRow += 1;
    }
    public int getCurrRow(){
        return this.currRow;
    }
    public int getCurrCol(){
        return this.currCol;
    }
    public String getPreviousPosition(){
        return this.previousPosition;
    }  
    public void setPreviousPosition(String prev){
        this.previousPosition = prev;
    }
    public void setTexture(Texture texture){
        this.texture = texture;
    }
    public Texture getTexture(){
        return this.texture;
    }
    public void setInitialRow(int arg){
        this.currRow = arg;
    }
    public void setInitialCol(int arg){
        this.currCol = arg;
    }
}

