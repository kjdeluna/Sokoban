// Suggestions: Create constants, remove unused packages
// To do: working prev and next buttons

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.util.LinkedList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class SolutionPanel extends JPanel implements KeyListener{
    private ArrowButton upArrowButton;
    private ArrowButton downArrowButton;
    private ArrowButton leftArrowButton;
    private ArrowButton rightArrowButton;
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

        this.prevButton = new JButton("<- previous");
        this.nextButton = new JButton("next ->");
        // Add to the panel
        this.upArrowButton = new ArrowButton("up_arrowkey.png", "up_arrowkey_blur.png");
        this.downArrowButton = new ArrowButton("down_arrowkey.png", "down_arrowkey_blur.png");
        this.leftArrowButton = new ArrowButton("left_arrowkey.png", "left_arrowkey_blur.png");
        this.rightArrowButton = new ArrowButton("right_arrowkey.png", "right_arrowkey_blur.png");
        this.add(upArrowButton);
        this.add(downArrowButton);
        this.add(leftArrowButton);
        this.add(prevButton);
        this.add(rightArrowButton);
        this.add(nextButton);

        // Locate their locations
        upArrowButton.setBounds(275,75,64,64);
        downArrowButton.setBounds(275,139,64,64);
        leftArrowButton.setBounds(211,139,64,64);
        rightArrowButton.setBounds(339,139,64,64);
        prevButton.setBounds(100,225,100, 40);
        nextButton.setBounds(428,225,100, 40);
        
        // // Which button will appear first

        if(this.path.get(currentIndex) == Directions.UP){
            this.upArrowButton.unsetIcon();
        } else if (this.path.get(currentIndex) == Directions.DOWN){
            this.downArrowButton.unsetIcon();
        } else if (this.path.get(currentIndex) == Directions.LEFT){
            this.leftArrowButton.unsetIcon();
        } else if (this.path.get(currentIndex) == Directions.RIGHT){
            this.rightArrowButton.unsetIcon();
        }

        // this.addKeyListener(this);
        // this.setFocusable(true);
        // this.requestFocusInWindow();

        upArrowButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!upArrowButton.isDisabled()){
                    world.passDirectionToWorld(Directions.UP);
                    setNextIcon(Directions.UP); 
                } 
            }
        });
        downArrowButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!downArrowButton.isDisabled()){
                    world.passDirectionToWorld(Directions.DOWN);
                    setNextIcon(Directions.DOWN);  
                }
            }
        });
        leftArrowButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!leftArrowButton.isDisabled()){
                    world.passDirectionToWorld(Directions.LEFT);
                    setNextIcon(Directions.LEFT); 
                } 
            }
        });
        rightArrowButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!rightArrowButton.isDisabled()){
                    world.passDirectionToWorld(Directions.RIGHT);
                    setNextIcon(Directions.RIGHT);  
                }
            }
        });
        // prevButton.addActionListener(new ActionListener(){
        //     public void actionPerformed(ActionEvent e){
        //         currentIndex--;
        //         if(currentIndex < 0) return;
        //         Directions nextDir = path.get(currentIndex);

        //         ImageIcon img;
        //         switch(nextDir){
        //             case UP: 
        //                 img = new ImageIcon("resources/icons/up_arrowkey.png");
        //                 upArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/down_arrowkey_blur.png");
        //                 downArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/left_arrowkey_blur.png");
        //                 leftArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/right_arrowkey_blur.png");
        //                 rightArrowButton.setIcon(img);               
        //                 break;
        //             case DOWN:  
        //                 img = new ImageIcon("resources/icons/up_arrowkey_blur.png");
        //                 upArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/down_arrowkey.png");
        //                 downArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/left_arrowkey_blur.png");
        //                 leftArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/right_arrowkey_blur.png");
        //                 rightArrowButton.setIcon(img);
        //                 break;
        //             case LEFT:
        //                 img = new ImageIcon("resources/icons/up_arrowkey_blur.png");
        //                 upArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/down_arrowkey_blur.png");
        //                 downArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/left_arrowkey.png");
        //                 leftArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/right_arrowkey_blur.png");
        //                 rightArrowButton.setIcon(img);
        //                 break;
        //             case RIGHT:
        //                 img = new ImageIcon("resources/icons/up_arrowkey_blur.png");
        //                 upArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/down_arrowkey_blur.png");
        //                 downArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/left_arrowkey_blur.png");
        //                 leftArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/right_arrowkey.png");
        //                 rightArrowButton.setIcon(img);
        //                 break;
        //             default: System.out.println("Something wrong with dir");
                         
        //         }
        //     }
        // });

        // nextButton.addActionListener(new ActionListener(){
        //     public void actionPerformed(ActionEvent e){
        //         currentIndex++;
        //         if(currentIndex >= path.size()) return;
        //         Directions nextDir = path.get(currentIndex);

        //         ImageIcon img;
        //         switch(nextDir){
        //             case UP: 
        //                 img = new ImageIcon("resources/icons/up_arrowkey.png");
        //                 upArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/down_arrowkey_blur.png");
        //                 downArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/left_arrowkey_blur.png");
        //                 leftArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/right_arrowkey_blur.png");
        //                 rightArrowButton.setIcon(img);               
        //                 break;
        //             case DOWN:  
        //                 img = new ImageIcon("resources/icons/up_arrowkey_blur.png");
        //                 upArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/down_arrowkey.png");
        //                 downArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/left_arrowkey_blur.png");
        //                 leftArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/right_arrowkey_blur.png");
        //                 rightArrowButton.setIcon(img);
        //                 break;
        //             case LEFT:
        //                 img = new ImageIcon("resources/icons/up_arrowkey_blur.png");
        //                 upArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/down_arrowkey_blur.png");
        //                 downArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/left_arrowkey.png");
        //                 leftArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/right_arrowkey_blur.png");
        //                 rightArrowButton.setIcon(img);
        //                 break;
        //             case RIGHT:
        //                 img = new ImageIcon("resources/icons/up_arrowkey_blur.png");
        //                 upArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/down_arrowkey_blur.png");
        //                 downArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/left_arrowkey_blur.png");
        //                 leftArrowButton.setIcon(img);
        //                 img = new ImageIcon("resources/icons/right_arrowkey.png");
        //                 rightArrowButton.setIcon(img);
        //                 break;
        //             default: System.out.println("Something wrong with dir");
                         
        //         }
        //     }
        // });

    }
    public void setNextIcon(Directions dir){
        if(this.currentIndex >= this.path.size() - 1){
            this.removeKeyListener(this);
            return;
        }
        else{
            if(dir.equals(this.path.get(currentIndex))){
                switch(this.path.get(currentIndex)){
                    case UP:
                        this.upArrowButton.unsetIcon();
                        break;
                    case DOWN:
                        this.downArrowButton.unsetIcon();
                        break;
                    case LEFT:  
                        this.leftArrowButton.unsetIcon();
                        break;
                    case RIGHT:
                        this.rightArrowButton.unsetIcon();
                }
                currentIndex++;
                Directions nextDir = this.path.get(currentIndex);

                ImageIcon img;
                switch(nextDir){
                    case UP: 
                        this.upArrowButton.unsetIcon();           
                        break;
                    case DOWN:  
                        this.downArrowButton.unsetIcon();
                        break;
                    case LEFT:
                        this.leftArrowButton.unsetIcon();
                        break;
                    case RIGHT:
                        this.rightArrowButton.unsetIcon();
                        break;
                    default: System.out.println("Something wrong with dir");
                    
                }
            }
       }
    }
    public void keyTyped(KeyEvent e){}
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
    public void keyReleased(KeyEvent e){}
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(World.textureLoader.getTexture(Texture.DEFAULT_PATH, "summer.png").getImage(),0,0,null);
    }
    
}
