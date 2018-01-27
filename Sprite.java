import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class Sprite extends JPanel{
  private BufferedImage img;
  protected int xPos;
  protected int yPos;

  public Sprite(int xPos, int yPos, Texture texture){
    this.setOpaque(false);
    this.setSize(Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
    this.xPos = xPos;
    this.yPos = yPos;
    this.img = texture.getImage();
  }

  public void setImage(BufferedImage img){
    this.img = img;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D)g;
    g2d.drawImage(this.img, this.xPos, this.yPos, null);
    Toolkit.getDefaultToolkit().sync();
    g2d.dispose();
  }

  public int getXpos(){
      return this.xPos;
  }

  public int getYpos(){
      return this.yPos;
  }
}