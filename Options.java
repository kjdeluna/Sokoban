import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


// Options bar is just static and does not need to rerender anything
public class Options extends JPanel{
    private World world;
    public Options(World world){
        this.setLayout(null);
        this.setBounds(0,640,Main.FRAME_WIDTH, 100);
        this.setPreferredSize(new Dimension(Main.FRAME_WIDTH, 100));

        // Instantiate JButtons
        JButton bfsButton = new JButton("BFS Solve");
        JButton dfsButton = new JButton("DFS Solve");
        JButton fileButton = new JButton("Open file");

        // Instantiate JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        this.setBackground(Color.RED);
        
        // Add JButtons this JPanel (Options)
        this.add(dfsButton);
        this.add(bfsButton);
        this.add(fileButton);

        // Set buttons' absolute x, y, height and width
        bfsButton.setBounds(50, 675, 150, 40);
        dfsButton.setBounds(230, 675, 150, 40);
        fileButton.setBounds(410, 675, 150, 40);

        this.world = world;

        // Link Solver to bfsButton onClick
        bfsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // Instantiate initialPath -> this will contain a linked list of directions taken by player
                LinkedList<Directions> initialPath = new LinkedList<Directions>();

                // Create initialState based on the current position -> this is where the solver will start
                State initialState = new State(world.getWorldArray(), world.getPlayer(), world.getInitialEndPointCount(), initialPath);
                
                // Pass initialState to the Solver
                Solver bfsSolver = new Solver(initialState);
            }
        });

        dfsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println(" dfs I'm working");
            }
        });

        // Link fileButton to fileChooser
        fileButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FileFilter filter = new FileNameExtensionFilter("IN files", "in");
                fileChooser.setFileFilter(filter);                
                fileChooser.showOpenDialog(null);
                String filenameChosen = fileChooser.getSelectedFile().getName();
                world.readFile(filenameChosen);
            }
        });

        bfsButton.setFocusable(false);
        dfsButton.setFocusable(false);
        fileButton.setFocusable(false);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        World.textureLoader.getTexture(Texture.DEFAULT_PATH, "background.png").render(g2d, 0, 640);
    }   
}