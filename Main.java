import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {

    // Frame attributes
    private final static String TITLE = "Sokoban";
    // Dimensions
    public final static int FRAME_WIDTH = 640;
    public final static int FRAME_HEIGHT = 740;
    // States - will be useful in rendering other HOCs
    public enum STATE{
        GAME
    };

    // textureLoader -> contains a hashmap of textures
    private static String[] textures;
    public static TextureLoader textureLoader;

    // Will hold the STATE attribute
    private static STATE state = STATE.GAME;

    public static void main(String[] args){
        Main.textures = new String[]{ 
            "box.png",
            "box_in_storage.png",
            "empty.png",
            "endpoint.png",
            "outside.png",
            "player_moveup.png",
            "player_movedown.png",
            "player_moveleft.png",
            "player_moveright.png",
            "selector.png",
            "wall.png",
            "background.png",
            "solutionsBack.jpg"
        };
        // Load the textures mentioned
        Main.textureLoader = new TextureLoader(Texture.DEFAULT_PATH, Main.textures);

        // ------- Initialize JFrame ---------
        JFrame gameFrame = new JFrame(Main.TITLE);
        gameFrame.setPreferredSize(new Dimension(Main.FRAME_WIDTH, Main.FRAME_HEIGHT));
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // --- JFrame initialize end -----------

        World world = new World();
        Options options = new Options(world);
        // Pack components to JFrame
        gameFrame.add(world);
        gameFrame.add(options);
        gameFrame.pack();
        
        // Center the frame
        gameFrame.setLocationRelativeTo(null); // Center gameFrame to the screen
        
        gameFrame.setVisible(true);
    }
}