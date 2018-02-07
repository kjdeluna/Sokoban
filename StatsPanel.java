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
    private JTextArea solutionTextArea;
    private JTextArea statsTextArea;
    private long time;
    private int nodesGenerated;
    private JButton viewSolutionButton;
    private JScrollPane solutionTextAreaScrollPane;
    public StatsPanel(LinkedList<Directions> path, long time, int nodesGenerated){
        this.path = path;
        this.time = time;
        this.nodesGenerated = nodesGenerated;

        this.viewSolutionButton = new JButton("View Solution");
        // Set bounds of the panel
        this.setBounds(0, 400, 600, 200);
        JLabel solutionLabel = new JLabel("Solution");
        // this.add(solutionLabel);
        this.add(viewSolutionButton);
        // Create textArea
        this.solutionTextArea = new JTextArea(stringifySolution());
        this.solutionTextAreaScrollPane = new JScrollPane(this.solutionTextArea);
        this.solutionTextAreaScrollPane.setPreferredSize(new Dimension(150, 150));
        // this.statsTextArea.setBackground(new Color(255,255,255,128));
        this.solutionTextArea.setVisible(true);
        solutionTextAreaScrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.	VERTICAL_SCROLLBAR_AS_NEEDED );
        solutionTextArea.setEditable(false);
        solutionTextAreaScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        this.add(solutionTextAreaScrollPane);
        this.statsTextArea = new JTextArea(stringifyStats());
        this.statsTextArea.setPreferredSize(new Dimension(200, 150));
        this.statsTextArea.setVisible(true);
        this.statsTextArea.setEditable(false);
        this.add(statsTextArea);    
        viewSolutionButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                solutionTextArea.setVisible(!solutionTextArea.isVisible());
            }
        });
    }
public void setSolutionTextAreaVisibility(boolean value){
    this.solutionTextArea.setEditable(value);
}
public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(new Color(255,255,0,128));
    g2d.fillRect(0,0,SolutionWindow.SOLUTION_FRAME_WIDTH, SolutionWindow.SOLUTION_FRAME_HEIGHT);
}

private String stringifyStats(){
    String soln = "Stats:\n\n";
    soln += "Time taken: " + Long.toString(this.time) + " milliseconds\n";
    soln += "Total moves: " + this.path.size() + "\n";
    soln += "Total nodes generated: " + Integer.toString(this.nodesGenerated);
    return soln;
}

private String stringifySolution(){
    String soln = "";
    int count = 1;
    for(Directions direction : this.path){
        soln += Integer.toString(count) + ". "+direction.toString() + "\n";
        count++;
    }
    return soln;
}
}