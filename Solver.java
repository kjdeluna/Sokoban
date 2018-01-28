import java.util.Queue;
import java.util.ArrayDeque;
public class Solver {
    private Queue<String[][]> solverQueue;
    private State parentState;
    public Solver(State initialState){
        // Initial state 
        // When solver is instantiated, it gets the initial state -> parentState
        //                  of the game.
        this.parentState = initialState;
        this.solverQueue = new ArrayDeque<String[][]>();
        for(int i = 0 ; i < World.ROWS; i++){
            for(int j = 0; j < World.COLS; j++){
                System.out.print(this.parentState.getWorldArray()[i][j] + " ");
            }
            System.out.println("");
        }
    }


}