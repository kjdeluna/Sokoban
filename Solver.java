import java.util.Queue;
import java.util.ArrayDeque;
import java.util.LinkedList;
public class Solver {
    private Queue<State> frontier;
    private State parentState;
    public Solver(State initialState){
        // Initial state 
        // When solver is instantiated, it gets the initial state -> parentState
        //                  of the game.
        this.parentState = initialState;
        this.frontier = new ArrayDeque<State>();
        this.breadthFirstSearch();
    }

    private State breadthFirstSearch(){
        State currentState;
        this.frontier.offer(this.parentState);
        while(this.frontier.peek() != null){
            currentState = this.frontier.poll();
            if(GoalTest(currentState)) return currentState;
            else{
                LinkedList<Directions> actionList = Actions(currentState);
                for(Directions direction : actionList){
                    System.out.println(direction);
                }
            }
        }
        return null;
    }
    private boolean GoalTest( State currentState){
        // already fully functional
        String[][] arrayToInspect = this.parentState.getWorldArray();
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
        int currentRow = playerToBeInspected.getCurrRow();
        int currentCol = playerToBeInspected.getCurrCol();
        System.out.println("curr2" + currentRow);
        outOfBounds = currentRow - 1 >= 0 ? false : true;
        if(!outOfBounds){
            object = up(arrayToBeInspected, playerToBeInspected);
            if(object.equals(World.EMPTY) || object.equals(World.ENDPOINT)){
                actionList.add(Directions.UP);
            }
            else if(object.equals(World.BOX) || object.equals(World.BOX_IN_STORAGE)){
                int nextBoxRow = currentRow - 2;
                String objectTwo = arrayToBeInspected[nextBoxRow][currentCol];
                if(objectTwo.equals(World.EMPTY) || objectTwo.equals(World.ENDPOINT)){
                    actionList.add(Directions.UP);
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
                String objectTwo = arrayToBeInspected[nextBoxRow][currentCol];
                if(objectTwo.equals(World.EMPTY) || objectTwo.equals(World.ENDPOINT)){
                    actionList.add(Directions.DOWN);
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
                String objectTwo = arrayToBeInspected[currentRow][nextBoxCol];
                if(objectTwo.equals(World.EMPTY) || objectTwo.equals(World.ENDPOINT)){
                    actionList.add(Directions.LEFT);
                }
            }
        }
        
        // Inspect right
        outOfBounds = currentCol + 1 >= 0 ? false : true;
        if(!outOfBounds){
            object = right(arrayToBeInspected, playerToBeInspected);
            if(object.equals(World.EMPTY) || object.equals(World.ENDPOINT)){
                System.out.println("curr" +currentRow);
                actionList.add(Directions.RIGHT);
            }
            else if(object.equals(World.BOX) || object.equals(World.BOX_IN_STORAGE)){
                int nextBoxCol = currentCol + 2;
                String objectTwo = arrayToBeInspected[currentRow][nextBoxCol];
                if(objectTwo.equals(World.EMPTY) || objectTwo.equals(World.ENDPOINT)){
                    actionList.add(Directions.RIGHT);
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