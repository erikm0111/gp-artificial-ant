package hr.fer.zemris.optjava.dz12.gui;

import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import hr.fer.zemris.optjava.dz12.solution.ArtificialAnt;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ematosevic on 09.02.17..
 */
public class MapPanel extends JPanel{
    private ArtificialAnt ant;
    private int width, height;
    private JLabel[][] lbls;
    private int[][] map;

    public MapPanel(ArtificialAnt ant, int[][] map, int width, int height){
        this.ant = ant;
        this.map = map;
        this.width = width;
        this.height = height;
        setLayout(new GridLayout(width, height));

        lbls = new JLabel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                String val = "";
                switch (map[i][j]){
                    case -1:
                        val = "";
                        break;
                    case 0:
                        val = ".";
                        break;
                    case 1:
                        val = "#";
                        break;
                }
                lbls[i][j] = new JLabel(val);
                lbls[i][j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                add(lbls[i][j]);
            }
        }

        lbls[ant.getX()][ant.getY()].setText("<html><font color='red'>@</font></html>");

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                String val = "";
                switch (map[i][j]){
                    case -1:
                        val = "";
                        break;
                    case 0:
                        val = ".";
                        break;
                    case 1:
                        val = "#";
                        break;
                }
                lbls[i][j].setText(val);
            }
        }
        switch (ant.getDirection()){
            case NORTH:
                lbls[ant.getX()][ant.getY()].setText("<html><font color='red'>^</font></html>");
                break;
            case WEST:
                lbls[ant.getX()][ant.getY()].setText("<html><font color='red'>&lt;</font></html>");
                break;
            case SOUTH:
                lbls[ant.getX()][ant.getY()].setText("<html><font color='red'>v</font></html>");
                break;
            case EAST:
                lbls[ant.getX()][ant.getY()].setText("<html><font color='red'>&gt;</font></html>");
                break;
        }
    }
}
