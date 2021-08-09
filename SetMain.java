/**
musicMain.java
File to run
Tully Eva
07/29/2021
*/
import javax.swing.*;
import java.awt.Dimension;

public class SetMain
{
    /**
     * initiate the Frame
     * and create a HomepageMenu panel
     * @param args
     */
    public static void main(String[] args)
    {
        GameMeunue myPanel = new GameMeunue();
        JFrame myFrame = new JFrame("SET");
        myFrame.setPreferredSize(new Dimension(500, 500));
        myFrame.setSize(new Dimension(500, 500));
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setResizable(false);
        myFrame.add(myPanel);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);

        myFrame.setVisible(true);
    }
}
