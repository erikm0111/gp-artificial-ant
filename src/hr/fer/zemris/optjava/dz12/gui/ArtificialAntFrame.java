package hr.fer.zemris.optjava.dz12.gui;

import hr.fer.zemris.optjava.dz12.solution.ArtificialAnt;
import hr.fer.zemris.optjava.dz12.solution.Executor;
import hr.fer.zemris.optjava.dz12.solution.Node;
import hr.fer.zemris.optjava.dz12.solution.TreeBuilder;

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

        JButton btnNextStep = new JButton("Next step");
        add(btnNextStep, BorderLayout.SOUTH);

        TreeBuilder tb = new TreeBuilder();
        Node root = tb.createRandomTree(10);
        Executor ex = new Executor(ant, finalMap, width, height);
        ex.executeTree(root);

        btnNextStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean notDone = ex.nextAction();
                if (!notDone){
                    ex.executeTree(root);
                    ex.nextAction();
                }
                panel.repaint();
            }
        });

    }
}
