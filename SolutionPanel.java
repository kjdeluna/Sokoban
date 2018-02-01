import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.Color;
public class SolutionPanel extends JPanel{
    private JButton upArrowButton;
    private JButton downArrowButton;
    private JButton leftArrowButton;
    private JButton rightArrowButton;

    public SolutionPanel(){
        this.setLayout(null);
        this.setBounds(0,0,SolutionWindow.SOLUTION_FRAME_WIDTH, SolutionWindow.SOLUTION_FRAME_HEIGHT);
        this.setPreferredSize(new Dimension(SolutionWindow.SOLUTION_FRAME_WIDTH, SolutionWindow.SOLUTION_FRAME_HEIGHT));
        this.upArrowButton = new JButton();
        this.downArrowButton = new JButton();
        this.leftArrowButton = new JButton();
        this.rightArrowButton = new JButton();
        this.add(upArrowButton);
        this.add(downArrowButton);
        this.add(leftArrowButton);
        this.add(rightArrowButton);
        upArrowButton.setBounds(275,75,70,70);
        downArrowButton.setBounds(275,139,64,64);
        leftArrowButton.setBounds(211,139,64,64);
        rightArrowButton.setBounds(339,139,64,64);
        upArrowButton.setOpaque(false);
        upArrowButton.setContentAreaFilled(false);
        upArrowButton.setBorderPainted(false);
        ImageIcon img = new ImageIcon("resources/icons/up_arrowkey_blur.png");
        upArrowButton.setIcon(img);
        img = new ImageIcon("resources/icons/down_arrowkey_blur.png");
        downArrowButton.setIcon(img);
        img = new ImageIcon("resources/icons/left_arrowkey_blur.png");
        leftArrowButton.setIcon(img);
        img = new ImageIcon("resources/icons/right_arrowkey_blur.png");
        rightArrowButton.setIcon(img);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fillRect(0,0,600,300);
        
    }
    
}