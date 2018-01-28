public class Clone{
    public static Player clonePlayer(Player p){
        Texture tex = p.getTexture();
        int currRow = p.getCurrRow();
        int currCol = p.getCurrCol();
        String prevPosition = p.getPreviousPosition();
        Player a = new Player(currRow, currCol, tex, prevPosition);
        return a;
    }
}