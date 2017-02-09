package hr.fer.zemris.optjava.dz12.gui;

import hr.fer.zemris.optjava.dz12.solution.ArtificialAnt;
import hr.fer.zemris.optjava.dz12.solution.Node;
import hr.fer.zemris.optjava.dz12.solution.TreeBuilder;
import hr.fer.zemris.optjava.dz12.solution.TreeExecutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ematosevic on 07.02.17..
 */
public class ArtificialAntFrame extends JFrame{
    private ArtificialAnt ant;
//    public static void main(String[] args){
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                ArtificialAntFrame frame = new ArtificialAntFrame();
//            }
//        });
//    }

    public ArtificialAntFrame(ArtificialAnt ant, int[][] finalMap, int width, int height){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 650);
        setLocation(300, 100);
        //sthis.ant = ant;

        setLayout(new BorderLayout());
        MapPanel panel = new MapPanel(ant, finalMap, width, height);
        add(panel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        add(southPanel, BorderLayout.SOUTH);

        JButton btnNextStep = new JButton("Next step");
        southPanel.add(btnNextStep, BorderLayout.WEST);

        JLabel lblScore = new JLabel("Score: ");
        southPanel.add(lblScore, BorderLayout.EAST);

        TreeBuilder tb = new TreeBuilder();
        Node root = tb.growTree(10);
        TreeExecutor ex = new TreeExecutor(ant, finalMap, width, height);
        ex.executeTree(root);

        btnNextStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean notDone = ex.nextAction();
                if (!notDone){
                    ex.executeTree(root);
                    ex.nextAction();
                }
                lblScore.setText("Score: " + ant.getScore());
                panel.repaint();
            }
        });

    }
}
