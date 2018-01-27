import javax.swing.JFrame;
import java.awt.Dimension;

public class Main {

    // Frame attributes
    private final static String TITLE = "Sokoban";
    // Dimensions
    public final static int FRAME_WIDTH = 640;
    public final static int FRAME_HEIGHT = 640;
    // States - will be useful in rendering other HOCs
    public enum STATE{
        GAME
    };
    // Will hold the STATE attribute
    private static STATE state = STATE.GAME;

    public static void main(String[] args){
        // ------- Initialize JFrame ---------
        JFrame gameFrame = new JFrame(Main.TITLE);
        gameFrame.setPreferredSize(new Dimension(Main.FRAME_WIDTH, Main.FRAME_HEIGHT));
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // --- JFrame initialize end -----------

        World world = new World();
        // Pack components to JFrame
        gameFrame.add(world);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }
}