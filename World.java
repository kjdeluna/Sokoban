import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class World extends JPanel implements KeyListener{
    public final static int UNINITIALIZED_INT = -1;

    // Details of the map
    private final static String INPUT_FILENAME = "puzzle2.in";
    public final static int ROWS = 10;
    public final static int COLS = 10;

    // Will be used in loading textures

    // Values of the 2D Array
    private String[][] worldArray = new String[ROWS][COLS];
    public final static String BOX = "b";              // BOX
    public final static String BOX_IN_STORAGE = "B";   // BOX IN STORAGE
    public final static String WALL = "w";             // wall
    public final static String OUTSIDE = "x";          // none
    public final static String EMPTY = "e";            // empty space
    public final static String ENDPOINT = "s";         // storage
    public final static String WAREHOUSE_KEEPER = "k"; // warehouse keeper
    public final static String WAREHOUSE_KEEPER_ON_ENDPOINT = "K"; // warehouse keeper 
                                                                    // in storage
    private boolean win;

    public static final int TILE_SIZE = 64;

    // Will be passed to the player
    private int initialRow = World.UNINITIALIZED_INT;
    private int initialCol = World.UNINITIALIZED_INT;

    private Player player;

    private int initialEndPointCount;

    public World(){
        this.setBackground(Color.BLACK);
        this.readFile();
        this.player = new Player(this.initialRow, this.initialCol, Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "player_movedown.png"),
            (this.worldArray[this.initialRow][this.initialCol]).equals("K") ? "s" : "e"); // If it is standing in a storage
                                                                                        // previousPosition will be storage
        this.add(player);
        this.setBounds(0,0, Main.FRAME_WIDTH, 640);
        this.win = false;
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(null);
    }

    public String up(String[][] worldArray, Player player){
        return worldArray[player.getCurrRow() - 1][player.getCurrCol()];
    }

    public String down(String[][] worldArray, Player player){
        return worldArray[player.getCurrRow() + 1][player.getCurrCol()];
    }

    public String left(String[][] worldArray, Player player){
        return worldArray[player.getCurrRow()][player.getCurrCol() - 1];
    }

    public String right(String[][] worldArray, Player player){
        return worldArray[player.getCurrRow()][player.getCurrCol() + 1];
    }

    public void keyPressed(KeyEvent ke){
        if(!this.win){
            int keyCode = ke.getKeyCode();
            switch(keyCode){
                case KeyEvent.VK_UP:
                    this.player.setTexture(
                        Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "player_moveup.png")
                    );
                    movementDetected(Directions.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    this.player.setTexture(
                        Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "player_movedown.png")
                    );
                    movementDetected(Directions.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    this.player.setTexture(
                        Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "player_moveleft.png")
                    );
                    movementDetected(Directions.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    this.player.setTexture(
                        Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "player_moveright.png")
                    );
                    movementDetected(Directions.RIGHT);
                    break;
            }
            this.repaint();
            if(checkWin()){
                JOptionPane.showMessageDialog(null, "You win");
                this.win = true;
            }
        } 
    }

    public void keyTyped(KeyEvent ke){}

    public void keyReleased(KeyEvent ke){}

    public void movementDetected(Directions direction){
        String object = "x";
        // Will check if the next position of the player will be out of range
        boolean outOfBounds = false;
        int currentRow = this.player.getCurrRow();
        int currentColumn = this.player.getCurrCol();
        switch(direction){
            case UP:
                outOfBounds = currentRow - 1 >= 0 ? false : true;
                if(!outOfBounds) object = up(this.worldArray, this.player);
                break;
            case DOWN:
                outOfBounds = currentRow + 1 < World.ROWS ? false : true;
                if(!outOfBounds) object = down(this.worldArray, this.player);
                break;
            case LEFT:
                outOfBounds = currentColumn - 1 >= 0 ? false : true;
                if(!outOfBounds) object = left(this.worldArray, this.player);
                break;
            case RIGHT:
                outOfBounds = currentColumn + 1 < World.COLS ? false: true;
                if(!outOfBounds) object = right(this.worldArray, this.player);
                break;
            default: System.out.println("Invalid direction has been passed to movementDetected method.");
        }
        if(!outOfBounds){
            if((!(object.equals(World.OUTSIDE))) && (!(object.equals(World.WALL)))){
                if(object.equals(World.EMPTY) || object.equals(World.ENDPOINT)){
                    this.setChangesInArray(direction);
                    switch(direction){
                        case UP:
                            this.player.moveUp();
                            break;
                        case DOWN:
                            this.player.moveDown();
                            break;
                        case LEFT:
                            this.player.moveLeft();
                            break;
                        case RIGHT:
                            this.player.moveRight();
                            break;
                    }
                }
                else if(object.equals(World.BOX) || object.equals(World.BOX_IN_STORAGE)){
                    // Will check - if the next position of the box will be out of range
                    boolean boxOutOfBounds = false;
                    String objectTwo = "x";
                    int nextBoxRow = World.UNINITIALIZED_INT;
                    int nextBoxColumn = World.UNINITIALIZED_INT;
                    int currentBoxRow = World.UNINITIALIZED_INT;
                    int currentBoxColumn = World.UNINITIALIZED_INT;
                    switch(direction){
                        case UP:
                            currentBoxRow = currentRow - 1;
                            currentBoxColumn = currentColumn;
                            nextBoxRow = currentBoxRow - 1;
                            nextBoxColumn = currentBoxColumn;
                            boxOutOfBounds = nextBoxRow >= 0 ? false : true;
                            break;
                        case DOWN:
                            currentBoxRow = currentRow + 1;
                            currentBoxColumn = currentColumn;
                            nextBoxRow = currentBoxRow + 1;
                            nextBoxColumn = currentBoxColumn;
                            boxOutOfBounds = nextBoxRow < World.ROWS ? false : true;
                            break;
                        case LEFT: 
                            currentBoxRow = currentRow;
                            currentBoxColumn = currentColumn - 1;
                            nextBoxRow = currentBoxRow;
                            nextBoxColumn = currentBoxColumn - 1;
                            boxOutOfBounds = nextBoxColumn >= 0 ? false : true;
                            break;
                        case RIGHT:
                            currentBoxRow = currentRow;
                            currentBoxColumn = currentColumn + 1;
                            nextBoxRow = currentBoxRow;
                            nextBoxColumn = currentBoxColumn + 1;
                            boxOutOfBounds = nextBoxColumn < World.COLS ? false : true;
                            break;
                    }
                    if(!boxOutOfBounds){ // If box's next position is still within the indices:
                        objectTwo = this.worldArray[nextBoxRow][nextBoxColumn];
                        if((!(objectTwo.equals(World.OUTSIDE))) && (!(objectTwo.equals(World.WALL))) && 
                            (!(objectTwo.equals(World.BOX))) && (!(objectTwo.equals(World.BOX_IN_STORAGE)))){
                        // Update the new position of the boxes
                            if(this.worldArray[nextBoxRow][nextBoxColumn].equals(World.ENDPOINT)){
                                this.worldArray[nextBoxRow][nextBoxColumn] = World.BOX_IN_STORAGE;
                            }
                            else this.worldArray[nextBoxRow][nextBoxColumn] = World.BOX;
                            // Update the previous position of the boxes
                            if(this.worldArray[currentBoxRow][currentBoxColumn].equals(World.BOX_IN_STORAGE)){
                                this.worldArray[currentBoxRow][currentBoxColumn] = World.ENDPOINT;
                            }
                            else this.worldArray[currentBoxRow][currentBoxColumn] = World.EMPTY;
                            // Will allow the player to move and update the previous position of the tile
                            this.setChangesInArray(direction);
                            switch(direction){
                                case UP:
                                    // Will decrease the xPos, yPos, row, and column attributes of the player
                                    this.player.moveUp();
                                    break;
                                case DOWN:
                                    this.player.moveDown();
                                    break;
                                case LEFT:
                                    this.player.moveLeft();
                                    break;
                                case RIGHT:
                                    this.player.moveRight();
                                    break;
                            }
                        }
                    }
                }
            }
        }
        // printArrayTable(); /* UNCOMMENT ME TO SEE CONTENTS OF TABLE */
    }

    private boolean checkWin(){
        int boxInStorageCounter = 0;
        for(int i = 0; i < World.ROWS; i++){
            for(int j = 0; j < World.COLS; j++){
                if(this.worldArray[i][j].equals(World.BOX_IN_STORAGE)){
                    boxInStorageCounter++;
                }
            }
        }
        if (this.initialEndPointCount == boxInStorageCounter) return true;
        else return false;
    }
    public void setChangesInArray(Directions direction){
        int currentRow = this.player.getCurrRow(); 
        int currentColumn = this.player.getCurrCol();
        int nextRow = World.UNINITIALIZED_INT, nextColumn = World.UNINITIALIZED_INT;
        String prevState = this.player.getPreviousPosition();
        switch(direction){
            case UP:
                nextRow = currentRow - 1;
                nextColumn = currentColumn;
                break;
            case DOWN:
                nextRow = currentRow + 1;
                nextColumn = currentColumn;
                break;
            case LEFT:
                nextRow = currentRow;
                nextColumn = currentColumn - 1;
                break;
            case RIGHT:
                nextRow = currentRow;
                nextColumn = currentColumn + 1;
                break;
        }
        this.worldArray[currentRow][currentColumn] = prevState;
        this.player.setPreviousPosition(this.worldArray[nextRow][nextColumn]);
        if(this.worldArray[nextRow][nextColumn].equals(World.ENDPOINT)){
            this.worldArray[nextRow][nextColumn] = World.WAREHOUSE_KEEPER_ON_ENDPOINT;
        }
        else this.worldArray[nextRow][nextColumn] = World.WAREHOUSE_KEEPER;
    }

    private void readFile(){
        int initialEndPointCounter = 0;
        try{
            int i = 0;
            String[] lineRead;
            String line;
            FileReader fr = new FileReader(World.INPUT_FILENAME);
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine()) != null){
                lineRead = line.split(" ");
                for(int j = 0; j < COLS; j++){
                    if(initialRow == World.UNINITIALIZED_INT && initialCol == World.UNINITIALIZED_INT){
                        if(lineRead[j].equals(World.WAREHOUSE_KEEPER) || lineRead[j].equals(World.WAREHOUSE_KEEPER_ON_ENDPOINT)){
                            this.initialRow = i;
                            this.initialCol = j;
                        }
                    }
                    this.worldArray[i][j] = lineRead[j];
                    if(lineRead[j].equals(World.ENDPOINT) 
                        || lineRead[j].equals(World.WAREHOUSE_KEEPER_ON_ENDPOINT) 
                        || lineRead[j].equals(World.BOX_IN_STORAGE)){
                        initialEndPointCounter++;
                    }
                }
                i += 1;
            }
        System.out.println("Successfully read " + World.INPUT_FILENAME);
        } catch (Exception e){
            e. printStackTrace();
        }
        this.initialEndPointCount = initialEndPointCounter;
	}

    public void readFile(String filename){
        this.win = false;
        int initialEndPointCounter = 0;
        try{
            int i = 0;
            String[] lineRead;
            String line;
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine()) != null){
                lineRead = line.split(" ");
                for(int j = 0; j < COLS; j++){
                    if(lineRead[j].equals(World.WAREHOUSE_KEEPER) || lineRead[j].equals(World.WAREHOUSE_KEEPER_ON_ENDPOINT)){
                        this.initialRow = i;
                        this.initialCol = j;
                        this.player.setPreviousPosition((this.worldArray[this.initialRow][this.initialCol]).equals("K") ? "s" : "e");
                        this.player.setInitialCoordinates(this.initialRow, this.initialCol);
                    }
                    this.worldArray[i][j] = lineRead[j];
                    if(lineRead[j].equals(World.ENDPOINT) 
                        || lineRead[j].equals(World.WAREHOUSE_KEEPER_ON_ENDPOINT) 
                        || lineRead[j].equals(World.BOX_IN_STORAGE)){
                        initialEndPointCounter++;
                    }
                }
                i += 1;
            }
            this.repaint();
        } catch (Exception e){
            e. printStackTrace();
        }
        this.initialEndPointCount = initialEndPointCounter;
	}

    public void printArrayTable(){
        for(int i = 0; i < World.ROWS; i++){
            System.out.print("\n");
            for(int j = 0; j < World.COLS; j++){
                System.out.print(this.worldArray[i][j] + " ");
            }
        }
        System.out.println("\n");
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,0,Main.FRAME_WIDTH, 640);
        Texture temp;
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                if(this.worldArray[i][j].equals(World.BOX)){
                    Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "box.png")
                        .render(g2d, j * 64, i * 64);
                } 
                else if(this.worldArray[i][j].equals(World.BOX_IN_STORAGE)){
                    Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "box_in_storage.png")
                        .render(g2d, j * 64, i * 64);
                }
                else if(this.worldArray[i][j].equals(World.WALL)){
                    Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "wall.png")
                        .render(g2d, j * 64, i * 64);
                }
                else if(this.worldArray[i][j].equals(World.OUTSIDE)){
                    Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "outside.png")
                        .render(g2d, j * 64, i * 64);
                }
                else if(this.worldArray[i][j].equals(World.ENDPOINT)){
                    Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "endpoint.png")
                        .render(g2d, j * 64, i * 64);
                }
                else if(this.worldArray[i][j].equals(World.EMPTY)){
                    Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "empty.png")
                        .render(g2d, j * 64, i * 64);
                }
                else{
                    Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "empty.png")
                        .render(g2d, j * 64, i * 64);
                    Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "selector.png")
                        .render(g2d, j * 64, i * 64);
                }

            }
        }
    }

    public String[][] getWorldArray(){
        return this.worldArray;
    }

    public Player getPlayer(){
        return this.player;
    }

    public int getInitialEndPointCount(){
        return this.initialEndPointCount;
    }

    public void passDirectionToWorld(Directions dir){
        if(!this.win){
            switch(dir){
                case UP:
                    this.player.setTexture(
                        Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "player_moveup.png")
                    );
                    movementDetected(Directions.UP);
                    break;
                case DOWN:
                    this.player.setTexture(
                        Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "player_movedown.png")
                    );
                    movementDetected(Directions.DOWN);
                    break;
                case LEFT:
                    this.player.setTexture(
                        Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "player_moveleft.png")
                    );
                    movementDetected(Directions.LEFT);
                    break;
                case RIGHT:
                    this.player.setTexture(
                        Main.textureLoader.getTexture(Texture.DEFAULT_PATH, "player_moveright.png")
                    );
                    movementDetected(Directions.RIGHT);
                    break;
            }
            this.repaint();
            if(checkWin()){
                JOptionPane.showMessageDialog(null, "You win");
                this.win = true;
            }
        } 
    }
}
