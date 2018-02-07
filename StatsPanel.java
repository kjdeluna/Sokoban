import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.util.LinkedList;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.JViewport;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Static Component
public class StatsPanel extends JPanel{
    private LinkedList<Directions> path;
    private long time;
    private int nodesGenerated;
    private JButton viewSolutionButton;
    private JScrollPane solutionTextAreaScrollPane;
    private JTextArea solutionTextArea;
    private JTextArea statsTextArea;
    public StatsPanel(LinkedList<Directions> path, long time, int nodesGenerated){
        // Needed by statsTextArea
        this.path = path;
        this.time = time;
        this.nodesGenerated = nodesGenerated;
        
        // To trigger solutionTextArea
        //      The button should not be focusable to avoid disabling of keyListener
        this.viewSolutionButton = new JButton("Show Solution");
        this.viewSolutionButton.setFocusable(false);
        
        // Set bounds of the panel
        this.setBounds(0, 400, 600, 200);
        this.add(viewSolutionButton);
        
        // Create solutionTextArea
        //      -> solutionTextArea's contents are stored in a strng returned by stringifySolution()
        this.solutionTextArea = new JTextArea(stringifySolution());
        // In case that the output of the solution is too long, add JScrollPane
        this.solutionTextAreaScrollPane = new JScrollPane(this.solutionTextArea);
        this.solutionTextAreaScrollPane.setPreferredSize(new Dimension(150, 150));
        // Initially, solutionTextArea is not visible but user may opt to make it visible -> viewSolutionButton
        this.solutionTextArea.setVisible(false);
        this.solutionTextArea.setEditable(false);
        this.solutionTextAreaScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        
        // stringifyStats will return string representation of the stats obtained
        this.statsTextArea = new JTextArea(stringifyStats());
        this.statsTextArea.setPreferredSize(new Dimension(200, 150));
        this.statsTextArea.setVisible(true);
        this.statsTextArea.setEditable(false);
        
        // Add them to this panel
        this.add(solutionTextAreaScrollPane);
        this.add(statsTextArea);
    
        viewSolutionButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // If clicked and text area is visible, set the proper button labels 
                if(solutionTextArea.isVisible()){
                    viewSolutionButton.setText("Show Solution");
                }
                else viewSolutionButton.setText("Hide Solution");
                solutionTextArea.setVisible(!solutionTextArea.isVisible());
            }
        });
    }
    private void setSolutionTextAreaVisibility(boolean value){
        this.solutionTextArea.setEditable(value);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255,255,0,128));
        g2d.fillRect(0,0,SolutionWindow.SOLUTION_FRAME_WIDTH, SolutionWindow.SOLUTION_FRAME_HEIGHT);
    }

    private String stringifyStats(){
        // This function stringifies the stats statement
        String soln = "Stats:\n\n";
        soln += "Time taken: " + Long.toString(this.time) + " milliseconds\n";
        soln += "Total moves: " + this.path.size() + "\n";
        soln += "Total nodes generated: " + Integer.toString(this.nodesGenerated);
        return soln;
    }

    private String stringifySolution(){
        // This function stringifies the solution list
        String soln = "";
        int count = 1;
        for(Directions direction : this.path){
            soln += Integer.toString(count) + ". "+direction.toString() + "\n";
            count++;
        }
        return soln;
    }
}