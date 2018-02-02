import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
public class SolutionPanel extends JPanel implements KeyListener{
    private JButton upArrowButton;
    private JButton downArrowButton;
    private JButton leftArrowButton;
    private JButton rightArrowButton;
    private JButton prevButton;
    private JButton nextButton;
    private LinkedList<Directions> path;
    private World world;
    private int currentIndex = 0;
    public SolutionPanel(World world, LinkedList<Directions> path){
        this.world = world;
        this.path = path;
        this.setLayout(null);
        this.setBounds(0,0,SolutionWindow.SOLUTION_FRAME_WIDTH, SolutionWindow.SOLUTION_FRAME_HEIGHT);
        this.setPreferredSize(new Dimension(SolutionWindow.SOLUTION_FRAME_WIDTH, SolutionWindow.SOLUTION_FRAME_HEIGHT));
        this.upArrowButton = new JButton();
        this.downArrowButton = new JButton();
        this.leftArrowButton = new JButton();
        this.rightArrowButton = new JButton();
        this.prevButton = new JButton("<- previous");
        this.nextButton = new JButton("next ->");
        this.add(upArrowButton);
        this.add(downArrowButton);
        this.add(leftArrowButton);
        this.add(prevButton);
        this.add(rightArrowButton);
        this.add(nextButton);
        upArrowButton.setBounds(275,75,64,64);
        downArrowButton.setBounds(275,139,64,64);
        leftArrowButton.setBounds(211,139,64,64);
        rightArrowButton.setBounds(339,139,64,64);
        prevButton.setBounds(100,225,100, 40);
        nextButton.setBounds(428,225,100, 40);
        ImageIcon img = new ImageIcon("resources/icons/up_arrowkey_blur.png");
        if(this.path.get(currentIndex) == Directions.UP) img = new ImageIcon("resources/icons/up_arrowkey.png");
        else img = new ImageIcon("resources/icons/up_arrowkey_blur.png");
        upArrowButton.setIcon(img);
        if(this.path.get(currentIndex) == Directions.DOWN) img = new ImageIcon("resources/icons/down_arrowkey.png");
        else img = new ImageIcon("resources/icons/down_arrowkey_blur.png");
        downArrowButton.setIcon(img);
        if(this.path.get(currentIndex) == Directions.LEFT) img = new ImageIcon("resources/icons/left_arrowkey.png");
        else img = new ImageIcon("resources/icons/left_arrowkey_blur.png");
        leftArrowButton.setIcon(img);
        if(this.path.get(currentIndex) == Directions.RIGHT) img = new ImageIcon("resources/icons/right_arrowkey.png");
        else img = new ImageIcon("resources/icons/right_arrowkey_blur.png");
        rightArrowButton.setIcon(img);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
        // this.setNextIcon(this.path.get(currentIndex));
    }
    public void setNextIcon(Directions dir){
        System.out.println("running" + dir);
        if(this.currentIndex >= this.path.size() - 1){
            this.removeKeyListener(this);
            return;
        }
        else{
        if(dir.equals(this.path.get(currentIndex))){
            currentIndex++;
            Directions nextDir = this.path.get(currentIndex);

            ImageIcon img;
            switch(nextDir){
                case UP: 
                    img = new ImageIcon("resources/icons/up_arrowkey.png");
                    this.upArrowButton.setIcon(img);
                    img = new ImageIcon("resources/icons/down_arrowkey_blur.png");
                    downArrowButton.setIcon(img);
                    img = new ImageIcon("resources/icons/left_arrowkey_blur.png");
                    leftArrowButton.setIcon(img);
                    img = new ImageIcon("resources/icons/right_arrowkey_blur.png");
                    rightArrowButton.setIcon(img);               
                    break;
                case DOWN:  
                    img = new ImageIcon("resources/icons/up_arrowkey_blur.png");
                    this.upArrowButton.setIcon(img);
                    img = new ImageIcon("resources/icons/down_arrowkey.png");
                    downArrowButton.setIcon(img);
                    img = new ImageIcon("resources/icons/left_arrowkey_blur.png");
                    leftArrowButton.setIcon(img);
                    img = new ImageIcon("resources/icons/right_arrowkey_blur.png");
                    rightArrowButton.setIcon(img);
                    break;
                case LEFT:
                    img = new ImageIcon("resources/icons/up_arrowkey_blur.png");
                    this.upArrowButton.setIcon(img);
                    img = new ImageIcon("resources/icons/down_arrowkey_blur.png");
                    downArrowButton.setIcon(img);
                    img = new ImageIcon("resources/icons/left_arrowkey.png");
                    leftArrowButton.setIcon(img);
                    img = new ImageIcon("resources/icons/right_arrowkey_blur.png");
                    rightArrowButton.setIcon(img);
                    break;
                case RIGHT:
                    img = new ImageIcon("resources/icons/up_arrowkey_blur.png");
                    this.upArrowButton.setIcon(img);
                    img = new ImageIcon("resources/icons/down_arrowkey_blur.png");
                    downArrowButton.setIcon(img);
                    img = new ImageIcon("resources/icons/left_arrowkey_blur.png");
                    leftArrowButton.setIcon(img);
                    img = new ImageIcon("resources/icons/right_arrowkey.png");
                    rightArrowButton.setIcon(img);
                    break;
                default: System.out.println("Something wrong with dir");
                
            }
        }
        }
    }
    public void keyTyped(KeyEvent e){
        System.out.println(e.getKeyChar());
    }
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                world.passDirectionToWorld(Directions.UP);
                this.setNextIcon(Directions.UP);
                break;
            case KeyEvent.VK_DOWN:
                world.passDirectionToWorld(Directions.DOWN);
                this.setNextIcon(Directions.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                world.passDirectionToWorld(Directions.LEFT);
                this.setNextIcon(Directions.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                world.passDirectionToWorld(Directions.RIGHT);
                this.setNextIcon(Directions.RIGHT);
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        System.out.println(e.getKeyChar());
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // g2d.setColor(Color.RED);
        // g2d.fillRect(0,0,600,300);
        g2d.drawImage(World.textureLoader.getTexture(Texture.DEFAULT_PATH, "background.jpg").getImage(),0,0,null);
        
    }
    
}