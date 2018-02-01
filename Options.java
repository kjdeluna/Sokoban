import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
// Options bar is just static and does not need to render anything except the buttons
public class Options extends JPanel{
    private World world2;
    public Options(World world){
        this.setLayout(null);
        this.setBounds(0,640,Main.FRAME_WIDTH, 100);
        this.setPreferredSize(new Dimension(Main.FRAME_WIDTH, 100));
        JButton bfsButton = new JButton("BFS Solve");
        JButton dfsButton = new JButton("DFS Solve");
        JButton fileButton = new JButton("Open file");
        JFileChooser fileChooser = new JFileChooser();
        this.setBackground(Color.RED);
        this.add(dfsButton);
        this.add(bfsButton);
        this.add(fileButton);
        bfsButton.setBounds(50, 675, 150, 40);
        dfsButton.setBounds(230, 675, 150, 40);
        fileButton.setBounds(410, 675, 150, 40);
        // fileChooser.setBounds(410, 675, 500, 40);
        bfsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println(" bfs I'm working");
                LinkedList<Directions> ab = new LinkedList<Directions>();
                State initialState = new State(world2.getWorldArray(), world2.getPlayer(), world2.getInitialEndPointCount(), ab);
                Solver bfsSolver = new Solver(initialState);
            }
        });

        dfsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println(" dfs I'm working");
            }
        });

        fileButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fileChooser.showOpenDialog(null);            }
        });
        bfsButton.setFocusable(false);
        dfsButton.setFocusable(false);
        fileButton.setFocusable(false);
        this.world2 = world;
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        System.out.println("Repainting triggered in options");
        World.textureLoader.getTexture(Texture.DEFAULT_PATH, "background.png").render(g2d, 0, 640);
    }   
}