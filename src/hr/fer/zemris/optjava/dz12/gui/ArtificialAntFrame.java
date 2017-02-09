package hr.fer.zemris.optjava.dz12.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ematosevic on 07.02.17..
 */
public class ArtificialAntFrame extends JFrame{
//    public static void main(String[] args){
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                ArtificialAntFrame frame = new ArtificialAntFrame();
//            }
//        });
//    }

    public ArtificialAntFrame(int[][] finalMap, int width, int height){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 900);
        setLocation(300, 100);


        JPanel panel = new JPanel(new GridLayout(width, height));
        add(panel);


        JLabel[][] lbls = new JLabel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                String val = "";
                switch (finalMap[i][j]){
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
                panel.add(lbls[i][j]);
            }
        }
    }
}
