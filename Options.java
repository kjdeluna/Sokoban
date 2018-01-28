import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Options bar is just static and does not need to render anything except the buttons
public class Options extends JPanel{
    public Options(){
        this.setLayout(null);

        this.setPreferredSize(new Dimension(Main.FRAME_WIDTH, 100));
        JButton bfsButton = new JButton("BFS Solve");
        JButton dfsButton = new JButton("DFS Solve");
        this.setBackground(Color.WHITE); // try to paint some component here
        this.setOpaque(true);    
        bfsButton.setBounds(50, 675, 150, 40);
        dfsButton.setBounds(230, 675, 150, 40);
        this.add(bfsButton);
        this.add(dfsButton);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        World.textureLoader.getTexture(Texture.DEFAULT_PATH, "background.png").render(g2d, 0, 640);
    }
}