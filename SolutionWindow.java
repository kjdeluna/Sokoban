import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;

public class SolutionWindow{
    private static final String SOLUTION_TITLE = "Solution";
    public static final int SOLUTION_FRAME_WIDTH = 600;
    public static final int SOLUTION_FRAME_HEIGHT = 300;
    public SolutionWindow(){
        JFrame solutionFrame = new JFrame(SolutionWindow.SOLUTION_TITLE);
        solutionFrame.setPreferredSize(new Dimension(SOLUTION_FRAME_WIDTH, SOLUTION_FRAME_HEIGHT));
        solutionFrame.setResizable(false);
        solutionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        SolutionPanel sp = new SolutionPanel();
        solutionFrame.add(sp);

        solutionFrame.pack();
        solutionFrame.setVisible(true);
    }


}