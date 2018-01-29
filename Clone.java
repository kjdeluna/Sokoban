import java.util.LinkedList;

public class Clone{
    public static Player clonePlayer(Player p){
        Texture tex = p.getTexture();
        int currRow = p.getCurrRow();
        int currCol = p.getCurrCol();
        String prevPosition = p.getPreviousPosition();
        Player a = new Player(currRow, currCol, tex, prevPosition);
        return a;
    }

    public static String[][] cloneArray2D(String[][] src){
        int length = src.length;
        String[][] target = new String[length][src[0].length];
        for(int i = 0 ; i < length; i++){
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

    public static LinkedList<Directions> clonePath(LinkedList<Directions> directions){
        LinkedList<Directions> path = new LinkedList<Directions>(directions);
        return path;
    }
}