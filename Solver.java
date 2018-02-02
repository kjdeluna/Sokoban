import java.util.Queue;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
public class Solver {
    private Queue<State> frontier;
    private State parentState;
    private LinkedList<Directions> path;
    public Solver(State initialState){
        // Initial state 
        // When solver is instantiated, it gets the initial state -> parentState
        //                  of the game.
        this.parentState = initialState;
        this.frontier = new ArrayDeque<State>();
    }

    public State breadthFirstSearch(){
        State currentState;
        State nextState;
        int count = 0;
        Set<String> explored = new HashSet<String>();
        this.frontier.offer(this.parentState);
        while(this.frontier.peek() != null){
            currentState = this.frontier.poll();
            if(GoalTest(currentState)) return currentState;
            else{
                LinkedList<Directions> actionList = Actions(currentState);
                for(Directions direction : actionList){
                    count++;
                    nextState = Result(currentState, direction);
                    if(nextState != null){
                        String hash = "";
                        for(int i = 0; i < World.ROWS; i++){
                            for(int j = 0; j < World.COLS; j++){
                                hash += nextState.getWorldArray()[i][j];
                            }
                        }
                        // System.out.println(direction);
                        if(!explored.contains(hash)){
                            this.frontier.offer(nextState);
                            explored.add(hash);
                        }
                        // String[][] test = currentState.getWorldArray();
                        // for(int i = 0; i < World.ROWS; i++){
                        //     System.out.print("\n");
                        //     for(int j = 0; j < World.COLS; j++){
                        //         System.out.print(test[i][j] + " ");
                        //     }
                        // }
                        // System.out.print("\n");
                        System.out.println("Num of iterations: " + count);
                    }
                }
            }
        }
        return null;
    }

    private State Result(State currentState, Directions direction){
        Player clonedPlayer = Clone.clonePlayer(currentState.getPlayer());
        String[][] clonedArray2D = Clone.cloneArray2D(currentState.getWorldArray());
        // State nextState = new State(clonedArray2D, clonedPlayer, currentState.getInitEndPointCount());
        int currentRow = clonedPlayer.getCurrRow();
        int currentColumn = clonedPlayer.getCurrCol();
        String object = "x";
        // Will check if the next position of the player will be out of range
        boolean outOfBounds = false;
        switch(direction){
            case UP:
                object = up(clonedArray2D, clonedPlayer);
                break;
            case DOWN:
                object = down(clonedArray2D, clonedPlayer);
                break;
            case LEFT:
                object = left(clonedArray2D, clonedPlayer);
                break;
            case RIGHT:
                object = right(clonedArray2D, clonedPlayer);
                break;
            default: System.out.println("Invalid direction has been passed to movementDetected method.");
        }
        if((!(object.equals(World.OUTSIDE))) && (!(object.equals(World.WALL)))){
            if(object.equals(World.EMPTY) || object.equals(World.ENDPOINT)){
                this.setChangesInArray(clonedArray2D, clonedPlayer, direction);
                switch(direction){
                    case UP:
                        clonedPlayer.moveUp();
                        break;
                    case DOWN:
                        clonedPlayer.moveDown();
                        break;
                    case LEFT:
                        clonedPlayer.moveLeft();
                        break;
                    case RIGHT:
                        clonedPlayer.moveRight();
                        break;
                }
            }
            else if(object.equals(World.BOX) || object.equals(World.BOX_IN_STORAGE)){
                    // Will check - if the next position of the box will be out of range
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
                        break;
                    case DOWN:
                        currentBoxRow = currentRow + 1;
                        currentBoxColumn = currentColumn;
                        nextBoxRow = currentBoxRow + 1;
                        nextBoxColumn = currentBoxColumn;
                        break;
                    case LEFT: 
                        currentBoxRow = currentRow;
                        currentBoxColumn = currentColumn - 1;
                        nextBoxRow = currentBoxRow;
                        nextBoxColumn = currentBoxColumn - 1;
                        break;
                    case RIGHT:
                        currentBoxRow = currentRow;
                        currentBoxColumn = currentColumn + 1;
                        nextBoxRow = currentBoxRow;
                        nextBoxColumn = currentBoxColumn + 1;
                        break;
                }
                objectTwo = clonedArray2D[nextBoxRow][nextBoxColumn];
                if((!(objectTwo.equals(World.OUTSIDE))) && (!(objectTwo.equals(World.WALL))) && 
                    (!(objectTwo.equals(World.BOX))) && (!(objectTwo.equals(World.BOX_IN_STORAGE)))){
                // Update the new position of the boxes
                    if(clonedArray2D[nextBoxRow][nextBoxColumn].equals(World.ENDPOINT)){
                        clonedArray2D[nextBoxRow][nextBoxColumn] = World.BOX_IN_STORAGE;
                    }
                    else{
                        clonedArray2D[nextBoxRow][nextBoxColumn] = World.BOX;
                        // top left
                        if(clonedArray2D[nextBoxRow-1][nextBoxColumn].equals(World.WALL) && clonedArray2D[nextBoxRow][nextBoxColumn-1].equals(World.WALL)){
                            return null;
                        }
                        // top right
                        else if(clonedArray2D[nextBoxRow-1][nextBoxColumn].equals(World.WALL) && clonedArray2D[nextBoxRow][nextBoxColumn+1].equals(World.WALL)){
                            return null;
                        }
                        // bottom left
                        else if(clonedArray2D[nextBoxRow+1][nextBoxColumn].equals(World.WALL) && clonedArray2D[nextBoxRow][nextBoxColumn-1].equals(World.WALL)){
                            return null;
                        }
                        // bottom right
                        else if(clonedArray2D[nextBoxRow+1][nextBoxColumn].equals(World.WALL) && clonedArray2D[nextBoxRow][nextBoxColumn+1].equals(World.WALL)){
                            return null;
                        }
                    }
                    // Update the previous position of the boxes
                    if(clonedArray2D[currentBoxRow][currentBoxColumn].equals(World.BOX_IN_STORAGE)){
                        clonedArray2D[currentBoxRow][currentBoxColumn] = World.ENDPOINT;
                    }
                    else clonedArray2D[currentBoxRow][currentBoxColumn] = World.EMPTY;
                    // Will allow the player to move and update the previous position of the tile
                    this.setChangesInArray(clonedArray2D, clonedPlayer, direction);
                    switch(direction){
                        case UP:
                            // Will decrease the xPos, yPos, row, and column attributes of the player
                            clonedPlayer.moveUp();
                            break;
                        case DOWN:
                            clonedPlayer.moveDown();
                            break;
                        case LEFT:
                            clonedPlayer.moveLeft();
                            break;
                        case RIGHT:
                            clonedPlayer.moveRight();
                            break;
                    }
                }
            }
        }


        // What are you going to do if direction is applied to the currentState?
        // All directions provided here are already valid
            // that it won't provide out of bounds error, will not overwrite invalid states
        LinkedList<Directions> initial = Clone.clonePath(currentState.getPath());
        initial.add(direction);
        State nextState = new State(clonedArray2D, clonedPlayer, currentState.getInitEndPointCount(), initial);
        return nextState;
    }
    public void setChangesInArray(String[][] clonedArray2D, Player clonedPlayer, Directions direction){
        int currentRow = clonedPlayer.getCurrRow(); 
        int currentColumn = clonedPlayer.getCurrCol();
        int nextRow = World.UNINITIALIZED_INT, nextColumn = World.UNINITIALIZED_INT;
        String prevState = clonedPlayer.getPreviousPosition();
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
        clonedArray2D[currentRow][currentColumn] = prevState;
        clonedPlayer.setPreviousPosition(clonedArray2D[nextRow][nextColumn]);
        if(clonedArray2D[nextRow][nextColumn].equals(World.ENDPOINT)){
            clonedArray2D[nextRow][nextColumn] = World.WAREHOUSE_KEEPER_ON_ENDPOINT;
        }
        else clonedArray2D[nextRow][nextColumn] = World.WAREHOUSE_KEEPER;
    }
    private boolean GoalTest( State currentState){
        // already fully functional
        String[][] arrayToInspect = currentState.getWorldArray();
        int sealCounter = 0;
        for(int i = 0; i < World.ROWS; i++){
            for(int j = 0; j < World.COLS; j++){
                if(arrayToInspect[i][j].equals(World.BOX_IN_STORAGE)){
                        sealCounter++;
                }    
            }
        }
        if(sealCounter == currentState.getInitEndPointCount()) return true;
        else return false;
    }

    private LinkedList<Directions> Actions(State currentState){
        LinkedList<Directions> actionList = new LinkedList<Directions>();
        // To know the possible values of actionList:
            // We need to consider
                // 1. If the next position will be an empty tile
                // 2. If there is a box, push or stay - if stay -> don't add to actionList
                // 3. If out of bounds -> highest priority to avoid other mishaps
        String[][] arrayToBeInspected = currentState.getWorldArray();
        Player playerToBeInspected = currentState.getPlayer();

        boolean outOfBounds = false;
        String object = "x";
        boolean boxOutOfBounds = false;
        int currentRow = playerToBeInspected.getCurrRow();
        int currentCol = playerToBeInspected.getCurrCol();
        outOfBounds = currentRow - 1 >= 0 ? false : true;
        if(!outOfBounds){
            object = up(arrayToBeInspected, playerToBeInspected);
            if(object.equals(World.EMPTY) || object.equals(World.ENDPOINT)){
                actionList.add(Directions.UP);
            }
            else if(object.equals(World.BOX) || object.equals(World.BOX_IN_STORAGE)){
                int nextBoxRow = currentRow - 2;
                boxOutOfBounds = nextBoxRow >= 0 ? false : true;
                if(!boxOutOfBounds){
                    String objectTwo = arrayToBeInspected[nextBoxRow][currentCol];
                    if(objectTwo.equals(World.EMPTY) || objectTwo.equals(World.ENDPOINT)){
                        actionList.add(Directions.UP);
                    }
                }
            }
        }
        // Inspect down
        outOfBounds = currentRow + 1 >= 0 ? false : true;
        if(!outOfBounds){
            object = down(arrayToBeInspected, playerToBeInspected);
            if(object.equals(World.EMPTY) || object.equals(World.ENDPOINT)){
                actionList.add(Directions.DOWN);
            }
            else if(object.equals(World.BOX) || object.equals(World.BOX_IN_STORAGE)){
                int nextBoxRow = currentRow + 2;
                boxOutOfBounds = nextBoxRow < World.COLS ? false : true;
                if(!boxOutOfBounds){
                    String objectTwo = arrayToBeInspected[nextBoxRow][currentCol];
                    if(objectTwo.equals(World.EMPTY) || objectTwo.equals(World.ENDPOINT)){
                        actionList.add(Directions.DOWN);
                    }
                }
            }
        }
        // Inspect left
        outOfBounds = currentCol - 1 >= 0 ? false : true;
        if(!outOfBounds){
            object = left(arrayToBeInspected, playerToBeInspected);
            if(object.equals(World.EMPTY) || object.equals(World.ENDPOINT)){
                actionList.add(Directions.LEFT);
            }
            else if(object.equals(World.BOX) || object.equals(World.BOX_IN_STORAGE)){
                int nextBoxCol = currentCol - 2;
                boxOutOfBounds = nextBoxCol >= 0 ? false : true;
                if(!boxOutOfBounds){
                    String objectTwo = arrayToBeInspected[currentRow][nextBoxCol];
                    if(objectTwo.equals(World.EMPTY) || objectTwo.equals(World.ENDPOINT)){
                        actionList.add(Directions.LEFT);
                    }
                }
            }
        }
        // Inspect right
        outOfBounds = currentCol + 1 >= 0 ? false : true;
        if(!outOfBounds){
            object = right(arrayToBeInspected, playerToBeInspected);
            if(object.equals(World.EMPTY) || object.equals(World.ENDPOINT)){
                actionList.add(Directions.RIGHT);
            }
            else if(object.equals(World.BOX) || object.equals(World.BOX_IN_STORAGE)){
                int nextBoxCol = currentCol + 2;
                boxOutOfBounds = nextBoxCol < World.COLS ? false : true;
                if(!boxOutOfBounds){
                    String objectTwo = arrayToBeInspected[currentRow][nextBoxCol];
                    if(objectTwo.equals(World.EMPTY) || objectTwo.equals(World.ENDPOINT)){
                        actionList.add(Directions.RIGHT);
                    }
                }
            }
        }
        return actionList;
    }

    private String up(String[][] worldArray, Player player){
        return worldArray[player.getCurrRow() - 1][player.getCurrCol()];
    }
    private String down(String[][] worldArray, Player player){
        return worldArray[player.getCurrRow() + 1][player.getCurrCol()];
    }
    private String left(String[][] worldArray, Player player){
        return worldArray[player.getCurrRow()][player.getCurrCol() - 1];        
    }
    private String right(String[][] worldArray, Player player){
        return worldArray[player.getCurrRow()][player.getCurrCol() + 1];
    }

}